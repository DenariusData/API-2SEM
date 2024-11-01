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
    private Button btnSalvar;

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
        btnSalvar.setText("Atualizar Grupo"); // Muda o texto do botão para "Atualizar"
    }

    @FXML
    public void salvarGrupo(ActionEvent event) throws IOException {
        String nome = txtNomeGrupo.getText();
        String reposLink = txtReposLink.getText();
        int semestre = Integer.parseInt(txtSemestre.getText());

        if (grupoAtual == null) {
            // Se grupoAtual for nulo, estamos adicionando um novo grupo
            Grupo novoGrupo = new Grupo(nome, reposLink, semestre);
            GrupoDAO.addGrupo(novoGrupo);
        } else {
            // Se grupoAtual não for nulo, estamos editando um grupo existente
            grupoAtual.setNome(nome);
            grupoAtual.setReposLink(reposLink);
            grupoAtual.setSemestre(semestre);
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
