package pacer.utils;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sceneSwitcher {

    public static void switchScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(sceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public static <T> T switchSceneRetController(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(sceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        
        return loader.getController(); 
    }
}
