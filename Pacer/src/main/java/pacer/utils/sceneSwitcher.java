package pacer.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class sceneSwitcher {

    public static void switchScene(String fxmlPath, javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(sceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static <T> T switchSceneRetController(String fxmlPath, javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(sceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        return loader.getController();
    }

    public static <T> T openNewWindow(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(sceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();

        return loader.getController();
    }
}
