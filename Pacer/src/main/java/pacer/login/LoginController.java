package pacer.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.ProfessorDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Professor;
import pacer.utils.mbox;
import pacer.utils.sceneSwitcher;

public class LoginController implements Initializable {

     @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleLogin(javafx.event.ActionEvent event) throws IOException {
        String email = emailField.getText();
        String senha = passwordField.getText();
        String fxmlPath;

        
        // verifica se é um aluno (se nao for se torna null)
        Aluno aluno = AlunoDAO.findByEmailAndSenha(email, senha);
        // verifica se é um professor (se nao for se torna null)
        Professor professor = ProfessorDAO.findByEmailAndSenha(email, senha);

        // se aluno nao é nulo, portanto o email e senha digitados correspondem a um aluno (o mesmo vale pro professor)
        if (aluno != null ) {
            fxmlPath = "/FXML/AlunoHomeView.fxml";
        }
        else if (professor != null) {
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
}
