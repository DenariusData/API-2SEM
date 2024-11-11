package pacer.professor;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pacer.data.dao.GrupoDAO;
import pacer.data.models.Grupo;
import pacer.utils.sceneSwitcher;

public class ProfEquipesAddEditController {

    @FXML
    private TextField txtNomeGrupo;
    @FXML
    private TextField txtReposLink;
    @FXML
    private TextField txtSemestre;
    @FXML
    private TextField txtCursoSigla; // Campo para cursoSigla
    @FXML
    private Button btnSalvar; // Mantido para manter a lógica atual

    private Grupo grupoAtual; // Grupo atual a ser editado

    @FXML
    public void initialize() {
        // Configuração inicial, se necessário
    }

    // Método para definir o grupo a ser editado
    public void setGrupo(Grupo grupo) {
        this.grupoAtual = grupo;
        txtNomeGrupo.setText(grupo.getNome());
        txtReposLink.setText(grupo.getReposLink());
        txtSemestre.setText(String.valueOf(grupo.getSemestre()));
        txtCursoSigla.setText(grupo.getCursoSigla()); // Definindo o cursoSigla
        btnSalvar.setText("Atualizar Grupo");
    }

    @FXML
    public void salvarGrupo(ActionEvent event) throws IOException {
        String nome = txtNomeGrupo.getText();
        String reposLink = txtReposLink.getText();
        String semestre = txtSemestre.getText();
        String cursoSigla = txtCursoSigla.getText(); // Obtendo o cursoSigla

        if (grupoAtual == null) {
            Grupo novoGrupo = new Grupo(nome, reposLink, cursoSigla, semestre);
            GrupoDAO.addGrupo(novoGrupo);
        } else {
            grupoAtual.setNome(nome);
            grupoAtual.setReposLink(reposLink);
            grupoAtual.setSemestre(semestre);
            grupoAtual.setCursoSigla(cursoSigla);
            GrupoDAO.updateGrupo(grupoAtual);
        }

        // Volta para a tela anterior
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }
}
