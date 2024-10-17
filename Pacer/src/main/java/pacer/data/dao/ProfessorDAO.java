package pacer.data.dao;

import java.io.ByteArrayInputStream;
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
        String sql = "INSERT INTO PROFESSOR (PROF_EMAIL, PROF_SENHA, FOTO) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getEmail());
            stmt.setString(2, professor.getSenha());
            stmt.setBlob(3, new ByteArrayInputStream(professor.getFoto()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Professor findByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM PROFESSOR WHERE PROF_EMAIL = ? AND PROF_SENHA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professor(rs.getInt("PROF_ID"), rs.getString("PROF_EMAIL"),
                        rs.getString("PROF_SENHA"), rs.getBytes("FOTO"));
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
                professores.add(new Professor(rs.getInt("PROF_ID"), rs.getString("PROF_EMAIL"),
                        rs.getString("PROF_SENHA"), rs.getBytes("FOTO")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public static void updateProfessor(Professor professor) {
        String sql = "UPDATE PROFESSOR SET PROF_EMAIL = ?, PROF_SENHA = ?, FOTO = ? WHERE PROF_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getEmail());
            stmt.setString(2, professor.getSenha());
            stmt.setBlob(3, new ByteArrayInputStream(professor.getFoto()));
            stmt.setInt(4, professor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProfessor(int id) {
        String sql = "DELETE FROM PROFESSOR WHERE PROF_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
