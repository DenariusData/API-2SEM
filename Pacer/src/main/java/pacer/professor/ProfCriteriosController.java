package pacer.professor;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pacer.data.dao.CriteriosDAO;
import pacer.data.models.Criterios;
import pacer.utils.sceneSwitcher;

public class ProfCriteriosController {

    @FXML
    private TableView<Criterios> tableView;
    @FXML
    private TableColumn<Criterios, Integer> idColumn;
    @FXML
    private TableColumn<Criterios, String> nomeColumn;
    @FXML
    private TableColumn<Criterios, String> descricaoColumn;
    @FXML
    private TableColumn<Criterios, Boolean> ativoColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextArea descricaoField;
    @FXML
    private CheckBox ativoField;
    @FXML
    private Button adicionarButton;
    @FXML
    private Button editarButton;
    @FXML
    private Button deletarButton;

    private Criterios criterioSelecionado;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        ativoColumn.setCellValueFactory(new PropertyValueFactory<>("ativo"));

        ativoColumn.setCellFactory(column -> new TableCell<Criterios, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Sim" : "Não");
                }
            }
        });

        carregarDados();
    }

    private void carregarDados() {
        tableView.setItems(FXCollections.observableArrayList(CriteriosDAO.getAll()));
    }
    @FXML
    private void handleRowClick() {
        criterioSelecionado = tableView.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void handleVoltar(javafx.event.ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfHomeView.fxml", event);
    }
    @FXML
    private void adicionarCriterio(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfCriteriosAddEditView.fxml", event);
    }
    @FXML
    private void editarCriterio(ActionEvent event) throws IOException {
        if (criterioSelecionado != null) {
            ProfCriteriosAddEditController controller = sceneSwitcher.switchSceneRetController("/FXML/ProfCriteriosAddEditView.fxml", event);
            controller.setCriterio(criterioSelecionado);
        }
    }
    @FXML
    private void deletarCriterio() {
        if (criterioSelecionado != null) {
            CriteriosDAO.delete(criterioSelecionado.getId());
            carregarDados();
        }
    }
    @FXML
    private void limparCampos() {
        tableView.getSelectionModel().clearSelection();
    }
}
