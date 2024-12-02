package pacer.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pacer.data.DatabaseConnection;
import pacer.data.models.Aluno;
import pacer.data.models.Grupo;

public class GrupoDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addGrupo(Grupo grupo) {
        String sql = "INSERT INTO GRUPO (GRUPO_NOME, REPOS_LINK) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            stmt.setString(2, grupo.getReposLink());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Grupo> getAllGrupos() {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM GRUPO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                grupos.add(new Grupo(
                        rs.getInt("GRUPO_ID"),
                        rs.getString("GRUPO_NOME"),
                        rs.getString("REPOS_LINK")
                        
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public static void updateGrupo(Grupo grupo) {
        String sql = "UPDATE GRUPO SET GRUPO_NOME = ?, REPOS_LINK = ? WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            stmt.setString(2, grupo.getReposLink());
            stmt.setInt(3, grupo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGrupo(int grupoId) {
        String sql = "DELETE FROM GRUPO WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Grupo getGrupoComAlunos(int grupoId) {
        Grupo grupo = null;
        String sql = "SELECT G.GRUPO_ID, G.GRUPO_NOME, G.REPOS_LINK, A.ALUNO_RA, A.ALUNO_EMAIL, A.ALUNO_NOME FROM GRUPO G LEFT JOIN ALUNO A ON G.GRUPO_ID = A.GRUPO_ID WHERE G.GRUPO_ID = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Aluno> alunos = new ArrayList<>();
                
                while (rs.next()) {
                    // Inicializa o grupo uma única vez
                    if (grupo == null) {
                        grupo = new Grupo(
                            rs.getInt("GRUPO_ID"),
                            rs.getString("GRUPO_NOME"),
                            rs.getString("REPOS_LINK")
                        );
                    }
                    
                    String alunoRA = rs.getString("ALUNO_RA");
                    if (alunoRA != null) {  
                        Aluno aluno = new Aluno(
                            Long.parseLong(alunoRA),
                            rs.getString("ALUNO_EMAIL"),
                            rs.getString("ALUNO_NOME"),
                            grupoId
                        );
                        alunos.add(aluno);
                    }
                }

                if (grupo != null) {
                    grupo.setAlunos(alunos);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return grupo;
    }
    public static Grupo getGrupoByNome(String nome) {
        Grupo grupo = null;
        String sql = "SELECT * FROM GRUPO WHERE GRUPO_NOME = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                grupo = new Grupo(
                    rs.getInt("GRUPO_ID"), 
                    rs.getString("GRUPO_NOME"), 
                    rs.getString("REPOS_LINK")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }
    public static Grupo getGrupoById(int id) {
        Grupo grupo = null;
        String sql = "SELECT * FROM GRUPO WHERE GRUPO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                grupo = new Grupo(
                    rs.getInt("GRUPO_ID"), 
                    rs.getString("GRUPO_NOME"), 
                    rs.getString("REPOS_LINK")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }
}
