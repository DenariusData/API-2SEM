package pacer.professor;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import pacer.data.dao.CriteriosDAO;
import pacer.data.models.Criterios;
import pacer.utils.sceneSwitcher;

public class ProfCriteriosAddEditController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtDescricao;
    @FXML
    private CheckBox chkAtivo;
    @FXML
    private Button btnSalvar;

    private Criterios criterioAtual;

    @FXML
    public void initialize() {

    }

    public void setCriterio(Criterios criterio) {
        this.criterioAtual = criterio;
        txtNome.setText(criterio.getNome());
        txtDescricao.setText(criterio.getDescricao());
        chkAtivo.setSelected(criterio.isAtivo());
        btnSalvar.setText("Atualizar Critério");
    }

    @FXML
    public void salvarCriterio(ActionEvent event) throws IOException {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        boolean ativo = chkAtivo.isSelected();

        if (criterioAtual == null) {
            Criterios novoCriterio = new Criterios(nome, descricao, ativo);
            CriteriosDAO.create(novoCriterio);
        } else {
            criterioAtual.setNome(nome);
            criterioAtual.setDescricao(descricao);
            criterioAtual.setAtivo(ativo);
            CriteriosDAO.update(criterioAtual);
        }
        sceneSwitcher.switchScene("/FXML/ProfCriteriosView.fxml", event);
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfCriteriosView.fxml", event);
    }
}
