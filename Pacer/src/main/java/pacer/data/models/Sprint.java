package pacer.data.models;

import java.time.LocalDate;

public class Sprint {

    //region Atributos
    private int sprintId;
    private int sprint;
    private int semestre;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    //endregion

    //region Construtor
    public Sprint(int sprintId, int sprint, int semestre, LocalDate dataInicio, LocalDate dataFim) {
        this.sprintId = sprintId;
        this.sprint = sprint;
        this.semestre = semestre;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    //endregion

    //region Getters e Setters
    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public int getSprint() {
        return sprint;
    }

    public void setSprint(int sprint) {
        this.sprint = sprint;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
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
