package com.codingsimply.app.canvascourseapplicator.core.services;

import com.codingsimply.app.canvascourseapplicator.core.repositories.CsvCoursesRepository;
import com.codingsimply.app.canvascourseapplicator.core.repositories.JsonConfigRepository;
import com.codingsimply.app.canvascourseapplicator.core.repositories.RestContentMigrationsRepository;
import com.codingsimply.app.canvascourseapplicator.core.repositories.RestCoursesSearchRepository;
import com.codingsimply.app.canvascourseapplicator.data.models.ApiConnector;
import com.codingsimply.app.canvascourseapplicator.data.models.Config;
import com.codingsimply.app.canvascourseapplicator.data.models.MigrationOption;
import com.codingsimply.app.canvascourseapplicator.core.services.ServiceTask.Todo;
import com.codingsimply.app.canvascourseapplicator.data.models.Course;
import com.codingsimply.app.canvascourseapplicator.data.models.CourseResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * The update service performs the following tasks: 1) Reads the config file. 2)
 * Build migration options from config. 3) Find all the courses in the csv file.
 * 4) Loop through the courses: 1) Get the canvas course id for the course. 2)
 * If the course exists in canvas then migrate information from course listed in
 * config file.
 *
 * @author Adam Saladino
 */
public class TemplateService {

    private static TemplateService instance;

    public interface ITemplateServiceListeners {

        @FunctionalInterface
        public interface IFindAllCourses {

            public void complete(List<Course> courses);
        }

        @FunctionalInterface
        public interface IApplyTemplateToCoursesSuccess {

            public void message(Course course, int index);
        }

        @FunctionalInterface
        public interface IApplyTemplateToCoursesError {

            public void message(Course course, int index);
        }

        @FunctionalInterface
        public interface IApplyTemplateToCoursesComplete {

            public void message();
        }

        @FunctionalInterface
        public interface ISearchCoursesComplete {

            public void complete(CourseResult courseResult);
        }

        @FunctionalInterface
        public interface ISearchCoursesError {

            public void error(IOException ex);
        }

        @FunctionalInterface
        public interface IConfigFileLoadError {

            public void message(String file);
        }

        @FunctionalInterface
        public interface IServerConnectionError {

            public void message(String server);
        }
    }

    /**
     * Configuration information for the app.
     */
    private Config config;

    /**
     * Location of the config file.
     */
    private String configFile;

    /**
     * Information for connecting to the canvas api.
     */
    private ApiConnector apiConnector;

    /**
     * Gets information from the config file.
     */
    private JsonConfigRepository jsonConfigRepository;

    /**
     * Reads all the courses from the csv.
     */
    private CsvCoursesRepository csvCoursesRepository;

    /**
     * Updates the course through the canvas api.
     */
    private RestContentMigrationsRepository restContentMigrationsRepository;

    /**
     * Searches for a course through the canvas api.
     */
    private RestCoursesSearchRepository restCoursesSearchRepository;

    private Thread applyTemplateThread;
    private Thread findAllCoursesThread;
    private Thread searchCoursesThread;

    private ServiceTask applyTemplateTask;
    private ServiceTask findAllCoursesTask;
    private ServiceTask searchCoursesTask;

    /**
     * Set to false to stop the application of templates to courses.
     */
    private final AtomicBoolean isApplyTemplateToCourses = new AtomicBoolean(true);

    /**
     * Initialize the template service with the config file.
     *
     * @param string configFile
     */
    private TemplateService() {
    }

    public static TemplateService getInstance() {
        if (instance == null) {
            instance = new TemplateService();
        }
        return instance;
    }

    /**
     * Load all the repositories.
     */
    public void load() throws FileNotFoundException {
        loadConfig();
        initRepos();
    }
    
    private void initRepos() {
        apiConnector = new ApiConnector(getConfig());
        restContentMigrationsRepository = new RestContentMigrationsRepository(apiConnector);
        restCoursesSearchRepository = new RestCoursesSearchRepository(apiConnector);
    }

    private void loadConfig() throws FileNotFoundException {
        setConfigFile(System.getProperty("user.dir") + File.separator + "config.json");
        jsonConfigRepository = new JsonConfigRepository(getConfigFile());
        try {
            setConfig(jsonConfigRepository.read());
        } catch (FileNotFoundException ex) {
            setConfig(new Config());
            jsonConfigRepository.write(getConfig());
        }
    }

    public void saveConfig(Config config) throws FileNotFoundException {
        this.config = config;
        jsonConfigRepository.write(config);
        initRepos();
    }

    public List<Course> findAllCourses(String configFile) throws IOException {
        csvCoursesRepository = new CsvCoursesRepository(configFile);
        List<Course> courses = csvCoursesRepository.findAll();
        return courses;
    }

    /**
     * Apply the course template to the courses in the csv file.
     */
    public void applyTemplateToCourses(ObservableList<Course> courses,
            ITemplateServiceListeners.IApplyTemplateToCoursesSuccess success,
            ITemplateServiceListeners.IApplyTemplateToCoursesError error,
            ITemplateServiceListeners.IApplyTemplateToCoursesComplete complete) throws IOException {
        MigrationOption migrationOption = new MigrationOption(getConfig());
        int index = 1;
        getIsApplyTemplateToCourses().set(true);
        courses.stream().filter(course -> {
            return course.getUpdateCourse().get();
        }).forEach(course -> {
            course.getDidTryCourseUpdate().set(false);
            course.getDidUpdateCourse().set(false);
            course.getDidErrorOnUpdateCourse().set(false);
            course.getUpdateCourse().set(true);
        });
        for (Course course : courses) {
            if (!isApplyTemplateToCourses.get()) {
                break;
            }
            if (course.getUpdateCourse().get()) {
                course.getDidTryCourseUpdate().set(true);
                CourseResult courseResult = restCoursesSearchRepository.find(getConfig().getAccountId(), course.getCourseId());
                if (courseResult != null) {
                    restContentMigrationsRepository.update(courseResult, migrationOption);
                    course.getDidUpdateCourse().set(true);
                    success.message(course, index);
                } else {
                    course.getDidErrorOnUpdateCourse().set(true);
                    error.message(course, index);
                }
            }
            index++;
        }
        complete.message();
    }

    public CourseResult searchCourses(Course course) throws IOException {
        CourseResult courseResult = restCoursesSearchRepository.find(getConfig().getAccountId(), course.getCourseId());
        return courseResult;
    }

    public Task<Void> searchCoursesAsync(Course course,
            ITemplateServiceListeners.ISearchCoursesComplete success,
            ITemplateServiceListeners.ISearchCoursesError error) {
        searchCoursesTask = startThread(searchCoursesThread, searchCoursesTask, () -> {
            try {
                CourseResult courseResult = searchCourses(course);
                success.complete(courseResult);
            } catch (IOException ex) {
                error.error(ex);
            }
        });
        return searchCoursesTask;
    }

    public Task<Void> findAllCoursesAsync(String configFile, ITemplateServiceListeners.IFindAllCourses listener) {
        findAllCoursesTask = startThread(findAllCoursesThread, findAllCoursesTask, () -> {
            try {
                List<Course> courses = findAllCourses(configFile);
                listener.complete(courses);
            } catch (IOException ex) {
                Logger.getLogger(TemplateService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return findAllCoursesTask;
    }

    public Task<Void> applyTemplateToCoursesAsync(ObservableList<Course> courses,
            ITemplateServiceListeners.IApplyTemplateToCoursesSuccess success,
            ITemplateServiceListeners.IApplyTemplateToCoursesError error,
            ITemplateServiceListeners.IServerConnectionError serverConnectionError,
            ITemplateServiceListeners.IConfigFileLoadError configFileLoadError,
            ITemplateServiceListeners.IApplyTemplateToCoursesComplete complete) {
        applyTemplateTask = startThread(applyTemplateThread, applyTemplateTask, () -> {
            try {
                load();
                applyTemplateToCourses(courses, success, error, complete);
            } catch (FileNotFoundException ex) {
                configFileLoadError.message(getConfigFile());
            } catch (IOException ex) {
                serverConnectionError.message(config.getServer());
            }
        });
        return applyTemplateTask;
    }

    public static void startThread(Thread thread, Task task) {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(task);
            thread.start();
        }
    }

    public static ServiceTask startThread(Thread thread, ServiceTask serviceTask, Todo todo) {
        if (serviceTask == null || serviceTask.isDone()) {
            serviceTask = new ServiceTask(todo);
            startThread(thread, serviceTask);
        }
        return serviceTask;
    }

    /**
     * Configuration information for the app.
     *
     * @return the config
     */
    public Config getConfig() {
        return config;
    }

    /**
     * Configuration information for the app.
     *
     * @param config the config to set
     */
    public void setConfig(Config config) {
        this.config = config;
    }

    /**
     * Location of the config file.
     *
     * @return the configFile
     */
    public String getConfigFile() {
        return configFile;
    }

    /**
     * Location of the config file.
     *
     * @param configFile the configFile to set
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Set to false to stop the application of templates to courses.
     *
     * @return the isApplyTemplateToCourses
     */
    public AtomicBoolean getIsApplyTemplateToCourses() {
        return isApplyTemplateToCourses;
    }

    /**
     * Set to false to stop the application of templates to courses.
     *
     * @param isApplyTemplateToCourses the isApplyTemplateToCourses to set
     */
    public void setIsApplyTemplateToCourses(boolean isApplyTemplateToCourses) {
        this.isApplyTemplateToCourses.set(isApplyTemplateToCourses);
    }

}
