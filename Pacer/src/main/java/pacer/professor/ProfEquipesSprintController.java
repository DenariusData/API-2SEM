package pacer.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Grupo;
import pacer.data.models.Sprint;
import pacer.utils.sceneSwitcher;

public class ProfEquipesSprintController implements Initializable {
    @FXML
    private ComboBox<Sprint> cmbSprint;

    private Sprint sprintSelecionada;
    private Grupo grupoSelecionado;
    private Button btn;
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
                ProfEquipesController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfEquipesView.fxml", event);
                controller.gerarRelatorio(grupoSelecionado, sprintSelecionada);
            }
            case "Pontos" -> {
                ProfEquipesPontosController controllerPontos = sceneSwitcher.switchSceneRetController("/FXML/ProfEquipesPontosView.fxml", event);
                controllerPontos.selectSprint(grupoSelecionado, sprintSelecionada);
            }
        }
    }
    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }
}