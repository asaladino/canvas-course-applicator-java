package com.codingsimply.app.canvascourseapplicator.core.repositories;

import com.codingsimply.app.canvascourseapplicator.data.models.ContentMigration;
import com.google.gson.Gson;
import com.codingsimply.app.canvascourseapplicator.data.models.ApiConnector;
import com.codingsimply.app.canvascourseapplicator.data.models.CourseResult;
import com.codingsimply.app.canvascourseapplicator.data.models.MigrationOption;
import java.io.IOException;

/**
 * Interfaces with the canvas content migrations api.
 * https://canvas.instructure.com/doc/api/content_migrations.html#method.content_migrations.create
 *
 * @author Adam Saladino
 */
public class RestContentMigrationsRepository extends RestBaseRepository {

    public RestContentMigrationsRepository(ApiConnector apiConnector) {
        super(apiConnector);
    }

    /**
     * Update content on an existing course. 
     * 
     * curl 'https://<instance>.test.instructure.com/api/v1/courses/11319/content_migrations' \
     * -X POST \
     * -F 'migration_type=course_copy_importer' \
     * -F 'settings[source_course_id]=13995' \
     * -H 'Authorization: Bearer 1afdOAHlSCi9'
     * 
     * @param CourseResult $courseResult - is a canvas course with the correct canvas id.
     * @param MigrationOption $migrationOption - information about the migration.
     * @return ContentMigration - information about the content migration.
     */
    public ContentMigration update(CourseResult courseResult, MigrationOption migrationOption) throws IOException {
        String uri = "/api/v1/courses/" + courseResult.getId() + "/content_migrations";
        String json = post(uri, migrationOption.asPostFields());
        Gson gson = new Gson();
        return gson.fromJson(json, ContentMigration.class);
    }
}