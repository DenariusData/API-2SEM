package pacer.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleLogin(javafx.event.ActionEvent event) throws IOException {

        // TODO verificaçao de login com o LoginDAO
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AlunoHomeView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
