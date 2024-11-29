package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import pacer.data.DatabaseConnection;
import pacer.data.models.Avaliacao;

public class AvaliacaoDAO {

    public static void create(Avaliacao avaliacao) {
        String sql = "INSERT INTO AVALIACAO (AVALIADO_ALUNO_RA, AVALIADOR_ALUNO_RA, CRITERIO_ID, NOTA, SPRINT_ID, AVALIACAO_DATA) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, avaliacao.getAvaliadoAlunoRa());
            pstmt.setLong(2, avaliacao.getAvaliadorAlunoRa());
            pstmt.setInt(3, avaliacao.getCriterioId());
            pstmt.setDouble(4, avaliacao.getNota());
            pstmt.setInt(5, avaliacao.getSprintId());
            pstmt.setTimestamp(6, java.sql.Timestamp.valueOf(avaliacao.getData()));
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
                avaliacao = new Avaliacao(rs.getInt("AVALIACAO_ID"), 
                                        rs.getLong("AVALIADO_ALUNO_RA"), 
                                        rs.getLong("AVALIADOR_ALUNO_RA"), 
                                        rs.getInt("CRITERIO_ID"), 
                                        rs.getDouble("NOTA"),
                                        rs.getInt("SPRINT_ID"), 
                                        rs.getTimestamp("AVALIACAO_DATA").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avaliacao;
    }

    public static void update(Avaliacao avaliacao) {
        String sql = "UPDATE AVALIACAO SET AVALIADO_ALUNO_RA = ?, AVALIADOR_ALUNO_RA = ?, CRITERIO_ID = ?, NOTA = ?, SPRINT_ID = ?, AVALIACAO_DATA = ? WHERE AVALIACAO_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, avaliacao.getAvaliadoAlunoRa());
            pstmt.setLong(2, avaliacao.getAvaliadorAlunoRa());
            pstmt.setInt(3, avaliacao.getCriterioId());
            pstmt.setDouble(4, avaliacao.getNota());
            pstmt.setInt(4, avaliacao.getSprintId());
            pstmt.setTimestamp(6, java.sql.Timestamp.valueOf(avaliacao.getData()));
            pstmt.setInt(7, avaliacao.getAvaliacaoId());
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
    public static Avaliacao getAvaliacaoPorAlunoECriterio(Long avaliadorRa, Long avaliadoRa, int criterioId) {
        Avaliacao avaliacao = null;
        String sql = "SELECT * FROM AVALIACAO WHERE AVALIADOR_ALUNO_RA = ? AND AVALIADO_ALUNO_RA = ? AND CRITERIO_ID = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setLong(1, avaliadorRa); // Aluno que está fazendo a avaliação
            stmt.setLong(2, avaliadoRa); // Aluno que está sendo avaliado
            stmt.setLong(3, criterioId); // Critério específico
    
            ResultSet rs = stmt.executeQuery();
            
            
            if (rs.next()) {
                LocalDateTime data = rs.getTimestamp("AVALIACAO_DATA").toLocalDateTime();
                
                avaliacao = new Avaliacao();
                avaliacao.setAvaliacaoId(rs.getInt("AVALIACAO_ID"));
                avaliacao.setAvaliadorAlunoRa(rs.getLong("AVALIADOR_ALUNO_RA"));
                avaliacao.setAvaliadoAlunoRa(rs.getLong("AVALIADO_ALUNO_RA"));
                avaliacao.setCriterioId(rs.getInt("CRITERIO_ID"));
                avaliacao.setNota(rs.getDouble("NOTA"));
                avaliacao.setSprintId(rs.getInt("SPRINT_ID"));
                avaliacao.setData(data);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return avaliacao;
    }
    
}
