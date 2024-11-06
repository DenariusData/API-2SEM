package pacer.professor;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pacer.data.models.Professor;
import pacer.utils.sceneSwitcher;

public class ProfHomeController implements Initializable {

    @FXML
    ImageView pnlFoto;
    @FXML
    ToggleButton btnEditarFoto; 
    @FXML
    Label pnlNome;
    @FXML
    Label pnlEmail;
    @FXML 
    Label pnlCurso;
    
    Professor logado;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logado = Professor.ProfessorLogado.getProfessor();
        pnlEmail.setText(logado.getEmail());
    }
    @FXML
    private void handleCriterios(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfCriteriosView.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleEquipes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfEquipesView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();        
    }
    @FXML
    private void handleSair(javafx.event.ActionEvent event) throws IOException {
        // Criação do alerta de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Logout");
        alert.setHeaderText("Tem certeza de que deseja sair?");
        alert.setContentText("Você será redirecionado para a tela de login.");
    
        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonSim, buttonNao);
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonSim) {
            
            sceneSwitcher.switchScene("/FXML/LoginView.fxml", event);
        } else {
            
            alert.close();
        }
    
    }

}
