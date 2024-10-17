package pacer.data.models;

import java.time.LocalDateTime;

public class Avaliacao {

    //region Atributos
    private int avaliacaoId;
    private long avaliadoAlunoRa;
    private long avaliadorAlunoRa;
    private int criterioId;
    private double nota;
    private LocalDateTime data;
    //endregion

    //region Construtor
    public Avaliacao(int avaliacaoId, long avaliadoAlunoRa, long avaliadorAlunoRa, int criterioId, double nota, LocalDateTime data) {
        this.avaliacaoId = avaliacaoId;
        this.avaliadoAlunoRa = avaliadoAlunoRa;
        this.avaliadorAlunoRa = avaliadorAlunoRa;
        this.criterioId = criterioId;
        this.nota = nota;
        this.data = data;
    }
    //endregion

    //region Getters e Setters
    public int getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(int avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public long getAvaliadoAlunoRa() {
        return avaliadoAlunoRa;
    }

    public void setAvaliadoAlunoRa(long avaliadoAlunoRa) {
        this.avaliadoAlunoRa = avaliadoAlunoRa;
    }

    public long getAvaliadorAlunoRa() {
        return avaliadorAlunoRa;
    }

    public void setAvaliadorAlunoRa(long avaliadorAlunoRa) {
        this.avaliadorAlunoRa = avaliadorAlunoRa;
    }

    public int getCriterioId() {
        return criterioId;
    }

    public void setCriterioId(int criterioId) {
        this.criterioId = criterioId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    //endregion
}
