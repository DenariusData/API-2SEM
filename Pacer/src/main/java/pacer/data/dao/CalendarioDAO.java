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
import pacer.data.models.Calendario;

public class CalendarioDAO {
    private static Connection connection = DatabaseConnection.getConnection();

    public static void addCalendario(Calendario calendario) {
        String sql = "INSERT INTO CALENDARIO (SEMESTRE, SPRINT_ID, DATA_INICIO, DATA_FIM) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, calendario.getSemestre());
            stmt.setInt(2, calendario.getSprintId());
            stmt.setDate(3, Date.valueOf(calendario.getDataInicio()));
            stmt.setDate(4, Date.valueOf(calendario.getDataFim()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Calendario> getAllCalendarios() {
        List<Calendario> calendarios = new ArrayList<>();
        String sql = "SELECT * FROM CALENDARIO";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                calendarios.add(new Calendario(rs.getInt("CALENDARIO_ID"),
                                                rs.getString("SEMESTRE"),
                                                rs.getInt("SPRINT_ID"),
                                                rs.getDate("DATA_INICIO").toLocalDate(),
                                                rs.getDate("DATA_FIM").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calendarios;
    }

    public static void updateCalendario(Calendario calendario) {
        String sql = "UPDATE CALENDARIO SET SEMESTRE = ?, SPRINT_ID = ?, DATA_INICIO = ?, DATA_FIM = ? WHERE CALENDARIO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, calendario.getSemestre());
            stmt.setInt(2, calendario.getSprintId());
            stmt.setDate(3, Date.valueOf(calendario.getDataInicio()));
            stmt.setDate(4, Date.valueOf(calendario.getDataFim()));
            stmt.setInt(5, calendario.getCalendarioId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCalendario(int calendarioId) {
        String sql = "DELETE FROM CALENDARIO WHERE CALENDARIO_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, calendarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
