package com.codingsimply.app.canvascourseapplicator;

import com.codingsimply.app.canvascourseapplicator.data.models.Config;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Adam Saladino
 */
public class PreferencesController implements Initializable {

    @FXML
    private TextField accountIdTextField;
    @FXML
    private TextField serverTextField;
    @FXML
    private TextField sourceCourseIdTextField;
    @FXML
    private TextField tokenTextField;

    private Config config;
    
    private PreferencesControllerListener.ISave preferencesControllerListener;

    public interface PreferencesControllerListener {

        @FunctionalInterface
        public interface ISave {

            public void ready(Config config);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public PreferencesController load(Config config) {
        this.config = config;
        accountIdTextField.setText(config.getAccountId());
        serverTextField.setText(config.getServer());
        sourceCourseIdTextField.setText(config.getSourceCourseId());
        tokenTextField.setText(config.getToken());
        return this;
    }
    
    public void onSave(PreferencesControllerListener.ISave preferencesControllerListener) {
        this.preferencesControllerListener = preferencesControllerListener;
    }
    
    @FXML
    protected void openCourse(ActionEvent actionEvent) throws IOException, URISyntaxException {
        String url = "https://" + serverTextField.getText() + "/courses/" + sourceCourseIdTextField.getText();
        Desktop.getDesktop().browse(new URI(url));
    }

    @FXML
    protected void save(ActionEvent actionEvent) {
        config.setAccountId(accountIdTextField.getText());
        config.setServer(serverTextField.getText());
        config.setSourceCourseId(sourceCourseIdTextField.getText());
        config.setToken(tokenTextField.getText());
        preferencesControllerListener.ready(config);
        close();
    }

    @FXML
    protected void cancel(ActionEvent actionEvent) {
        close();
    }
    
    private void close() {
        Stage stage = (Stage) accountIdTextField.getScene().getWindow();
        stage.close();
    }
}
