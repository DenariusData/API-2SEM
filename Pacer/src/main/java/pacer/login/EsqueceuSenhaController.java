package pacer.login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import pacer.data.dao.AlunoDAO;
import pacer.data.models.Aluno;
import pacer.utils.mbox;
import pacer.utils.sceneSwitcher;

public class EsqueceuSenhaController {

    @FXML
    private TextField txtRA;

    @FXML
    public void initialize() {
        txtRA.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtRA.setText(oldValue); 
            } else if (newValue.length() > 13) {
                mbox.ShowMessageBox(Alert.AlertType.WARNING, "Atenção", "O RA deve ter no máximo 13 dígitos.");
                txtRA.setText(oldValue); 
            }
        });
    }
    

    @FXML
    private void handleEnviarRA(ActionEvent event) throws IOException {
        String ra = txtRA.getText();
        if (ra.isEmpty()) {
            mbox.ShowMessageBox(Alert.AlertType.WARNING, "Atenção", "Por favor, insira seu RA.");
            return;
        }
        // Simulação de envio
        mbox.ShowMessageBox(
            Alert.AlertType.INFORMATION, 
            "Sucesso", 
            "RA enviado: " + ra + "\n\n" + 
            "Se você estiver cadastrado no sistema, será redirecionado."
        );

        Aluno aluno = AlunoDAO.getAlunosByRA(Long.parseLong(ra));
        if(aluno != null){
            RedefinirSenhaController controller = sceneSwitcher.switchSceneRetController("/FXML/RedefinirSenhaView.fxml", event);
            controller.configureAlunoEsqueceuSenha(aluno);
        }
        
        // Limpar o campo após o envio
        txtRA.clear();
    }
    @FXML
    private void handleVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/LoginView.fxml", event);
    }
}
