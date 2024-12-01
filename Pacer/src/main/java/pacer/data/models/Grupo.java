package pacer.data.models;

import java.util.List;

import javafx.stage.Stage;
import pacer.grupo.GrupoGerarRelatorio;

public class Grupo {
    // region Atributos
    private int grupoId;
    private String grupoNome;
    private String reposLink;
    private List<Aluno> alunos;
    private int pontosSprint;
    // endregion

    // region Construtores
    public Grupo(String grupoNome, String reposLink, int pontosSprint) {
        this.grupoNome = grupoNome;
        this.reposLink = reposLink;
        this.pontosSprint = pontosSprint;
    }

    public Grupo(int grupoId, String grupoNome, String reposLink, int pontosSprint) {
        this.grupoId = grupoId;
        this.grupoNome = grupoNome;
        this.reposLink = reposLink;
        this.pontosSprint = pontosSprint;
    }
    // endregion

    // region Getters e Setters
    public int getId() {
        return grupoId;
    }
    public int getPontosSprint(){
        return pontosSprint;
    }
    public void setPontosSprint(int pontosSprint){
        this.pontosSprint = pontosSprint;
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

    public List<Aluno> getAlunos() { 
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public void getRelatorio(Stage stage, Sprint sprint) {
        GrupoGerarRelatorio.GenRelatorio(this, stage, sprint);
    }

    @Override
    public String toString() {
        return grupoNome;
    }
    // endregion
}
