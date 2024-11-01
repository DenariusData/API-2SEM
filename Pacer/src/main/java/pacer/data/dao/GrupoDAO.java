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
        String sql = "INSERT INTO GRUPO (GRUPO_NOME, REPOS_LINK, GRUPO_SEMESTRE) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            stmt.setString(2, grupo.getReposLink());
            stmt.setInt(3, grupo.getSemestre()); // Adicionando semestre
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
                grupos.add(new Grupo(
                    rs.getInt("GRUPO_ID"),
                    rs.getString("GRUPO_NOME"),
                    rs.getString("REPOS_LINK"),
                    rs.getInt("GRUPO_SEMESTRE") // Adicionando semestre
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public static void updateGrupo(Grupo grupo) {
        String sql = "UPDATE GRUPO SET GRUPO_NOME = ?, REPOS_LINK = ?, GRUPO_SEMESTRE = ? WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            stmt.setString(2, grupo.getReposLink());
            stmt.setInt(3, grupo.getSemestre()); // Adicionando semestre
            stmt.setInt(4, grupo.getId());
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
