package pacer.professor;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pacer.data.dao.CriteriosDAO;
import pacer.data.models.Criterios;

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
    private TextField descricaoField;
    @FXML
    private CheckBox ativoField;
    @FXML
    private Button adicionarButton;
    @FXML
    private Button editarButton;
    @FXML
    private Button deletarButton;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        ativoColumn.setCellValueFactory(new PropertyValueFactory<>("ativo"));

        carregarDados();
    }

    @FXML
    private void adicionarCriterio() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        boolean ativo = ativoField.isSelected();
        if (!nome.isEmpty() && !descricao.isEmpty()) {
            Criterios novoCriterio = new Criterios(0, nome, descricao, ativo);
            CriteriosDAO.create(novoCriterio);
            carregarDados();
            limparCampos();
        }
    }
    @FXML
    private void editarCriterio() {
        Criterios selecionado = selectRow();
        if (selecionado != null) {
            int id = selecionado.getId();
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            boolean ativo = ativoField.isSelected();
            Criterios criterioEditado = new Criterios(id, nome, descricao, ativo);
            CriteriosDAO.update(criterioEditado);
            carregarDados();
            limparCampos();
        }
    }


    @FXML
    private void deletarCriterio() {
        
        if (selectRow() != null) {
            CriteriosDAO.delete(selectRow().getId());
            carregarDados(); 
            limparCampos();
        }
    }

    @FXML
    private void handleRowClick() {
        Criterios selecionado = selectRow();
        if (selecionado != null) {
            preencherCampos(selecionado); 
        }
    }


    private Criterios selectRow() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    private void carregarDados() {
        tableView.setItems(FXCollections.observableArrayList(CriteriosDAO.getAll()));
    }

    private void limparCampos() {
        idField.clear();
        nomeField.clear();
        descricaoField.clear();
        ativoField.setSelected(false);
    }

    private void preencherCampos(Criterios criterio) {
        idField.setText(Integer.toString(criterio.getId()));
        nomeField.setText(criterio.getNome());
        descricaoField.setText(criterio.getDescricao());
        ativoField.setSelected(criterio.isAtivo());
    }
}
