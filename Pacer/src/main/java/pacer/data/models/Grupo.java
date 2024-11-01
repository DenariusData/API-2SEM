package pacer.data.models;

public class Grupo {

    //region Atributos
    private int id;
    private String nome;
    private String reposLink; // Link do repositório do grupo
    private int semestre; // Semestre do grupo
    //endregion

    //region Construtores
    public Grupo(String nome, String reposLink, int semestre) {
        this.nome = nome;
        this.reposLink = reposLink;
        this.semestre = semestre;
    }

    public Grupo(int id, String nome, String reposLink, int semestre) {
        this.id = id;
        this.nome = nome;
        this.reposLink = reposLink;
        this.semestre = semestre;
    }
    //endregion

    //region Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReposLink() {
        return reposLink;
    }

    public void setReposLink(String reposLink) {
        this.reposLink = reposLink;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    //endregion
}
