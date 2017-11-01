package com.codingsimply.app.canvascourseapplicator.data.models;

import javafx.beans.property.SimpleBooleanProperty;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

/**
 * Describes the course to get templated.
 *
 * @author Adam Saladino
 */
@CsvDataType
public class Course {

    /**
     * Should the course be updated?
     */
    private SimpleBooleanProperty updateCourse = new SimpleBooleanProperty(true);
    
    /**
     * Did the course try to updated?
     */
    private SimpleBooleanProperty didTryCourseUpdate = new SimpleBooleanProperty(false);
    
    /**
     * Did the course get updated?
     */
    private SimpleBooleanProperty didUpdateCourse = new SimpleBooleanProperty(false); 
    
    /**
     * Did the course update error?
     */
    private SimpleBooleanProperty didErrorOnUpdateCourse = new SimpleBooleanProperty(false); 
    
    /**
     * SIS course id.
     */
    @CsvField(pos = 1)
    private String courseId;

    /**
     * Seems to be the same as the course id.
     */
    @CsvField(pos = 2)
    private String shortName;

    /**
     * Short name + the tile.
     */
    @CsvField(pos = 3)
    private String longName;

    /**
     * College the course belongs to: ACCT, ARTS, etc...
     */
    @CsvField(pos = 4)
    private String accountId;

    /**
     * Term the course is offered.
     */
    @CsvField(pos = 5)
    private String termId;

    /**
     * Status of the course, hopefully active.
     */
    @CsvField(pos = 6)
    private String status;

    /**
     * Date the course starts.
     */
    @CsvField(pos = 7)
    private String startDate;

    /**
     * Date the course ends.
     */
    @CsvField(pos = 8)
    private String endDate;

    /**
     * SIS course id.
     *
     * @return the courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * SIS course id.
     *
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Seems to be the same as the course id.
     *
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Seems to be the same as the course id.
     *
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Short name + the tile.
     *
     * @return the longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Short name + the tile.
     *
     * @param longName the longName to set
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * College the course belongs to: ACCT, ARTS, etc...
     *
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * College the course belongs to: ACCT, ARTS, etc...
     *
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Term the course is offered.
     *
     * @return the termId
     */
    public String getTermId() {
        return termId;
    }

    /**
     * Term the course is offered.
     *
     * @param termId the termId to set
     */
    public void setTermId(String termId) {
        this.termId = termId;
    }

    /**
     * Status of the course, hopefully active.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status of the course, hopefully active.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Date the course starts.
     *
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Date the course starts.
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Date the course ends.
     *
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Date the course ends.
     *
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Should the course be updated?
     * @return the updateCourse
     */
    public SimpleBooleanProperty getUpdateCourse() {
        return updateCourse;
    }

    /**
     * Should the course be updated?
     * @param updateCourse the updateCourse to set
     */
    public void setUpdateCourse(SimpleBooleanProperty updateCourse) {
        this.updateCourse = updateCourse;
    }

    /**
     * Did the course get updated?
     * @return the didUpdateCourse
     */
    public SimpleBooleanProperty getDidUpdateCourse() {
        return didUpdateCourse;
    }

    /**
     * Did the course get updated?
     * @param didUpdateCourse the didUpdateCourse to set
     */
    public void setDidUpdateCourse(SimpleBooleanProperty didUpdateCourse) {
        this.didUpdateCourse = didUpdateCourse;
    }

    /**
     * Did the course update error?
     * @return the didErrorOnUpdateCourse
     */
    public SimpleBooleanProperty getDidErrorOnUpdateCourse() {
        return didErrorOnUpdateCourse;
    }

    /**
     * Did the course update error?
     * @param didErrorOnUpdateCourse the didErrorOnUpdateCourse to set
     */
    public void setDidErrorOnUpdateCourse(SimpleBooleanProperty didErrorOnUpdateCourse) {
        this.didErrorOnUpdateCourse = didErrorOnUpdateCourse;
    }

    /**
     * Did the course try to updated?
     * @return the didTryCourseUpdate
     */
    public SimpleBooleanProperty getDidTryCourseUpdate() {
        return didTryCourseUpdate;
    }

    /**
     * Did the course try to updated?
     * @param didTryCourseUpdate the didTryCourseUpdate to set
     */
    public void setDidTryCourseUpdate(SimpleBooleanProperty didTryCourseUpdate) {
        this.didTryCourseUpdate = didTryCourseUpdate;
    }
}
