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
import pacer.data.models.Aluno;

public class AlunoDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addAluno(Aluno aluno) {
        String sql = "INSERT INTO ALUNO (ALUNO_RA, ALUNO_EMAIL, ALUNO_NOME, ALUNO_SENHA, FOTO, GRUPO_ID, CURSO_SIGLA, SEMESTRE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, aluno.getRa());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getNome());
            stmt.setString(4, aluno.getSenha());
            stmt.setBlob(5, new ByteArrayInputStream(aluno.getFoto()));
            stmt.setInt(6, aluno.getGrupoId());
            stmt.setString(7, aluno.getCursoSigla());
            stmt.setString(8, aluno.getSemestre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Aluno findByEmailAndSenha(String email, String senha) {
        String sql = "SELECT * FROM ALUNO WHERE ALUNO_EMAIL = ? AND ALUNO_SENHA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Aluno.AlunoLogado alunoLogado = Aluno.AlunoLogado.getInstancia(
                    rs.getLong("ALUNO_RA"), 
                    rs.getString("ALUNO_EMAIL"), 
                    rs.getString("ALUNO_NOME"), 
                    rs.getString("ALUNO_SENHA"), 
                    rs.getBytes("FOTO"),
                    rs.getInt("GRUPO_ID"),
                    rs.getString("CURSO_SIGLA"),
                    rs.getString("SEMESTRE")
                );

                return alunoLogado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Aluno> getAllAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM ALUNO";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alunos.add(new Aluno(
                    rs.getLong("ALUNO_RA"), 
                    rs.getString("ALUNO_EMAIL"),
                    rs.getString("ALUNO_NOME"), 
                    rs.getString("ALUNO_SENHA"), 
                    rs.getBytes("FOTO"), 
                    rs.getInt("GRUPO_ID"),
                    rs.getString("CURSO_SIGLA"),
                    rs.getString("SEMESTRE")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static void updateAluno(Aluno aluno) {
        String sql = "UPDATE ALUNO SET ALUNO_EMAIL = ?, ALUNO_NOME = ?, ALUNO_SENHA = ?, FOTO = ?, GRUPO_ID = ?, CURSO_SIGLA = ?, SEMESTRE = ? WHERE ALUNO_RA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getEmail());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getSenha());
            stmt.setBlob(4, new ByteArrayInputStream(aluno.getFoto()));
            stmt.setInt(5, aluno.getGrupoId());
            stmt.setString(6, aluno.getCursoSigla());
            stmt.setString(7, aluno.getSemestre());
            stmt.setLong(8, aluno.getRa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAluno(long ra) {
        String sql = "DELETE FROM ALUNO WHERE ALUNO_RA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ra);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Aluno findByEmailWithNullSenha(String email) {
        String sql = "SELECT * FROM ALUNO WHERE ALUNO_EMAIL = ? AND ALUNO_SENHA IS NULL";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                        rs.getLong("ALUNO_RA"),
                        rs.getString("ALUNO_EMAIL"),
                        rs.getString("ALUNO_NOME"),
                        rs.getString("ALUNO_SENHA"),
                        rs.getBytes("FOTO"),
                        rs.getInt("GRUPO_ID"),
                        rs.getString("CURSO_SIGLA"), 
                        rs.getString("SEMESTRE")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Aluno> getAlunosDoGrupo(long grupoId) {
        String sql = "SELECT * FROM ALUNO WHERE GRUPO_ID = ?";
        List<Aluno> alunos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, grupoId);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getLong("ALUNO_RA"),
                    rs.getString("ALUNO_NOME"),
                    rs.getString("ALUNO_EMAIL"),
                    rs.getInt("GRUPO_ID"),
                    rs.getString("CURSO_SIGLA"),
                    rs.getString("SEMESTRE")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return alunos;
    }

    public static List<Aluno> getAlunosByGrupo(int grupoId) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM ALUNO WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alunos.add(new Aluno(
                    rs.getLong("ALUNO_RA"), 
                    rs.getString("ALUNO_EMAIL"), 
                    rs.getString("ALUNO_NOME"), 
                    rs.getString("ALUNO_SENHA"), 
                    rs.getBytes("FOTO"), 
                    rs.getInt("GRUPO_ID"),
                    rs.getString("CURSO_SIGLA"), 
                    rs.getString("SEMESTRE")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
    
    
}
