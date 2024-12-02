package pacer.data.models;

public class Professor {

    //region Atributos
    private String email;
    private String senha;
    //endregion

     //region Professor Logado
    public static class ProfessorLogado extends Professor {
        private static ProfessorLogado instancia;

        // Construtor privado
        private ProfessorLogado(String email, String senha) {
            super(email, senha);
        }

        // Método para obter a instância singleton
        public static ProfessorLogado getInstancia(String email, String senha) {
            if (instancia == null) {
                instancia = new ProfessorLogado(email, senha);
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
    public Professor(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    //endregion

    //region Getters e Setters
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
    //endregion
}
