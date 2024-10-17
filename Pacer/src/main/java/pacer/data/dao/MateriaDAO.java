package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacer.data.DatabaseConnection;
import pacer.data.models.Materia;

public class MateriaDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addMateria(Materia materia) {
        String sql = "INSERT INTO MATERIA (MATERIA_NOME, MATERIA_SEMESTRE) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, materia.getNome());
            stmt.setInt(2, materia.getSemestre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Materia> getAllMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM MATERIA";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                materias.add(new Materia(rs.getInt("MATERIA_ID"), rs.getString("MATERIA_NOME"),
                        rs.getInt("MATERIA_SEMESTRE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public static void updateMateria(Materia materia) {
        String sql = "UPDATE MATERIA SET MATERIA_NOME = ?, MATERIA_SEMESTRE = ? WHERE MATERIA_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, materia.getNome());
            stmt.setInt(2, materia.getSemestre());
            stmt.setInt(3, materia.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMateria(int id) {
        String sql = "DELETE FROM MATERIA WHERE MATERIA_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
