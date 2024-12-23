package pacer.professor;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.GrupoDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Grupo;
import pacer.data.models.Pontos;
import pacer.data.models.Sprint;
import pacer.utils.mbox;
import pacer.utils.sceneSwitcher;

public class ProfEquipesController {
    
    @FXML
    private TableView<Grupo> tblGrupos;
    @FXML
    private TableView<Aluno> tblIntegrantes; // Tabela de membros

    private ObservableList<Grupo> grupos = FXCollections.observableArrayList();
    private ObservableList<Aluno> membros = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Grupo, String> colNomeGrupo;

    @FXML
    private TableColumn<Aluno, String> colNomeIntegrante;

    @FXML
    private TableColumn<Aluno, String> colFuncaoIntegrante;

    @FXML
    private TableColumn<Grupo, String> colReposLink;
    @FXML
    private TableColumn<Pontos, Integer> colPontos;

    private Grupo grupoSelecionado;
    private Aluno membroSelecionado;
    
    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnDeletarIntegrante;

    @FXML
    private Button btnSalvarIntegrante;


    @FXML
    public void initialize() {
        colNomeGrupo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colReposLink.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReposLink()));
        colNomeIntegrante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

        carregarGrupos();
        configurarTabelas();
        
        tblGrupos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            tblIntegrantes.getItems().clear();
            if (newSelection != null) {
                carregarMembros(newSelection.getId());
            }
        });

        tblIntegrantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        });
        
    }

    private void configurarTabelas() {
        tblGrupos.setItems(grupos);
        tblIntegrantes.setItems(membros);
    }

    private void carregarMembros(int grupoId) {
        membros.setAll(AlunoDAO.getAlunosByGrupo(grupoId));
    }
    @FXML
    private void handleSemGrupos() {
        membros.setAll(AlunoDAO.getAlunosSemGrupo());
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
    public void deletarGrupo(ActionEvent event) {
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
        grupos.setAll(GrupoDAO.getAllGrupos());
    }
    
    @FXML
    public void adicionarIntegrante(ActionEvent event) throws IOException {
        ProfIntegrantesAddEditController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfIntegrantesAddEditView.fxml", event);
            controller.addAluno(grupoSelecionado); 
    }
    @FXML
    public void handleRowClickIntegrantes(MouseEvent event) {
        membroSelecionado = tblIntegrantes.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void editarIntegrante(ActionEvent event) throws IOException {
        if (membroSelecionado != null) {
            ProfIntegrantesAddEditController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfIntegrantesAddEditView.fxml", event);
            controller.setAluno(membroSelecionado, grupoSelecionado); 
    }
}

    @FXML
    public void deletarIntegrante(ActionEvent event) throws IOException {
        if (membroSelecionado != null) {
            AlunoDAO.deleteAluno(membroSelecionado.getRa());
            carregarMembros(grupoSelecionado.getId()); // Atualiza a tabela após a exclusão
        }
    }

    @FXML
    public void limparCamposIntegrante(ActionEvent event) throws IOException {
        tblIntegrantes.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleRelatorio(ActionEvent event) throws IOException {
        if (grupoSelecionado == null) {
            mbox.ShowError("Selecione um grupo");
            return;
        }
        ProfEquipesSprintController controller = sceneSwitcher.openNewWindow("/FXML/ProfEquipesSprintView.fxml");
        controller.selectGrupo(grupoSelecionado, "Relatorio");
    }
    public void gerarRelatorio(Grupo grupoSelecionado, Sprint sprintSelecionada, Stage stage) throws IOException {
        stage.close();
        grupoSelecionado.getRelatorio((Stage) btnRelatorio.getScene().getWindow(), sprintSelecionada);
    }
    @FXML
    private void handlePontos(ActionEvent event) throws IOException {
        if (grupoSelecionado == null) {
            mbox.ShowError("Selecione um grupo");
            return;
        }
        ProfEquipesSprintController controller = sceneSwitcher.openNewWindow("/FXML/ProfEquipesSprintView.fxml");
        controller.selectGrupo(grupoSelecionado, "Pontos");
    }
    @FXML
    private void handleVerPontos(ActionEvent event) throws IOException {
        if (grupoSelecionado == null) {
            mbox.ShowError("Selecione um grupo");
            return;
        }
        ProfEquipesSprintController controller = sceneSwitcher.openNewWindow("/FXML/ProfEquipesSprintView.fxml");
        controller.selectGrupo(grupoSelecionado, "VerPontos");
    }
}
