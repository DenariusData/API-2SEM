package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pacer.data.DatabaseConnection;
import pacer.data.models.Criterios;

public class CriteriosDAO {

    public static void create(Criterios criterio) {
        String sql = "INSERT INTO CRITERIOS (CRITERIO_NOME, CRITERIO_DESCRICAO, CRITERIO_ATIVO) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, criterio.getNome());
            pstmt.setString(2, criterio.getDescricao());
            pstmt.setBoolean(3, criterio.isAtivo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Criterios read(int criterioId) {
        String sql = "SELECT * FROM CRITERIOS WHERE CRITERIO_ID = ?";
        Criterios criterio = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, criterioId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                criterio = new Criterios(rs.getInt("CRITERIO_ID"), rs.getString("CRITERIO_NOME"), rs.getString("CRITERIO_DESCRICAO"), rs.getBoolean("CRITERIO_ATIVO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criterio;
    }

    public static void update(Criterios criterio) {
        String sql = "UPDATE CRITERIOS SET CRITERIO_NOME = ?, CRITERIO_DESCRICAO = ?, CRITERIO_ATIVO = ? WHERE CRITERIO_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, criterio.getNome());
            pstmt.setString(2, criterio.getDescricao());
            pstmt.setBoolean(3, criterio.isAtivo());
            pstmt.setInt(4, criterio.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int criterioId) {
        String sql = "DELETE FROM CRITERIOS WHERE CRITERIO_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, criterioId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
