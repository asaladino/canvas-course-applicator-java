package com.codingsimply.app.canvascourseapplicator.core.repositories;

import com.codingsimply.app.canvascourseapplicator.data.models.ApiConnector;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Handles the basic rest repo interactions.
 *
 * @author Adam Saladino
 */
public class RestBaseRepository {

    /**
     * Holds information for connecting to the api.
     */
    private final ApiConnector apiConnector;

    /**
     * This should remain https.
     */
    private String protocol = "https://";

    private final ResponseHandler<String> responseHandler = (final HttpResponse response) -> {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    };

    /**
     * Initialize the repos with the api connection information.
     */
    public RestBaseRepository(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    /**
     * Performs a get request on the api with the correct token.
     *
     * @param uri
     * @return
     */
    public String get(String uri) throws IOException {
        String url = protocol + apiConnector.getServer() + uri;
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Authorization", " Bearer " + apiConnector.getToken());
        String response;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            response = httpclient.execute(httpget, responseHandler);
        }
        return response;
    }

    /**
     * Performs a post request on the api with the correct token.
     *
     * @param uri
     * @param data
     * @return
     */
    public String post(String uri, List<NameValuePair> data) throws UnsupportedEncodingException, IOException {
        String url = protocol + apiConnector.getServer() + uri;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(data));
        httpPost.setHeader("Authorization", " Bearer  " + this.apiConnector.getToken());
        String response;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            response = httpclient.execute(httpPost, responseHandler);
        }
        return response;
    }

}
