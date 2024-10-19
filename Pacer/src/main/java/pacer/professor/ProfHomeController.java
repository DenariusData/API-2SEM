package pacer.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfHomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    private void handlepnlBotaocriteriosClick(ActionEvent event) throws IOException {
        // Carregar a nova tela (substitua o caminho pelo FXML correto)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfCriteriosView.fxml"));
        Parent root = loader.load();

        // Obter a janela (Stage) atual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Definir a nova cena no Stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        // Exibir a nova tela
        stage.show();
    }
}
