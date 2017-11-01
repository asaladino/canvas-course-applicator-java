package com.codingsimply.app.canvascourseapplicator.utilities;

import com.codingsimply.app.canvascourseapplicator.MainController;
import com.codingsimply.app.canvascourseapplicator.data.models.Course;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;

/**
 * @author Adam Saladino
 */
public class CourseTableRow extends TableRow<Course> {
    
    private final MainController mainController;
    
    public CourseTableRow(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    protected void updateItem(Course course, boolean empty) {
        super.updateItem(course, empty);
        final String[] courseStyles = new String[]{"courseSelected", "courseUpdated", "courseError"};
        if (course != null && course.getUpdateCourse().get()) {
            setOnMouseReleased(mounseEvent -> {
                if(mounseEvent.getButton() == MouseButton.SECONDARY) {
                    final ContextMenu contextMenu = new ContextMenu();
                    MenuItem openUrlMenuItem = new MenuItem("Open in browser...");
                    contextMenu.getItems().add(openUrlMenuItem);
                    openUrlMenuItem.setOnAction(openEvent -> {
                        mainController.openCourseUrl(course);
                    });
                    contextMenu.show(this, mounseEvent.getScreenX(), mounseEvent.getScreenY());
                }
            });
            
            
            getStyleClass().clear();
            if (course.getUpdateCourse().get()) {
                getStyleClass().add("courseSelected");
            }
            if (course.getDidTryCourseUpdate().get()) {
                getStyleClass().add("courseTryUpdate");
            }

            if (course.getDidUpdateCourse().get()) {
                getStyleClass().add("courseUpdated");
            }
            if (course.getDidErrorOnUpdateCourse().get()) {
                getStyleClass().add("courseError");
            }

            course.getUpdateCourse().addListener(listener -> {
                getStyleClass().clear();
                if (course.getUpdateCourse().get()) {
                    getStyleClass().add("courseSelected");
                } else {
                    getStyleClass().remove("courseSelected");
                }
            });
            course.getDidTryCourseUpdate().addListener(listener -> {
                getTableView().scrollTo(course);
                if (course.getDidTryCourseUpdate().get()) {
                    getStyleClass().add("courseTryUpdate");
                } else {
                    getStyleClass().remove("courseTryUpdate");
                }
            });
            course.getDidUpdateCourse().addListener(listener -> {
                if (course.getDidUpdateCourse().get()) {
                    getStyleClass().add("courseUpdated");
                } else {
                    getStyleClass().remove("courseUpdated");
                }
            });
            course.getDidErrorOnUpdateCourse().addListener(listener -> {
                if (course.getDidErrorOnUpdateCourse().get()) {
                    getStyleClass().add("courseError");
                } else {
                    getStyleClass().remove("courseError");
                }
            });
        } else {
            getStyleClass().removeAll(courseStyles);
        }
    }
}
