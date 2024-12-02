package pacer.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pacer.data.dao.PontosDAO;
import pacer.data.models.Grupo;
import pacer.data.models.Pontos;
import pacer.data.models.Sprint;
import pacer.utils.mbox;

public class ProfEquipesPontosController implements Initializable {
    @FXML
    private TextField txtPontos;

    @FXML
    private Label lblGrupo;
    @FXML
    private Label lblSprint;

    private Grupo grupoSelecionado;
    private Sprint sprintSelecionada;

    public void selectSprint(Grupo grupo, Sprint sprint) {
        sprintSelecionada = sprint;
        grupoSelecionado = grupo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Thread tCarregaLabels = new Thread(() -> {
                Platform.runLater(() -> {
                    lblGrupo.setText("Grupo: " + grupoSelecionado.getNome());
                    lblSprint.setText("Sprint: " + String.valueOf(sprintSelecionada.getSprint()));
                });
            });
            tCarregaLabels.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void enviarPontos(ActionEvent event) throws IOException {
        try{
            int pontosEscolhidos = Integer.parseInt(txtPontos.getText());
            Pontos pontos = new Pontos(pontosEscolhidos, sprintSelecionada.getSprintId(), grupoSelecionado.getId());
            if (PontosDAO.getPontosBySprintAndGrupo(pontos) != null) {
                mbox.ShowError("Pontos ja foram atribuitos para o grupo selecionado na sprint selecionada.");
                return;
            }
            PontosDAO.addPontos(pontos);
            mbox.ShowMessageBox(AlertType.INFORMATION, "Pontos", "Pontos foram atribuitos ao grupo selecionado.");
        } catch (Exception e) {
            mbox.ShowError("Não foi possível atribuir os pontos ao grupo selecionado");
            e.printStackTrace();
        } finally {
            cancelar(event);
        }
    }
        
    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) lblGrupo.getScene().getWindow();
        stage.close();
    }
}
