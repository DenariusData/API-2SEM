package pacer.data.models;

public class Grupo {

    //region Atributos
    private int id;
    private String nome;
    private String reposLink; // Link do repositório do grupo
    //endregion

    //region Construtor
    public Grupo(int id, String nome, String reposLink) {
        this.id = id;
        this.nome = nome;
        this.reposLink = reposLink;
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
    //endregion
}
