package pacer.data.models;

public class Pontos {

    //#region Atributos
    private int id;
    private int pontosIniciais;
    private int pontosAtuais;
    private int sprintId;
    private int grupoId;
    //#endregion

    //#region Construtores
    public Pontos(int id, int pontosIniciais, int pontosAtuais, int sprintId, int grupoId) {
        this.id = id;
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosAtuais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
    }
    public Pontos(int pontosIniciais, int pontosAtuais, int sprintId, int grupoId) {
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosAtuais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
    }
    public Pontos(int pontosIniciais, int sprintId, int grupoId) {
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosIniciais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
    }
    //#endregion

    //#region Getters e setters
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setPontosIniciais(int pontosIniciais) {
        this.pontosIniciais = pontosIniciais;
    }
    public int getPontosIniciais() {
        return pontosIniciais;
    }
    public void setPontosAtuais(int pontosAtuais) {
        this.pontosAtuais = pontosAtuais;
    }
    public int getPontosAtuais() {
        return pontosAtuais;
    }
    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }
    public int getSprintId() {
        return sprintId;
    }
    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }
    public int getGrupoId() {
        return grupoId;
    }
    //#endregion
}
