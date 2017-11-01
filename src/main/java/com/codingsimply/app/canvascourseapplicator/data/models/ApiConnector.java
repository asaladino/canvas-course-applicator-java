package com.codingsimply.app.canvascourseapplicator.data.models;

/**
 * Canvas API connector information.
 *
 * @author Adam Saladino
 */
public class ApiConnector {

    /**
     * Domain name of the canvas instance.
     */
    private String server;

    /**
     * Canvas api access token.
     */
    private String token;

    /**
     * Initialize an api connector from a config.
     *
     * @param Config String config to build from.
     */
    public ApiConnector(Config config) {
        this.server = config.getServer();
        this.token = config.getToken();
    }

    /**
     * Domain name of the canvas instance.
     *
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * Domain name of the canvas instance.
     *
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Canvas api access token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Canvas api access token.
     *
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
