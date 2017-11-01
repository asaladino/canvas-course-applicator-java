package com.codingsimply.app.canvascourseapplicator.core.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.codingsimply.app.canvascourseapplicator.data.models.ApiConnector;
import com.codingsimply.app.canvascourseapplicator.data.models.CourseResult;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Searches for a course based on sis course id.
 * https://canvas.instructure.com/doc/api/accounts.html#method.accounts.courses_api
 *
 * @author Adam Saladino
 */
public class RestCoursesSearchRepository extends RestBaseRepository {

    public RestCoursesSearchRepository(ApiConnector apiConnector) {
        super(apiConnector);
    }

    /**
     * Search for a course based on sis course id.
     *
     * curl
     * https://<instance>.test.instructure.com/api/v1/accounts/1/courses?search_term=ACCT_201_601_2016SU
     * \ -H 'Authorization: Bearer 1adfAHlSCi9'
     *
     * @param int $account_id that has has the course.
     * @param string $search_term is the sis course id.
     * @return CourseResult for the course found or null.
     */
    public CourseResult find(String account_id, String search_term) throws IOException {
        String uri = "/api/v1/accounts/" + account_id + "/courses?search_term=" + search_term;
        String json = get(uri);
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<CourseResult>>() {}.getType();
        List<CourseResult> results = gson.fromJson(json, collectionType);
        if (!results.isEmpty()) {
            for (CourseResult courseResult : results) {
                if (search_term.equals(courseResult.getSis_course_id())) {
                    return courseResult;
                }
            }
        }
        return null;
    }

}
