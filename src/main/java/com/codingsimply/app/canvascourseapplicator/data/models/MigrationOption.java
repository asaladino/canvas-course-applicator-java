package com.codingsimply.app.canvascourseapplicator.data.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Describes the content migration that will be performed.
 *
 * @author Adam Saladino
 */
public class MigrationOption {

    /**
     * This can be other options but course copy is default.
     */
    private String migrationType = "course_copy_importer";

    /**
     * There are a lot of options but we are only interest in source_course_id.
     */
    private Map<String, String> settings = new HashMap();

    /**
     * Initialize the migration option from a config.
     *
     * @param Config String config
     */
    public MigrationOption(Config config) {
        settings.put("source_course_id", config.getSourceCourseId());
    }

    /**
     * Get the parameters as post fields.
     *
     * @return parameters as post fields
     */
    public List<NameValuePair> asPostFields() {
        List<NameValuePair> fields = new ArrayList();
        fields.add(new BasicNameValuePair("migration_type", getMigrationType()));
        for (Map.Entry<String, String> entry : settings.entrySet()) {
            fields.add(new BasicNameValuePair("settings[" + entry.getKey() + "]", entry.getValue()));
        }
        return fields;
    }

    /**
     * This can be other options but course copy is default.
     *
     * @return the migrationType
     */
    public String getMigrationType() {
        return migrationType;
    }

    /**
     * This can be other options but course copy is default.
     *
     * @param migrationType the migrationType to set
     */
    public void setMigrationType(String migrationType) {
        this.migrationType = migrationType;
    }

    /**
     * There are a lot of options but we are only interest in source_course_id.
     *
     * @return the settings
     */
    public Map<String, String> getSettings() {
        return settings;
    }

    /**
     * There are a lot of options but we are only interest in source_course_id.
     *
     * @param settings the settings to set
     */
    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

}
