package pacer.professor;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.GrupoDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Grupo;
import pacer.utils.sceneSwitcher;

public class ProfIntegrantesAddEditController {

    @FXML
    private TextField txtNomeAluno;
    @FXML
    private TextField txtRa;
    @FXML
    private TextField txtSemestre;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<Grupo> cmbGrupo;
    @FXML
    private TextField txtCursoSigla; // Campo para cursoSigla
    @FXML
    private Button btnSalvar; // Mantido para manter a lógica atual

    private Aluno alunoAtual; // Aluno atual a ser editado

    private int grupoId;

    @FXML
    public void initialize() {
        ObservableList<Grupo> grupos = FXCollections.observableArrayList(GrupoDAO.getAllGrupos());
        cmbGrupo.setItems(grupos);
    }

    // Método para definir o grupo a ser editado
    public void setAluno(Aluno aluno, Grupo grupo) {
        this.alunoAtual = aluno;
        txtNomeAluno.setText(aluno.getNome());
        txtEmail.setText(aluno.getEmail());
        txtRa.setText(String.valueOf(aluno.getRa()));
        cmbGrupo.setValue(grupo);
        txtSemestre.setText(String.valueOf(aluno.getSemestre()));
        txtCursoSigla.setText(aluno.getCursoSigla()); // Definindo o cursoSigla
        btnSalvar.setText("Atualizar Aluno");
    }

    @FXML
    private void selectGrupo() {
        Grupo grupoSelecionado = cmbGrupo.getSelectionModel().getSelectedItem();
        if (grupoSelecionado != null) {
            grupoId = grupoSelecionado.getId();
        }
    }

    @FXML
    public void salvarAluno(ActionEvent event) throws IOException {
        long ra = Long.parseLong(txtRa.getText());
        String email = txtEmail.getText();
        String nome = txtNomeAluno.getText();
        String cursoSigla = txtCursoSigla.getText(); 
        String semestre = txtSemestre.getText();

        if (alunoAtual == null) {
            Aluno novoAluno = new Aluno(ra, email, nome, grupoId, cursoSigla, semestre);
            AlunoDAO.addAluno(novoAluno);
        } else {
            alunoAtual.setRa(ra);
            alunoAtual.setEmail(email);
            alunoAtual.setNome(nome);
            alunoAtual.setGrupoId(grupoId);
            alunoAtual.setCursoSigla(cursoSigla);
            alunoAtual.setSemestre(semestre);
            AlunoDAO.updateAluno(alunoAtual);
        }

        // Volta para a tela anterior
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);
    }
}

