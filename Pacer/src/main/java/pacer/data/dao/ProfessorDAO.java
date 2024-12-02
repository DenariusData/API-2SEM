package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacer.data.DatabaseConnection;
import pacer.data.models.Professor;

public class ProfessorDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addProfessor(Professor professor) {
        String sql = "INSERT INTO PROFESSOR (PROF_EMAIL, PROF_SENHA) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getEmail());
            stmt.setString(2, professor.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Professor.ProfessorLogado findByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM PROFESSOR WHERE PROF_EMAIL = ? AND PROF_SENHA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return Professor.ProfessorLogado.getInstancia(
                    rs.getString("PROF_EMAIL"),
                    rs.getString("PROF_SENHA")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Professor> getAllProfessores() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM PROFESSOR";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                professores.add(new Professor(rs.getString("PROF_EMAIL"), rs.getString("PROF_SENHA")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }
}
