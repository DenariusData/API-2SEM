package pacer.aluno;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlunoAvaliacaoController implements Initializable {
    public Button realizarAvaliacaoBtn; 
    public void abrirRealizarAvaliacao(ActionEvent event) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AlunoRealizarAvalView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Realizar Avaliação");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmarVoltar(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Tem certeza que deseja voltar?");
        alert.setContentText("Clique em 'OK' para confirmar ou 'Cancelar' para retornar.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("Usuário confirmou voltar.");

                Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }
    public void setupTooltips() {
        Tooltip tooltipAutonomia = new Tooltip("Definição de autonomia.");
        Tooltip tooltipColaboracao = new Tooltip("Definição de colaboração.");
        Tooltip tooltipEntrega = new Tooltip("Definição de entrega.");
        Tooltip tooltipResultados = new Tooltip("Definição de resultados.");
        Tooltip tooltipStatus = new Tooltip("Definição de status.");

        colAutonomia.setTooltip(tooltipAutonomia);
        colColaboracao.setTooltip(tooltipColaboracao);
        colEntrega.setTooltip(tooltipEntrega);
        colResultados.setTooltip(tooltipResultados);
        colStatus.setTooltip(tooltipStatus);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTooltips();
    }
}
