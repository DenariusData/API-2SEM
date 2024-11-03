package pacer.professor;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import pacer.data.dao.GrupoDAO;
import pacer.data.models.Grupo;
import pacer.utils.sceneSwitcher;

public class ProfEquipesController {
    
    @FXML
    private TableView<Grupo> tblGrupos;

    @FXML
    private TableColumn<Grupo, String> colNomeGrupo;

    @FXML
    private TableColumn<Grupo, String> colReposLink;
    @FXML
    private TableColumn<Grupo, String> colSemestre;
    private Grupo grupoSelecionado;

    @FXML
    public void initialize() {
        colNomeGrupo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colReposLink.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReposLink()));
        colSemestre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSemestre()));
        colSemestre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCursoSigla()));

        carregarGrupos();
    }

    @FXML
    public void handleRowClickGrupos(MouseEvent event) {
        grupoSelecionado = tblGrupos.getSelectionModel().getSelectedItem();
    }


    @FXML
    public void confirmarVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfHomeView.fxml", event);
    }

    @FXML
    public void abrirImport(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfImportarView.fxml", event);
    }

    @FXML
    public void adicionarGrupo(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesAddEditView.fxml", event);
    }

    @FXML
    public void editarGrupo(ActionEvent event) throws IOException {
        if (grupoSelecionado != null) {
            ProfEquipesAddEditController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfEquipesAddEditView.fxml", event);
            controller.setGrupo(grupoSelecionado); 
        }
    }

    @FXML
    public void deletarGrupo(ActionEvent event) throws IOException {
        if (grupoSelecionado != null) {
            GrupoDAO.deleteGrupo(grupoSelecionado.getId());
            carregarGrupos();
        }
    }

    @FXML
    public void limparCamposGrupo(ActionEvent event) throws IOException {
        tblGrupos.getSelectionModel().clearSelection();
    }

    private void carregarGrupos() {
        
        tblGrupos.getItems().clear();
        tblGrupos.getItems().addAll(GrupoDAO.getAllGrupos());
    }

    @FXML
    public void adicionarIntegrante(ActionEvent event) throws IOException {
    }

    @FXML
    public void editarIntegrante(ActionEvent event) throws IOException {
    }

    @FXML
    public void deletarIntegrante(ActionEvent event) throws IOException {
    }

    @FXML
    public void limparCamposIntegrante(ActionEvent event) throws IOException {
    }

    @FXML
    public void baixarRelatorio(ActionEvent event) throws IOException {
    }
}
