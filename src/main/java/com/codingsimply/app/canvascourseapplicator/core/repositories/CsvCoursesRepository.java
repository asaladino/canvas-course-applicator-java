package com.codingsimply.app.canvascourseapplicator.core.repositories;

import com.codingsimply.app.canvascourseapplicator.data.models.Course;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

/**
 * Loads all the courses from the csv file.
 *
 * @author Adam Saladino
 */
public class CsvCoursesRepository {

    /**
     * App configuration.
     *
     * @var Config
     */
    private final String config;

    /**
     * Create a repository with a config file.
     *
     * @param Config $config
     */
    public CsvCoursesRepository(String config) {
        this.config = config;
    }

    /**
     * Find all the courses in the csv file.
     *
     * @return Course[]
     */
    public List<Course> findAll() throws FileNotFoundException, IOException {
        List<Course> courses = new ArrayList();
        Reader in = new FileReader(config);
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setFieldDelimiter(',');
        Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration, Course.class).createDeserializer();
        deserializer.open(in);
        if (deserializer.hasNext()) {
            deserializer.next();
        }
        while (deserializer.hasNext()) {
            Course course = deserializer.next();
            courses.add(course);
        }
        deserializer.close(true);
        return courses;
    }
}
