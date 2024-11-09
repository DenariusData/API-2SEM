package pacer.professor;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pacer.data.dao.AlunoDAO;
import pacer.data.models.Aluno;
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
    private TextField txtGrupoId;
    @FXML
    private TextField txtCursoSigla; // Campo para cursoSigla
    @FXML
    private Button btnSalvar; // Mantido para manter a lógica atual

    private Aluno alunoAtual; // Aluno atual a ser editado

    @FXML
    public void initialize() {
        // Configuração inicial, se necessário
    }

    // Método para definir o grupo a ser editado
    public void setAluno(Aluno aluno) {
        this.alunoAtual = aluno;
        txtNomeAluno.setText(aluno.getNome());
        txtRa.setText(String.valueOf(aluno.getRa()));
        txtSemestre.setText(String.valueOf(aluno.getSemestre()));
        txtCursoSigla.setText(aluno.getCursoSigla()); // Definindo o cursoSigla
        btnSalvar.setText("Atualizar Aluno");
    }

    @FXML
    public void salvarAluno(ActionEvent event) throws IOException {
        long ra = Long.parseLong(txtRa.getText());
        String email = txtEmail.getText();
        String nome = txtNomeAluno.getText();
        int grupoId = Integer.parseInt(txtGrupoId.getText());
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

