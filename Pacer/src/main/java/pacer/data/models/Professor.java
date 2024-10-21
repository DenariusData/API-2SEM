package pacer.data.models;

public class Professor {

    //region Atributos
    private int id;
    private String nome;
    private String email;
    private String senha;
    private byte[] foto;
    //endregion

     //region Professor Logado
    public static class ProfessorLogado extends Professor {
        private static ProfessorLogado instancia;

        // Construtor privado
        private ProfessorLogado(int id, String nome, String email, String senha, byte[] foto) {
            super(id, nome, email, senha, foto);
        }

        // Método para obter a instância singleton
        public static ProfessorLogado getInstancia(int id, String nome, String email, String senha, byte[] foto) {
            if (instancia == null) {
                instancia = new ProfessorLogado(id, nome, email, senha, foto);
            }
            return instancia;
        }
        public static ProfessorLogado getProfessor() {
            return instancia;
        }

        // Método para limpar a instância (logout)
        public static void logout() {
            instancia = null;
        }
    }
    //endregion

    //region Construtor
    public Professor(int id, String nome, String email, String senha, byte[] foto) {
        this.id = id;
        this.nome = nome;
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
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
