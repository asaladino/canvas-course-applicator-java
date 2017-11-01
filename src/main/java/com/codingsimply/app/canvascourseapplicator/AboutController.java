package com.codingsimply.app.canvascourseapplicator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Adam Saladino
 */
public class AboutController implements Initializable {

    @FXML
    private Label titleLable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public AboutController load() {
        return this;
    }
    
    @FXML
    protected void save(ActionEvent actionEvent) {
        Stage stage = (Stage) titleLable.getScene().getWindow();
        stage.close();
    }
}
