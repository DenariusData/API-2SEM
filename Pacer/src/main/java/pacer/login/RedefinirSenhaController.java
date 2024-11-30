package pacer.login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import pacer.data.dao.AlunoDAO;
import pacer.data.models.Aluno;
import pacer.utils.mbox;

public class RedefinirSenhaController {

    @FXML
    private PasswordField txtNovaSenha;
    @FXML
    private PasswordField txtConfirmarSenha;
    @FXML
    private Label lblMensagem;
   @FXML
   private ToggleButton togglePasswordVisibilityButton;
   @FXML
   private ToggleButton togglePasswordVisibilityButton2;

   private Aluno aluno;

    public void configureAlunoEsqueceuSenha(Aluno aluno) {
        this.aluno = aluno;
    }

    @FXML
    private void handleConfirmarSenha() {
        String novaSenha = txtNovaSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
            mbox.ShowError("Por favor, preencha todos os campos.");
            return;
        }

        if (!novaSenha.equals(confirmarSenha)) {
            mbox.ShowError("As senhas não coincidem. Tente novamente.");
            return;
        }

        if (novaSenha.length() < 8 || !novaSenha.matches(".*[0-9].*") || !novaSenha.matches(".*[a-zA-Z].*")) {
            mbox.ShowError("A senha deve ter pelo menos 8 caracteres e incluir números e letras.");
            return;
        }
        try {
            aluno.setSenha(novaSenha);
            AlunoDAO.updateAluno(aluno);
            mbox.ShowMessageBox(Alert.AlertType.INFORMATION, "Sucesso", "Senha redefinida com sucesso!");
        } catch (Exception e) {
        }
        

        txtNovaSenha.clear();
        txtConfirmarSenha.clear();
    }
    @FXML
    private void togglePasswordVisibility(){
        if (togglePasswordVisibilityButton.isSelected()) {
            // Altera para texto visível
            txtNovaSenha.setPromptText(txtNovaSenha.getText());
            txtNovaSenha.setText("");
            txtNovaSenha.setStyle("-fx-border-radius: 50; -fx-border-color: black;");
            txtNovaSenha.setDisable(true);
        } else {
            // Volta para senha oculta
            txtNovaSenha.setDisable(false);
            txtNovaSenha.setText(txtNovaSenha.getPromptText());
            txtNovaSenha.setPromptText("Digite sua senha...");
            txtNovaSenha.setStyle("-fx-border-radius: 50; -fx-border-color: black;");
        }
    }
    @FXML
    private void togglePasswordVisibility2(){
        if (togglePasswordVisibilityButton2.isSelected()) {
            // Altera para texto visível
            txtConfirmarSenha.setPromptText(txtConfirmarSenha.getText());
            txtConfirmarSenha.setText("");
            txtConfirmarSenha.setStyle("-fx-border-radius: 50; -fx-border-color: black;");
            txtConfirmarSenha.setDisable(true);
        } else {
            // Volta para senha oculta
            txtConfirmarSenha.setDisable(false);
            txtConfirmarSenha.setText(txtConfirmarSenha.getPromptText());
            txtConfirmarSenha.setPromptText("Digite sua senha...");
            txtConfirmarSenha.setStyle("-fx-border-radius: 50; -fx-border-color: black;");
        }
    }


    @FXML
    public void initialize() {
        lblMensagem.setText(""); // Limpa a mensagem ao inicializar
    }
}