package pacer.data.models;

public class Grupo {

    //region Atributos
    private int id;
    private String nome;
    //endregion

    //region Construtor
    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
    //endregion
}
