package pacer.data.models;

public class Professor {

    //region Atributos
    private int id;
    private String email;
    private String senha;
    private byte[] foto;
    //endregion

    //#region Professor Logado
    public static class ProfessorLogado extends Professor {
        public ProfessorLogado(int id, String email, String senha, byte[] foto) {
            super(id, email, senha, foto);
        }
    }
    //#endregion Professor Logado

    //region Construtor
    public Professor(int id, String email, String senha, byte[] foto) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
    //endregion

    //region Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    //endregion
}
