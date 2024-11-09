package pacer.data.models;

import java.util.List;

import javafx.stage.Stage;
import pacer.grupo.GrupoGerarRelatorio;

public class Grupo {
    // region Atributos
    private int grupoId;
    private String grupoNome;
    private String reposLink;
    private String cursoSigla;
    private String semestre;
    private List<Aluno> alunos;
    // endregion

    // region Construtores
    public Grupo(String grupoNome, String reposLink, String cursoSigla, String semestre) {
        this.grupoNome = grupoNome;
        this.reposLink = reposLink;
        this.cursoSigla = cursoSigla;
        this.semestre = semestre;
    }

    public Grupo(int grupoId, String grupoNome, String reposLink, String cursoSigla, String semestre) {
        this.grupoId = grupoId;
        this.grupoNome = grupoNome;
        this.reposLink = reposLink;
        this.cursoSigla = cursoSigla;
        this.semestre = semestre;
    }
    // endregion

    // region Getters e Setters
    public int getId() {
        return grupoId;
    }

    public void setId(int grupoId) {
        this.grupoId = grupoId;
    }

    public String getNome() {
        return grupoNome;
    }

    public void setNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    public String getReposLink() {
        return reposLink;
    }

    public void setReposLink(String reposLink) {
        this.reposLink = reposLink;
    }

    public String getCursoSigla() {
        return cursoSigla;
    }

    public void setCursoSigla(String cursoSigla) {
        this.cursoSigla = cursoSigla;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public List<Aluno> getAlunos() { 
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public void getRelatorio(Stage stage) {
        GrupoGerarRelatorio.GenRelatorio(this, stage);
    }

    @Override
    public String toString() {
        return grupoNome;
    }
    // endregion
}
