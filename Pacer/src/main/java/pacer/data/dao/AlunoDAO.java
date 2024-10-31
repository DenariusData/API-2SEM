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
        String sql = "INSERT INTO ALUNO (ALUNO_RA, ALUNO_EMAIL, ALUNO_NOME, ALUNO_SENHA, FOTO, GRUPO_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, aluno.getRa());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getNome());
            stmt.setString(4, aluno.getSenha());
            stmt.setBlob(5, new ByteArrayInputStream(aluno.getFoto()));
            stmt.setInt(6, aluno.getGrupoId());
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
                    rs.getInt("GRUPO_ID")
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
                    rs.getInt("GRUPO_ID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static void updateAluno(Aluno aluno) {
        String sql = "UPDATE ALUNO SET ALUNO_EMAIL = ?, ALUNO_NOME = ?, ALUNO_SENHA = ?, FOTO = ?, GRUPO_ID = ? WHERE ALUNO_RA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getEmail());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getSenha());
            stmt.setBlob(4, new ByteArrayInputStream(aluno.getFoto()));
            stmt.setInt(5, aluno.getGrupoId());
            stmt.setLong(6, aluno.getRa());
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
}
