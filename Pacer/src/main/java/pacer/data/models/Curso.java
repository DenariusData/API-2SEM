package pacer.data.models;

public class Curso {
    //region Atributos
    private int cursoId; 
    private String cursoNome;
    private String cursoSigla;
    //endregion

    //region Construtor
    public Curso(int cursoId, String cursoNome, String cursoSigla) {
        this.cursoId = cursoId;
        this.cursoNome = cursoNome;
        this.cursoSigla = cursoSigla;
    }
    //endregion

    //region Getters e Setters
    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public String getCursoSigla() {
        return cursoSigla;
    }

    public void setCursoSigla(String cursoSigla) {
        this.cursoSigla = cursoSigla;
    }
    //endregion
}
