package com.codingsimply.app.canvascourseapplicator.data.models;

/**
 * Describes the canvas course. See canvas api for more information. We are just
 * interested in the sis_canvas_id.
 *
 * @author Adam Saladino
 */
public class CourseResult {

    private String id;
    private String name;
    private String account_id;
    private String start_at;
    private String grading_standard_id;
    private String is_public;
    private String course_code;
    private String default_view;
    private String enrollment_term_id;
    private String end_at;
    private String public_syllabus;
    private String storage_quota_mb;
    private String is_public_to_auth_users;
    private String apply_assignment_group_weights;

    /**
     * Use to make sure the correct course is getting migrated too.
     */
    private String sis_course_id;
    private String integration_id;
    private String hide_final_grades;
    private String workflow_statepublic;
    private String restrict_enrollments_to_course_dates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getGrading_standard_id() {
        return grading_standard_id;
    }

    public void setGrading_standard_id(String grading_standard_id) {
        this.grading_standard_id = grading_standard_id;
    }

    public String getIs_public() {
        return is_public;
    }

    public void setIs_public(String is_public) {
        this.is_public = is_public;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getDefault_view() {
        return default_view;
    }

    public void setDefault_view(String default_view) {
        this.default_view = default_view;
    }

    public String getEnrollment_term_id() {
        return enrollment_term_id;
    }

    public void setEnrollment_term_id(String enrollment_term_id) {
        this.enrollment_term_id = enrollment_term_id;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public String getPublic_syllabus() {
        return public_syllabus;
    }

    public void setPublic_syllabus(String public_syllabus) {
        this.public_syllabus = public_syllabus;
    }

    public String getStorage_quota_mb() {
        return storage_quota_mb;
    }

    public void setStorage_quota_mb(String storage_quota_mb) {
        this.storage_quota_mb = storage_quota_mb;
    }

    public String getIs_public_to_auth_users() {
        return is_public_to_auth_users;
    }

    public void setIs_public_to_auth_users(String is_public_to_auth_users) {
        this.is_public_to_auth_users = is_public_to_auth_users;
    }

    public String getApply_assignment_group_weights() {
        return apply_assignment_group_weights;
    }

    public void setApply_assignment_group_weights(String apply_assignment_group_weights) {
        this.apply_assignment_group_weights = apply_assignment_group_weights;
    }

    /**
     * Use to make sure the correct course is getting migrated too.
     */
    public String getSis_course_id() {
        return sis_course_id;
    }

    /**
     * Use to make sure the correct course is getting migrated too.
     */
    public void setSis_course_id(String sis_course_id) {
        this.sis_course_id = sis_course_id;
    }

    public String getIntegration_id() {
        return integration_id;
    }

    public void setIntegration_id(String integration_id) {
        this.integration_id = integration_id;
    }

    public String getHide_final_grades() {
        return hide_final_grades;
    }

    public void setHide_final_grades(String hide_final_grades) {
        this.hide_final_grades = hide_final_grades;
    }

    public String getWorkflow_statepublic() {
        return workflow_statepublic;
    }

    public void setWorkflow_statepublic(String workflow_statepublic) {
        this.workflow_statepublic = workflow_statepublic;
    }

    public String getRestrict_enrollments_to_course_dates() {
        return restrict_enrollments_to_course_dates;
    }

    public void setRestrict_enrollments_to_course_dates(String restrict_enrollments_to_course_dates) {
        this.restrict_enrollments_to_course_dates = restrict_enrollments_to_course_dates;
    }
}
