package pacer.professor;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pacer.data.dao.GrupoDAO;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Grupo;
import pacer.data.models.Sprint;
import pacer.utils.sceneSwitcher;

public class ProfEquipesAddEditController {

    @FXML
    private TextField txtNomeGrupo;
    @FXML
    private TextField txtReposLink;
    @FXML
    private TextField txtCursoSigla;
    @FXML
    private Button btnSalvar;


    private Grupo grupoAtual;

    @FXML
    public void initialize() {
    }

    public void setGrupo(Grupo grupo) {
        this.grupoAtual = grupo;
        txtNomeGrupo.setText(grupo.getNome());
        txtReposLink.setText(grupo.getReposLink());
        btnSalvar.setText("Atualizar Grupo");
    }

    @FXML
    public void salvarGrupo(ActionEvent event) throws IOException {
        String nome = txtNomeGrupo.getText();
        String reposLink = txtReposLink.getText();

        if (grupoAtual == null) {
            Grupo novoGrupo = new Grupo(nome, reposLink);
            GrupoDAO.addGrupo(novoGrupo);
        } else {
            grupoAtual.setNome(nome);
            grupoAtual.setReposLink(reposLink);
            GrupoDAO.updateGrupo(grupoAtual);
        }
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }
}
