package pacer.data.dao;

import pacer.data.DatabaseConnection;
import java.sql.*;

public class ProfMateriaDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addProfMateria(int materiaId, int profId) {
        String sql = "INSERT INTO PROF_MATERIA (MATERIA_ID, PROF_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, materiaId);
            stmt.setInt(2, profId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProfMateria(int materiaId, int profId) {
        String sql = "DELETE FROM PROF_MATERIA WHERE MATERIA_ID = ? AND PROF_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, materiaId);
            stmt.setInt(2, profId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
