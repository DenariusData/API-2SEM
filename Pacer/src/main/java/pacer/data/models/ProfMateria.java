package pacer.data.models;

public class ProfMateria {

    //region Atributos
    private int materiaId;
    private int profId;
    //endregion

    //region Construtor
    public ProfMateria(int materiaId, int profId) {
        this.materiaId = materiaId;
        this.profId = profId;
    }
    //endregion

    //region Getters e Setters
    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }
    //endregion
}
