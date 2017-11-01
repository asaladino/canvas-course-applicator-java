package com.codingsimply.app.canvascourseapplicator.data.models;

/**
 * This is returned from a content migration from the api.
 *
 * @author Adam Saladino
 */
public class Config {
    
    /**
     * Location of the csv file.
     */
    private String csv;

    /**
     * Course ID to migrate to the courses in the csv file.
     */
    private String sourceCourseId;

    /**
     * Where the courses are loaded.
     */
    private String accountId;

    /**
     * Domain for the canvas server.
     */
    private String server;

    /**
     * Token to access the canvas api.
     */
    private String token;

    /**
     * Location of the csv file.
     * @return the csv
     */
    public String getCsv() {
        return csv;
    }

    /**
     * Location of the csv file.
     * @param csv the csv to set
     */
    public void setCsv(String csv) {
        this.csv = csv;
    }

    /**
     * Course ID to migrate to the courses in the csv file.
     * @return the sourceCourseId
     */
    public String getSourceCourseId() {
        return sourceCourseId;
    }

    /**
     * Course ID to migrate to the courses in the csv file.
     * @param sourceCourseId the sourceCourseId to set
     */
    public void setSourceCourseId(String sourceCourseId) {
        this.sourceCourseId = sourceCourseId;
    }

    /**
     * Where the courses are loaded.
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Where the courses are loaded.
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Domain for the canvas server.
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * Domain for the canvas server.
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Token to access the canvas api.
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Token to access the canvas api.
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
