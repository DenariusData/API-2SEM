package pacer.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Grupo;
import pacer.data.models.Sprint;
import pacer.utils.sceneSwitcher;

public class ProfEquipesSprintController implements Initializable {
    @FXML
    private ComboBox<Sprint> cmbSprint;

    private Sprint sprintSelecionada;
    private Grupo grupoSelecionado;
    private String req;

    public void selectGrupo(Grupo grupo, String req) {
        grupoSelecionado = grupo;
        this.req = req;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Sprint> sprints = FXCollections.observableArrayList(SprintDAO.getAllSprints());
        cmbSprint.setItems(sprints);
    }
    @FXML
    public void selectSprint() {
        sprintSelecionada = cmbSprint.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void handleConcluir(ActionEvent event) throws IOException {
        switch(req)
        {
            case "Relatorio" -> {
                Stage stage = (Stage) cmbSprint.getScene().getWindow();
                ProfEquipesController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfEquipesView.fxml", event);
                controller.gerarRelatorio(grupoSelecionado, sprintSelecionada, stage);
                break;
            }
            case "Pontos" -> {
                ProfEquipesPontosController controllerPontos;
                controllerPontos = sceneSwitcher.switchSceneRetController("/FXML/ProfEquipesPontosView.fxml", event);
                controllerPontos.selectSprint(grupoSelecionado, sprintSelecionada);
                break;
            }

            case "VerPontos" -> {
                ProfVisualizarPontosController controllerVerPontos = sceneSwitcher.switchSceneRetController("/FXML/ProfVisualizarPontosView.fxml", event);
                controllerVerPontos.selectSprint(grupoSelecionado, sprintSelecionada);
                break;
            }
        }
    }
    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) cmbSprint.getScene().getWindow();
        stage.close();
    }
}
