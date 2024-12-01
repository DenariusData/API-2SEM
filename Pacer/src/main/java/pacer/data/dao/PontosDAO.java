package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacer.data.DatabaseConnection;
import pacer.data.models.Pontos;

public class PontosDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    // Adicionar Pontos
    public static void addPontos(Pontos pontos) {
        String sql = "INSERT INTO PONTOS_SPRINT (PONTOS_INICIAIS, PONTOS_ATUAIS, SPRINT_ID, GRUPO_ID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pontos.getPontosIniciais());
            stmt.setInt(2, pontos.getPontosAtuais());
            stmt.setInt(3, pontos.getSprintId());
            stmt.setInt(4, pontos.getGrupoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obter todos os registros de Pontos
    public static List<Pontos> getAllPontos() {
        List<Pontos> pontosList = new ArrayList<>();
        String sql = "SELECT * FROM PONTOS_SPRINT";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pontosList.add(new Pontos(
                    rs.getInt("PONTOS_INICIAIS"),
                    rs.getInt("PONTOS_ATUAIS"),
                    rs.getInt("SPRINT_ID"),
                    rs.getInt("GRUPO_ID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pontosList;
    }

    // Atualizar Pontos
    public static void updatePontos(Pontos pontos) {
        String sql = "UPDATE PONTOS_SPRINT SET PONTOS_INICIAIS = ?, PONTOS_ATUAIS = ? WHERE SPRINT_ID = ? AND GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pontos.getPontosIniciais());
            stmt.setInt(2, pontos.getPontosAtuais());
            stmt.setInt(3, pontos.getSprintId());
            stmt.setInt(4, pontos.getGrupoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar Pontos
    public static void deletePontos(Pontos pontos) {
        String sql = "DELETE FROM PONTOS_SPRINT WHERE SPRINT_ID = ? AND GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pontos.getSprintId());
            stmt.setInt(2, pontos.getGrupoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obter Pontos por SprintId e GrupoId
    public static Pontos getPontosBySprintAndGrupo(Pontos pontos) {
        String sql = "SELECT * FROM PONTOS_SPRINT WHERE SPRINT_ID = ? AND GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pontos.getSprintId());
            stmt.setInt(2, pontos.getGrupoId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pontos(
                        rs.getInt("PONTOS_INICIAIS"),
                        rs.getInt("PONTOS_ATUAIS"),
                        rs.getInt("SPRINT_ID"),
                        rs.getInt("GRUPO_ID")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
