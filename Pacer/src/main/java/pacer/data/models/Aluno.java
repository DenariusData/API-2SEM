package pacer.data.models;

public class Aluno {
    //region Atributos
    private long ra;
    private String email;
    private String nome;
    private String senha;
    private byte[] foto;
    //endregion

    //region Aluno logado
    public static class AlunoLogado extends Aluno {
        private static AlunoLogado instancia;

        // Construtor privado
        private AlunoLogado(long ra, String email, String nome, String senha, byte[] foto) {
            super(ra, email, nome, senha, foto);
        }

        // Método para obter a instância singleton
        public static AlunoLogado getInstancia(long ra, String email, String nome, String senha, byte[] foto) {
            if (instancia == null) {
                instancia = new AlunoLogado(ra, email, nome, senha, foto);
            }
            return instancia;
        }
        public static AlunoLogado getAluno() {
            return instancia;
        }

        // Método para limpar a instância
        public static void logout() {
            instancia = null;
        }
    }
    //endregion

    //region Construtor
    public Aluno(long ra, String email, String nome, String senha, byte[] foto) {
        this.ra = ra;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.foto = foto;
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
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    //endregion
}
