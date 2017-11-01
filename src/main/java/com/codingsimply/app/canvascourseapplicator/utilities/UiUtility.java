package com.codingsimply.app.canvascourseapplicator.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Adam Saladino
 */
public class UiUtility {

    public static final String CONTROLLERS_PACKAGE = "/fxml/";

    public static Node getNodeFromFxml(String url) {
        try {
            Node node = new VBox();
            FXMLLoader fxmlLoader = new FXMLLoader(UiUtility.class.getResource(url));
            fxmlLoader.setRoot(node);
            fxmlLoader.load();
            return node;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Parent loadController(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(UiUtility.class.getResource(CONTROLLERS_PACKAGE + name + ".fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            return root;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Object loadControllerWithStage(String name, boolean modal) {
        try {
            FXMLLoader loader = new FXMLLoader(UiUtility.class.getResource(CONTROLLERS_PACKAGE + name + ".fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            Stage stage = new Stage();
            stage.setScene(root.getScene());
            if(modal) {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
            stage.show();
            return loader.getController();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResourceBundle getLanguage() throws IOException {
        String lang = Locale.getDefault().getDisplayLanguage();
        InputStream inputStream = UiUtility.class.getClassLoader().getResourceAsStream("language/messages_" + lang + ".properties");
        if (inputStream == null) {
            inputStream = UiUtility.class.getClassLoader().getResourceAsStream("language/messages_en.properties");
        }
        return new PropertyResourceBundle(inputStream);
    }
}
