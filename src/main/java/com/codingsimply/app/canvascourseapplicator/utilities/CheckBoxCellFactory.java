package com.codingsimply.app.canvascourseapplicator.utilities;

import com.codingsimply.app.canvascourseapplicator.data.models.Course;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

public class CheckBoxCellFactory implements Callback {
    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<Course, Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
    }
}