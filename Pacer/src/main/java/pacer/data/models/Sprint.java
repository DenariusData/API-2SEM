package pacer.data.models;

import java.time.LocalDate;

public class Sprint {

    //region Atributos
    private int id;
    private int sprint;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    //endregion

    //region Construtor
    public Sprint(int sprintId, int sprint, LocalDate dataInicio, LocalDate dataFim) {
        this.id = sprintId;
        this.sprint = sprint;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public Sprint(int sprint, LocalDate dataInicio, LocalDate dataFim) {
        this.sprint = sprint;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public Sprint() {

    }
    //endregion

    //region Getters e Setters
    public int getSprintId() {
        return id;
    }

    public void setSprintId(int sprintId) {
        this.id = sprintId;
    }

    public int getSprint() {
        return sprint;
    }

    public void setSprint(int sprint) {
        this.sprint = sprint;
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
