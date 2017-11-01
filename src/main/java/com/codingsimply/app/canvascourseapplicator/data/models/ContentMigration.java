package com.codingsimply.app.canvascourseapplicator.data.models;

/**
 * This is returned from a content migration from the api.
 *
 * @author Adam Saladino
 */
public class ContentMigration {

    /**
     * The unique identifier for the migration.
     */
    private String id;

    /**
     * The type of content migration.
     */
    private String migrationType;

    /**
     * The name of the content migration type.
     */
    private String migrationTypeTitle;

    /**
     * API url to the content migration's issues.
     */
    private String migrationIssuesUrl;

    /**
     * Attachment api object for the uploaded file may not be present for all 
     * migrations.
     */
    private String attachment;

    /**
     * The api endpoint for polling the current progress.
     */
    private String progressUrl;

    /**
     * The user who started the migration.
     */
    private String userId;

    /**
     * Current state of the content migration: pre_processing, pre_processed,
     * running, waiting_for_select, completed, failed.
     */
    private String workflowState;

    /**
     * Timestamp.
     */
    private String startedAt;

    /**
     * Timestamp.
     */
    private String finishedAt;

    /**
     * Tile uploading data, see {file:fileUploads.html File Upload Documentation} for
     * file upload workflow This works a little differently in that all the file data
     * is in the pre_attachment hash if there is no uploadUrl then there was an
     * attachment pre-processing error, the error message will be in the message key
     * This data will only be here after a create or update call
     */
    private String preAttachment;

    /**
     * The unique identifier for the migration.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * The unique identifier for the migration.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The type of content migration.
     * @return the migrationType
     */
    public String getMigrationType() {
        return migrationType;
    }

    /**
     * The type of content migration.
     * @param migrationType the migrationType to set
     */
    public void setMigrationType(String migrationType) {
        this.migrationType = migrationType;
    }

    /**
     * The name of the content migration type.
     * @return the migrationTypeTitle
     */
    public String getMigrationTypeTitle() {
        return migrationTypeTitle;
    }

    /**
     * The name of the content migration type.
     * @param migrationTypeTitle the migrationTypeTitle to set
     */
    public void setMigrationTypeTitle(String migrationTypeTitle) {
        this.migrationTypeTitle = migrationTypeTitle;
    }

    /**
     * API url to the content migration's issues.
     * @return the migrationIssuesUrl
     */
    public String getMigrationIssuesUrl() {
        return migrationIssuesUrl;
    }

    /**
     * API url to the content migration's issues.
     * @param migrationIssuesUrl the migrationIssuesUrl to set
     */
    public void setMigrationIssuesUrl(String migrationIssuesUrl) {
        this.migrationIssuesUrl = migrationIssuesUrl;
    }

    /**
     * Attachment api object for the uploaded file may not be present for all
     * migrations.
     * @return the attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * Attachment api object for the uploaded file may not be present for all
     * migrations.
     * @param attachment the attachment to set
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * The api endpoint for polling the current progress.
     * @return the progressUrl
     */
    public String getProgressUrl() {
        return progressUrl;
    }

    /**
     * The api endpoint for polling the current progress.
     * @param progressUrl the progressUrl to set
     */
    public void setProgressUrl(String progressUrl) {
        this.progressUrl = progressUrl;
    }

    /**
     * The user who started the migration.
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * The user who started the migration.
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Current state of the content migration: pre_processing, pre_processed,
     * running, waiting_for_select, completed, failed.
     * @return the workflowState
     */
    public String getWorkflowState() {
        return workflowState;
    }

    /**
     * Current state of the content migration: pre_processing, pre_processed,
     * running, waiting_for_select, completed, failed.
     * @param workflowState the workflowState to set
     */
    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    /**
     * Timestamp.
     * @return the startedAt
     */
    public String getStartedAt() {
        return startedAt;
    }

    /**
     * Timestamp.
     * @param startedAt the startedAt to set
     */
    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * Timestamp.
     * @return the finishedAt
     */
    public String getFinishedAt() {
        return finishedAt;
    }

    /**
     * Timestamp.
     * @param finishedAt the finishedAt to set
     */
    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    /**
     * Tile uploading data, see {file:fileUploads.html File Upload Documentation} for
     * file upload workflow This works a little differently in that all the file data
     * is in the pre_attachment hash if there is no uploadUrl then there was an
     * attachment pre-processing error, the error message will be in the message key
     * This data will only be here after a create or update call
     * @return the preAttachment
     */
    public String getPreAttachment() {
        return preAttachment;
    }

    /**
     * Tile uploading data, see {file:fileUploads.html File Upload Documentation} for
     * file upload workflow This works a little differently in that all the file data
     * is in the pre_attachment hash if there is no uploadUrl then there was an
     * attachment pre-processing error, the error message will be in the message key
     * This data will only be here after a create or update call
     * @param preAttachment the preAttachment to set
     */
    public void setPreAttachment(String preAttachment) {
        this.preAttachment = preAttachment;
    }
}
