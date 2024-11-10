package pacer.data.models;

public class AlunosParaAvaliacao {
    private static Aluno avaliador;
    private static Aluno avaliado;

    public static void setAvaliador(Aluno aluno) {
        avaliador = aluno;
    }
    public static Aluno getAvaliador() {
        return avaliador;
    }

    public static void setAvaliado(Aluno aluno) {
        avaliado = aluno;
    }
    public static Aluno getAvaliado() {
        return avaliado;
    }
}
