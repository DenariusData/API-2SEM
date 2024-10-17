package pacer.data.models;

public class Materia {

    //region Atributos
    private int id;
    private String nome;
    private int semestre;
    //endregion

    //region Construtor
    public Materia(int id, String nome, int semestre) {
        this.id = id;
        this.nome = nome;
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

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    //endregion
}
