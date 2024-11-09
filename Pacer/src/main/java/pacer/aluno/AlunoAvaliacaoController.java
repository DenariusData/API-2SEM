package pacer.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.AvaliacaoDAO;
import pacer.data.dao.CriteriosDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Avaliacao;
import pacer.data.models.Criterios;
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
    private TableColumn<Aluno, String> colEmail;
    @FXML
    private TableView<Aluno> tableViewAlunos;
    private Aluno alunoSelecionado;
    private Aluno alunoLogado; 
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunoLogado = Aluno.AlunoLogado.getAluno();
        try {
            grupoField.setText(alunoLogado.getGrupoNome());
        } catch (Exception e) {
            mbox.ShowMessageBox(AlertType.ERROR, "Erro ao carregar grupo", "Erro ao carregar o grupo do usuário.");
        } finally {
            configurarTableView();
        } 
    }
    @FXML
    public void handleRowClickIntegrantes(MouseEvent event) {
        alunoSelecionado = tableView.getSelectionModel().getSelectedItem();
    }
    private void configurarTableView() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colNome.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
        colEmail.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
    
        List<Aluno> alunosDoGrupo = AlunoDAO.getAlunosDoGrupo(alunoLogado.getGrupoId());
        alunosDoGrupo.removeIf(aluno -> aluno.getRa() == alunoLogado.getRa());
        ObservableList<Aluno> alunosList = FXCollections.observableArrayList(alunosDoGrupo);
        tableView.setItems(alunosList);

        List<Criterios> criteriosList = CriteriosDAO.getAll();
    
        for (Criterios criterio : criteriosList) {
            if (criterio.isAtivo()) {
                TableColumn<Aluno, Double> colNota = new TableColumn<>(criterio.getNome());
    
                colNota.setCellValueFactory(param -> {
                    Avaliacao avaliacao = AvaliacaoDAO.getAvaliacaoPorAlunoECriterio(alunoLogado.getRa(), param.getValue().getRa(), criterio.getId());
                    return new javafx.beans.property.SimpleDoubleProperty(avaliacao != null ? avaliacao.getNota() : 0.0).asObject();
                });
        
                colNota.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
        
                tableView.getColumns().add(colNota);
            }
        }
    }
    @FXML
    public void abrirRealizarAvaliacao(ActionEvent event) throws IOException {
        if (alunoSelecionado == null) {
            mbox.ShowMessageBox(AlertType.WARNING, "Erro", "Selecione um integrante do grupo para avaliar");
            return;
        }
        AlunoRealizarAvalController controller = sceneSwitcher.switchSceneRetController("/FXML/AlunoRealizarAvalView.fxml", event);
        controller.carregarDadosAluno(alunoSelecionado); 
        
    }

    @FXML
    public void confirmarVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoHomeView.fxml", event);
    }
}
