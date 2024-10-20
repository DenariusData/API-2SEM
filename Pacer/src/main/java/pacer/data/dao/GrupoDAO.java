package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacer.data.DatabaseConnection;
import pacer.data.models.Grupo;

public class GrupoDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addGrupo(Grupo grupo) {
        String sql = "INSERT INTO GRUPO (GRUPO_NOME) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Grupo> getAllGrupos() {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM GRUPO";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                grupos.add(new Grupo(rs.getInt("GRUPO_ID"), rs.getString("GRUPO_NOME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public static void updateGrupo(Grupo grupo) {
        String sql = "UPDATE GRUPO SET GRUPO_NOME = ? WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            stmt.setInt(2, grupo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGrupo(int id) {
        String sql = "DELETE FROM GRUPO WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
