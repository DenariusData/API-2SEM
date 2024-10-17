package pacer.data.models;

public class AlunoGrupo {

    //region Atributos
    private long alunoRa;
    private int grupoId;
    //endregion

    //region Construtor
    public AlunoGrupo(long alunoRa, int grupoId) {
        this.alunoRa = alunoRa;
        this.grupoId = grupoId;
    }
    //endregion

    //region Getters e Setters
    public long getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(long alunoRa) {
        this.alunoRa = alunoRa;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }
    //endregion
}
