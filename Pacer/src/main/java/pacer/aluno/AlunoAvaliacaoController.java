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
import pacer.data.dao.GrupoDAO;
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
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            grupoField.setText(GrupoDAO.getGrupoComAlunos(Aluno.AlunoLogado.getAluno().getGrupoId()).getNome());
        } catch (Exception e) {
            mbox.ShowMessageBox(AlertType.ERROR, "Erro ao carregar grupo", "Erro ao carregar o grupo do usuário.");
        } finally {
            configurarTableView();
        } 
    }
    @FXML
    public void handleRowClickIntegrantes(MouseEvent event) {
        alunoSelecionado = tableView.getSelectionModel().getSelectedItem();
        System.out.println(alunoSelecionado.getNome());
    }
    private void configurarTableView() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colNome.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
        colEmail.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
    
        List<Aluno> alunosDoGrupo = AlunoDAO.getAlunosDoGrupo(Aluno.AlunoLogado.getAluno().getGrupoId());
        alunosDoGrupo.removeIf(aluno -> aluno.getRa() == Aluno.AlunoLogado.getAluno().getRa());
        ObservableList<Aluno> alunosList = FXCollections.observableArrayList(alunosDoGrupo);
        tableView.setItems(alunosList);

        List<Criterios> criteriosList = CriteriosDAO.getAll();
    
        for (Criterios criterio : criteriosList) {
            TableColumn<Aluno, Double> colNota = new TableColumn<>(criterio.getNome());
    
            colNota.setCellValueFactory(param -> {
                Avaliacao avaliacao = AvaliacaoDAO.getAvaliacaoPorAlunoECriterio(Aluno.AlunoLogado.getAluno().getRa(), param.getValue().getRa(), criterio.getId());
                return new javafx.beans.property.SimpleDoubleProperty(avaliacao != null ? avaliacao.getNota() : 0.0).asObject();
            });
    
            colNota.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-family: 'Arial Black';");
    
            tableView.getColumns().add(colNota);
        }
    }
    @FXML
    public void abrirRealizarAvaliacao(ActionEvent event) throws IOException {
        if (alunoSelecionado != null) {
            AlunoRealizarAvalController controller = sceneSwitcher.switchSceneRetController("/FXML/AlunoRealizarAvalView.fxml", event);
            controller.carregarDadosAluno(alunoSelecionado.getNome(), GrupoDAO.getGrupoComAlunos(Aluno.AlunoLogado.getAluno().getGrupoId()).getNome(), alunoSelecionado.getFoto()); 
        }
        
    }

    @FXML
    public void confirmarVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoHomeView.fxml", event);
    }
}
