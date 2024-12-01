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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Sprint;
import pacer.data.models.Grupo;
import pacer.utils.sceneSwitcher;

public class ProfEquipesRelatorioController implements Initializable {
    @FXML
    private ComboBox<Sprint> cmbSprint;

    private Sprint sprintSelecionada;
    private Grupo grupoSelecionado;
    private Button btn;

    public void selectGrupo(Grupo grupo) {
        grupoSelecionado = grupo;
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
    public void baixarRelatorio(ActionEvent event) throws IOException {
        ProfEquipesController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfEquipesView.fxml", event);
        controller.gerarRelatorio(grupoSelecionado, sprintSelecionada);
    }
    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }
}
