package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pacer.data.DatabaseConnection;
import pacer.data.models.AlunoGrupo;

public class AlunoGrupoDAO {

    public static void create(AlunoGrupo alunoGrupo) {
        String sql = "INSERT INTO ALUNO_GRUPO (ALUNO_RA, GRUPO_ID) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, alunoGrupo.getAlunoRa());
            pstmt.setInt(2, alunoGrupo.getGrupoId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AlunoGrupo read(long alunoRa, int grupoId) {
        String sql = "SELECT * FROM ALUNO_GRUPO WHERE ALUNO_RA = ? AND GRUPO_ID = ?";
        AlunoGrupo alunoGrupo = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, alunoRa);
            pstmt.setInt(2, grupoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                alunoGrupo = new AlunoGrupo(rs.getLong("ALUNO_RA"), rs.getInt("GRUPO_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunoGrupo;
    }

    public static void update(AlunoGrupo alunoGrupo) {
        String sql = "UPDATE ALUNO_GRUPO SET GRUPO_ID = ? WHERE ALUNO_RA = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, alunoGrupo.getGrupoId());
            pstmt.setLong(2, alunoGrupo.getAlunoRa());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(long alunoRa, int grupoId) {
        String sql = "DELETE FROM ALUNO_GRUPO WHERE ALUNO_RA = ? AND GRUPO_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, alunoRa);
            pstmt.setInt(2, grupoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
