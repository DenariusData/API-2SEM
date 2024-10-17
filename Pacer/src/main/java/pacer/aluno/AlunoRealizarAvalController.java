package pacer.aluno;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class AlunoRealizarAvalController {

    @FXML
    private Button enviarAvaliacaoBtn;

    @FXML
    public void enviarAvaliacao() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Avaliação Enviada");
        alert.setHeaderText(null);
        alert.setContentText("Enviado com sucesso!");
        alert.showAndWait();
    }
}