package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacer.data.models.Curso;

public class CursoDAO {
    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO CURSO (CURSO_NOME, CURSO_SIGLA) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getCursoNome());
            stmt.setString(2, curso.getCursoSigla());
            stmt.executeUpdate();
        }
    }

    public List<Curso> getAllCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM CURSO";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("CURSO_ID"),
                        rs.getString("CURSO_NOME"),
                        rs.getString("CURSO_SIGLA")
                ));
            }
        }
        return cursos;
    }

    public void updateCurso(Curso curso) throws SQLException {
        String sql = "UPDATE CURSO SET CURSO_NOME = ?, CURSO_SIGLA = ? WHERE CURSO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getCursoNome());
            stmt.setString(2, curso.getCursoSigla());
            stmt.setInt(3, curso.getCursoId());
            stmt.executeUpdate();
        }
    }

    public void deleteCurso(int cursoId) throws SQLException {
        String sql = "DELETE FROM CURSO WHERE CURSO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cursoId);
            stmt.executeUpdate();
        }
    }
}
