package pacer.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pacer.data.dao.PontosDAO;
import pacer.data.models.Grupo;
import pacer.data.models.Pontos;
import pacer.data.models.Sprint;

public class ProfVisualizarPontosController implements Initializable {
    @FXML
    private TextField txtPontos;

    @FXML
    private Label lblGrupo;
    @FXML
    private Label lblSprint;
    @FXML
    private Label lblPontosAtuais;
    @FXML
    private Label lblPontosDefinidos;

    private Grupo grupoSelecionado;
    private Sprint sprintSelecionada;
    private Pontos pontosSelecionado;

    public void selectSprint(Grupo grupo, Sprint sprint) {
        sprintSelecionada = sprint;
        grupoSelecionado = grupo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Thread tCarregaLabels = new Thread(() -> {
                Platform.runLater(() -> {
                    Pontos pontos = new Pontos(sprintSelecionada.getSprintId(), grupoSelecionado.getId());
                    pontosSelecionado = PontosDAO.getPontosBySprintAndGrupo(pontos);
                    lblGrupo.setText("Grupo: " + grupoSelecionado.getNome());
                    lblSprint.setText("Sprint: " + String.valueOf(sprintSelecionada.getSprint()));
                    try {
                        lblPontosDefinidos.setText("Pontos Definidos: " + String.valueOf(pontosSelecionado.getPontosIniciais()));
                        lblPontosAtuais.setText("Pontos Atuais: " + String.valueOf(pontosSelecionado.getPontosAtuais()));
                    } catch(Exception e) {
                        e.printStackTrace();
                    }   
                });
            });
            tCarregaLabels.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    @FXML
    public void handleOk(ActionEvent event) throws IOException {
        Stage stage = (Stage) lblGrupo.getScene().getWindow();
        stage.close();
    }
}
