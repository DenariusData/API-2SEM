package pacer.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacer.data.DatabaseConnection;
import pacer.data.models.Sprint;

public class SprintDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addSprint(Sprint sprint) {
        String sql = "INSERT INTO SPRINT (SPRINT, SEMESTRE, DATA_INICIO, DATA_FIM) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sprint.getSprint());
            stmt.setInt(2, sprint.getSemestre());
            stmt.setDate(3, Date.valueOf(sprint.getDataInicio()));
            stmt.setDate(4, Date.valueOf(sprint.getDataFim()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Sprint> getAllSprints() {
        List<Sprint> sprints = new ArrayList<>();
        String sql = "SELECT * FROM SPRINT";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sprints.add(new Sprint(rs.getInt("SPRINT_ID"), 
                                       rs.getInt("SPRINT"), 
                                       rs.getInt("SEMESTRE"),
                                       rs.getDate("DATA_INICIO").toLocalDate(), 
                                       rs.getDate("DATA_FIM").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sprints;
    }

    public static void updateSprint(Sprint sprint) {
        String sql = "UPDATE SPRINT SET SPRINT = ?, SEMESTRE = ?, DATA_INICIO = ?, DATA_FIM = ? WHERE SPRINT_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sprint.getSprint());
            stmt.setInt(2, sprint.getSemestre());
            stmt.setDate(3, Date.valueOf(sprint.getDataInicio()));
            stmt.setDate(4, Date.valueOf(sprint.getDataFim()));
            stmt.setInt(5, sprint.getSprintId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSprint(int id) {
        String sql = "DELETE FROM SPRINT WHERE SPRINT_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
