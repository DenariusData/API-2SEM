package pacer.data.models;

import java.time.LocalDate;

public class Calendario {

    //region Atributos
    private int calendarioId;
    private String semestre;
    private int sprintId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    //endregion

    //region Construtor
    public Calendario(int calendarioId, String semestre, int sprintId, LocalDate dataInicio, LocalDate dataFim) {
        this.calendarioId = calendarioId;
        this.semestre = semestre;
        this.sprintId = sprintId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public Calendario(int calendarioId, int sprintId, LocalDate dataInicio, LocalDate dataFim) {
        this.calendarioId = calendarioId;
        this.sprintId = sprintId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public Calendario( int sprintId, LocalDate dataInicio, LocalDate dataFim) {
        this.sprintId = sprintId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    //endregion

    //region Getters e Setters
    public int getCalendarioId() {
        return calendarioId;
    }

    public void setCalendarioId(int calendarioId) {
        this.calendarioId = calendarioId;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    //endregion
}
