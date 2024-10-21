package pacer.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sceneSwitcher {

    public static void switchScene(String fxmlPath, javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(sceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
