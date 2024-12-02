package pacer.data.models;

import java.util.Date;

public class Pontos {

    private int id;
    private int pontosIniciais;
    private int pontosAtuais;
    private int sprintId;
    private int grupoId;
    private Date dataAtribuicao;

    public Pontos(int id, int pontosIniciais, int pontosAtuais, int sprintId, int grupoId, Date dataAtribuicao) {
        this.id = id;
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosAtuais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
        this.dataAtribuicao = dataAtribuicao;
    }

    public Pontos(int pontosIniciais, int pontosAtuais, int sprintId, int grupoId, Date dataAtribuicao) {
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosAtuais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
        this.dataAtribuicao = dataAtribuicao;
    }

    public Pontos(int pontosIniciais, int sprintId, int grupoId, Date dataAtribuicao) {
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosIniciais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
        this.dataAtribuicao = dataAtribuicao;
    }

    public Pontos(int pontosIniciais, int sprintId, int grupoId) {
        this.pontosIniciais = pontosIniciais;
        this.pontosAtuais = pontosIniciais;
        this.sprintId = sprintId;
        this.grupoId = grupoId;
        this.dataAtribuicao = dataAtribuicao;
    }

    public Pontos(int sprintId, int grupoId, Date dataAtribuicao) {
        this.sprintId = sprintId;
        this.grupoId = grupoId;
        this.dataAtribuicao = dataAtribuicao;
    }
    public Pontos(int sprintId, int grupoId) {
        this.sprintId = sprintId;
        this.grupoId = grupoId;
        this.dataAtribuicao = dataAtribuicao;
    }

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

    public void setDataAtribuicao(Date dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public Date getDataAtribuicao() {
        return dataAtribuicao;
    }
}
