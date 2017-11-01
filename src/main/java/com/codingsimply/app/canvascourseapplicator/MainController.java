package com.codingsimply.app.canvascourseapplicator;

import com.codingsimply.app.canvascourseapplicator.utilities.CourseTableRow;
import com.codingsimply.app.canvascourseapplicator.utilities.UiUtility;
import com.codingsimply.app.canvascourseapplicator.core.services.TemplateService;
import com.codingsimply.app.canvascourseapplicator.data.models.Config;
import com.codingsimply.app.canvascourseapplicator.data.models.Course;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class MainController implements Initializable {

    @FXML
    protected TableView<Course> coursesTable;

    @FXML
    private TableColumn<Course, Boolean> checkBoxTableColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private ProgressBar progressBar;

    private final Alert alert = new Alert(AlertType.NONE);

    private final TemplateService templateService = TemplateService.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxTableColumn.setCellValueFactory(cellData -> cellData.getValue().getUpdateCourse());
        checkBoxTableColumn.setCellFactory(param -> new CheckBoxTableCell());
        coursesTable.setRowFactory(tableView -> new CourseTableRow(this));
        try {
            templateService.load();
        } catch (FileNotFoundException ex) {
            showConfigError(templateService.getConfigFile());
        }
    }

    private void loadCsvFile(String file) {
        updateProgress(-1);
        templateService.findAllCoursesAsync(file, (courses) -> {
            coursesTable.getItems().setAll(courses);
            updateStatus("Found: " + courses.size());
            updateProgress(0);
        });
    }

    @FXML
    protected void start(ActionEvent actionEvent) {
        updateStatus("Found: "
                + coursesTable.getItems().size()
                + " | Updating: "
                + coursesTable.getItems().stream().filter(course -> course.getUpdateCourse().get()).count());
        coursesTable.setEditable(false);
        startButton.setDisable(true);
        stopButton.setDisable(false);

        templateService.applyTemplateToCoursesAsync(coursesTable.getItems(), (course, index) -> {
            updateProgress((double) index / coursesTable.getItems().size());
        }, (course, index) -> {
            updateProgress((double) index / coursesTable.getItems().size());
        }, message -> {
            progressBar.setProgress(0);
            showDialog("Server Connection",
                    "Could not connect to\n -" + message + "\nCheck connection or token.",
                    AlertType.ERROR);
        }, message -> {
            progressBar.setProgress(0);
            showConfigError(message);
        }, () -> {
            coursesTable.setEditable(true);
            startButton.setDisable(false);
            stopButton.setDisable(true);
            progressBar.setProgress(0);
        });
    }

    public void openCourseUrl(Course course) {
        templateService.searchCoursesAsync(course, courseResult -> {
            Platform.runLater(() -> {
                alert.close();
                Config config = templateService.getConfig();
                String url = "https://" + config.getServer() + "/courses/" + courseResult.getId();
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }, ex -> {
            Platform.runLater(() -> {
                showDialog("Error Course...",
                "Error looking up course, " + course.getCourseId() + " and navigating to page.",
                AlertType.ERROR);
            });
        });
        showDialog("Finding Course...",
                "Looking up course, " + course.getCourseId() + " and navigating to page.",
                AlertType.INFORMATION);
    }

    @FXML
    protected void stop(ActionEvent actionEvent) {
        templateService.setIsApplyTemplateToCourses(false);
    }

    @FXML
    protected void about(ActionEvent actionEvent) {
        AboutController aboutController = (AboutController) UiUtility.loadControllerWithStage("about", true);
        aboutController.load();
    }

    private void showConfigError(String file) {
        showDialog("Config File Access.",
                "Could not access " + file + ". Check please check file permissions.",
                AlertType.ERROR);
    }

    private void showDialog(String header, String message, AlertType alertType) {
        Platform.runLater(() -> {
            alert.setAlertType(alertType);
            alert.setTitle(header);
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.show();
        });
    }

    @FXML
    protected void preferences(ActionEvent actionEvent) {
        Config config = templateService.getConfig();
        PreferencesController preferencesController = (PreferencesController) UiUtility.loadControllerWithStage("preferences", true);
        preferencesController.load(config).onSave(cf -> {
            try {
                templateService.saveConfig(config);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    protected void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void updateProgress(double value) {
        Platform.runLater(() -> {
            progressBar.setProgress(value);
        });
    }

    private void updateStatus(String status) {
        Platform.runLater(() -> {
            statusLabel.setText(status);
        });
    }

    @FXML
    protected void fileDraggedOver(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        } else {
            event.consume();
        }
    }

    @FXML
    protected void fileDroppedOn(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            for (File file : db.getFiles()) {
                loadCsvFile(file.getAbsolutePath());
                break;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
