package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pacer.data.DatabaseConnection;
import pacer.data.models.Avaliacao;

public class AvaliacaoDAO {

    public static void create(Avaliacao avaliacao) {
        String sql = "INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, AVALIACAO_DATA) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, avaliacao.getAvaliadoAlunoRa());
            pstmt.setLong(2, avaliacao.getAvaliadorAlunoRa());
            pstmt.setInt(3, avaliacao.getCriterioId());
            pstmt.setDouble(4, avaliacao.getNota());
            pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(avaliacao.getData()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Avaliacao read(int avaliacaoId) {
        String sql = "SELECT * FROM AVALIACAO WHERE AVALIACAO_ID = ?";
        Avaliacao avaliacao = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, avaliacaoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                avaliacao = new Avaliacao(rs.getInt("AVALIACAO_ID"), rs.getLong("AVALIADO_ALUNO_RA"), rs.getLong("AVALIADOR_ALUNO_RA"), rs.getInt("CRITERIO_ID"), rs.getDouble("NOTA"), rs.getTimestamp("AVALIACAO_DATA").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avaliacao;
    }

    public static void update(Avaliacao avaliacao) {
        String sql = "UPDATE AVALIACAO SET AVALIADO_ALUNO_RA = ?, AVALIADOR_ALUNO_RA = ?, CRITERIO_ID = ?, NOTA = ?, AVALIACAO_DATA = ? WHERE AVALIACAO_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, avaliacao.getAvaliadoAlunoRa());
            pstmt.setLong(2, avaliacao.getAvaliadorAlunoRa());
            pstmt.setInt(3, avaliacao.getCriterioId());
            pstmt.setDouble(4, avaliacao.getNota());
            pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(avaliacao.getData()));
            pstmt.setInt(6, avaliacao.getAvaliacaoId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int avaliacaoId) {
        String sql = "DELETE FROM AVALIACAO WHERE AVALIACAO_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, avaliacaoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
