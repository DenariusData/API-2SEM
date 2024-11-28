package pacer.login;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.ProfessorDAO;
import pacer.utils.mbox;
import pacer.utils.sceneSwitcher;

public class LoginController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ToggleButton togglePasswordVisibilityButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void togglePasswordVisibility(){
        if (togglePasswordVisibilityButton.isSelected()) {
            // Altera para texto visível
            passwordField.setPromptText(passwordField.getText());
            passwordField.setText("");
            passwordField.setStyle("-fx-border-radius: 50; -fx-border-color: black;");
            passwordField.setDisable(true);
        } else {
            // Volta para senha oculta
            passwordField.setDisable(false);
            passwordField.setText(passwordField.getPromptText());
            passwordField.setPromptText("Digite sua senha...");
            passwordField.setStyle("-fx-border-radius: 50; -fx-border-color: black;");
        }
    }

    @FXML
    private void handleLogin(javafx.event.ActionEvent event) throws IOException {
        String email = emailField.getText();
        String senha = passwordField.getText();
        String fxmlPath;

        if (AlunoDAO.findByEmailWithNullSenha(email) != null) {
            mbox.ShowMessageBox(AlertType.WARNING, "Erro ao efetuar login.", "Usuário sem senha definida.");
            return;
        }

        // se aluno nao é nulo, portanto o email e senha digitados correspondem a um aluno (o mesmo vale pro professor)
        if (AlunoDAO.findByEmailAndSenha(email, senha) != null ) {
            fxmlPath = "/FXML/AlunoHomeView.fxml";
        }
        else if (ProfessorDAO.findByEmailAndSenha(email, senha) != null) {
            fxmlPath = "/FXML/ProfHomeView.fxml";
        }
        // se nao é nenhum dos dois emite uma mensagem de credenciais invalidas
        else {
            mbox.ShowMessageBox(AlertType.WARNING, "Erro ao efetuar login.", "Usuário ou senha incorretos.");
            return;
        }

        // função que abre a tela
        sceneSwitcher.switchScene(fxmlPath, event);
    }
    @FXML
    private void fecharSistema(javafx.event.ActionEvent event) throws IOException {
        // Criação do alerta de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de saída do sistema");
        alert.setHeaderText("Tem certeza de que deseja sair?");
        alert.setContentText("Você fechará a tela do sistema.");
    
        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonSim, buttonNao);
    
        // Exibe o alerta e aguarda a resposta
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonSim) {
            // Fecha o aplicativo
            Platform.exit();
        } else {
            // Se o usuário clicar em "Não", fecha o alerta
            alert.close();
        }
    }
    
    @FXML
    private void handleesqueceuSenha(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/FXML/EsqueceuSenhaView.fxml"));
        Parent root = loader.load();
        var scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}