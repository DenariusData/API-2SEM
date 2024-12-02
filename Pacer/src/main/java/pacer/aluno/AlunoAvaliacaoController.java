package pacer.aluno;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.AvaliacaoDAO;
import pacer.data.dao.CriteriosDAO;
import pacer.data.dao.PontosDAO;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Aluno;
import pacer.data.models.AlunosParaAvaliacao;
import pacer.data.models.Avaliacao;
import pacer.data.models.Criterios;
import pacer.data.models.Pontos;
import pacer.data.models.Sprint;
import pacer.professor.ProfVisualizarPontosController;
import pacer.utils.mbox;
import pacer.utils.sceneSwitcher;

public class AlunoAvaliacaoController implements Initializable {

    @FXML
    private Button realizarAvaliacaoBtn;
    @FXML
    private ImageView imgVoltar;
    @FXML
    private Label grupoField;
    @FXML
    private TableView<Aluno> tableView;
    @FXML
    private TableColumn<Aluno, String> colNome;
    @FXML
    private TableView<Aluno> tableViewAlunos;
    @FXML
    private ComboBox<Sprint> cmbSprint;

    private Aluno alunoSelecionado;
    private Aluno alunoLogado;
    
    private int sprintId;

    private Sprint sprintSelecionado;

    private final Sprint sprintAtual = SprintDAO.getSprintAtual();

    private Pontos pontosSelecionados;

    private LocalDate dataLimite;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoLogado = Aluno.AlunoLogado.getAluno();
        try {
            grupoField.setText(alunoLogado.getGrupoNome());
        } catch (Exception e) {
            mbox.ShowMessageBox(AlertType.ERROR, "Erro ao carregar grupo", "Erro ao carregar o grupo do usuário.");
        } 
        ObservableList<Sprint> sprints = FXCollections.observableArrayList(SprintDAO.getAllSprints());

        if (sprintAtual != null) {
            Thread tCarregaTable = new Thread(() -> {
                Platform.runLater(() -> {
                    cmbSprint.setValue(sprintAtual);
                    sprintId = sprintAtual.getSprintId();
                    configurarTableView(sprintId);
                });
            });
            tCarregaTable.start();
        }
        cmbSprint.setItems(sprints);

        pontosSelecionados = PontosDAO.getPontosBySprintAndGrupo(sprintAtual.getSprintId(), alunoLogado.getGrupo().getId());
        java.util.Date dataAtribuicao = pontosSelecionados.getDataAtribuicao();
        LocalDate dataAtribuicaoLocalDateTime = dataAtribuicao.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        dataLimite = dataAtribuicaoLocalDateTime.plusDays(7);
    }
    @FXML
    public void selectSprint() {
        sprintSelecionado = cmbSprint.getValue();
        if (sprintSelecionado != null) {
            sprintId = sprintSelecionado.getSprintId();
        }
        configurarTableView(sprintId);
    }

    @FXML
    public void handleRowClickIntegrantes(MouseEvent event) {
        alunoSelecionado = tableView.getSelectionModel().getSelectedItem();
    }

    private void configurarTableView(int sprintId) {
        tableView.getColumns().clear();
        tableView.getColumns().add(colNome);
    
        colNome.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().getNome()));
    
        List<Criterios> criteriosList = CriteriosDAO.getAll();
        for (Criterios criterio : criteriosList) {
            if (criterio.isAtivo()) {
                TableColumn<Aluno, Double> colNota = new TableColumn<>(criterio.getNome());
                colNota.setCellValueFactory(param -> {
                    Avaliacao avaliacao = AvaliacaoDAO.getAvaliacaoPorAlunoECriterio(alunoLogado.getRa(), param.getValue().getRa(), criterio.getId(), sprintId);
                    return new javafx.beans.property.SimpleDoubleProperty(avaliacao != null ? avaliacao.getNota() : 0.0).asObject();
                });
    
                colNota.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
    
                colNota.setPrefWidth((tableView.getWidth() - colNome.getWidth()) / criteriosList.size());
    
                tableView.getColumns().add(colNota);
            }
        }
    
        List<Aluno> alunosDoGrupo = AlunoDAO.getAlunosDoGrupo(alunoLogado.getGrupoId());
        alunosDoGrupo.removeIf(aluno -> aluno.getRa() == alunoLogado.getRa());
        ObservableList<Aluno> alunosList = FXCollections.observableArrayList(alunosDoGrupo);
        
        tableView.setItems(alunosList);
    
        tableView.layout();
    }    

    @FXML
    public void abrirRealizarAvaliacao(ActionEvent event) throws IOException {
        if (alunoSelecionado == null) {
            mbox.ShowMessageBox(AlertType.WARNING, "Erro", "Selecione um integrante do grupo para avaliar");
            return;
        }
        if (sprintAtual == null) {
            mbox.ShowMessageBox(AlertType.WARNING, "Sprint", "Não há nenhuma sprint ativa no momento.");
            return;
        }
        if (sprintSelecionado.getSprintId() != sprintAtual.getSprintId()) {
            mbox.ShowMessageBox(AlertType.WARNING, "Sprint", "Selecione a Sprint atual para efetuar a avaliação.");
            return;
        }
            if (LocalDate.now().isBefore(pontosSelecionados.getDataAtribuicao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) || 
                LocalDate.now().isAfter(dataLimite)) {

            mbox.ShowMessageBox(AlertType.WARNING, "Sprint", "Não há periodo de avaliação ativo no momento");
            return;
        }
        AlunosParaAvaliacao.setAvaliado(alunoSelecionado);
        AlunosParaAvaliacao.setAvaliador(alunoLogado);

        sceneSwitcher.switchSceneRetController("/FXML/AlunoRealizarAvalView.fxml", event);
        
    }

    @FXML
    public void handleVerPontos(ActionEvent event) throws IOException {
        ProfVisualizarPontosController controllerVerPontos = sceneSwitcher.openNewWindow("/FXML/ProfVisualizarPontosView.fxml");
        controllerVerPontos.selectSprint(alunoLogado.getGrupo(), sprintSelecionado);
    }

    @FXML
    public void confirmarVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoHomeView.fxml", event);
    }
}
