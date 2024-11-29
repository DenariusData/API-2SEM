package pacer.data.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import pacer.data.dao.GrupoDAO;

public class Aluno {
    //region Atributos
    private long ra;
    private String email;
    private String nome;
    private String senha;
    private byte[] foto;
    private int grupoId;
    //endregion

    //region Aluno logado ()
    public static class AlunoLogado extends Aluno {
        private static AlunoLogado instancia;

        // Construtor privado
        private AlunoLogado(long ra, String email, String nome, String senha, byte[] foto, int grupoId) {
            super(ra, email, nome, senha, foto, grupoId);
        }

        public static AlunoLogado getInstancia(long ra, String email, String nome, String senha, byte[] foto, int grupoId) {
            if (instancia == null) {
                if (foto == null || foto.length == 0) {
                    foto = loadDefaultImage();
                }
                instancia = new AlunoLogado(ra, email, nome, senha, foto, grupoId);
            }
            return instancia;
        }

        public static AlunoLogado getAluno() {
            return instancia;
        }

        // Método para limpar a instância (logout)
        public static void logout() {
            instancia = null;
        }
    }
    //endregion

    //region Construtores
    public Aluno(long ra, String email, String nome, String senha, byte[] foto, int grupoId) {
        this.ra = ra;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.foto = foto;
        this.grupoId = grupoId;
       
    }
    // Construtor para criação de aluno via professor
    public Aluno(long ra, String email, String nome, int grupoId) {
        this.ra = ra;
        this.email = email;
        this.nome = nome;
        this.grupoId = grupoId;
       
    }
    //endregion

    //region Getters e Setters
    public long getRa() {
        return ra;
    }

    public void setRa(long ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        if (foto == null) return loadDefaultImage();
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public String getGrupoNome() {
        return GrupoDAO.getGrupoComAlunos(this.grupoId).getNome();
    }
    //endregion

    private static byte[] loadDefaultImage() {
        try {
            return Files.readAllBytes(Paths.get("src/main/resources/images/placeholder-user.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
