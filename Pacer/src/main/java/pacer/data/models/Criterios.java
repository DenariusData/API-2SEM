package pacer.data.models;

public class Criterios {

    //region Atributos
    private int id;
    private String nome;
    private String descricao;
    private boolean ativo;
    //endregion

    //region Construtor
    public Criterios(int id, String nome, String descricao, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    //endregion
}
