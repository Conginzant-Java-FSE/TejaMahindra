package org.example.dao;

import org.example.model.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    private static final Logger logger = LogManager.getLogger(ApplicationDAO.class);
    private Connection connection;

    public ApplicationDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean applyForJob(int jobId, int seekerId) {
        String query = "INSERT INTO applications (job_id, seeker_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, jobId);
            pstmt.setInt(2, seekerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error applying for job. JobId: {}, SeekerId: {}", jobId, seekerId, e);
            return false;
        }
    }

    public List<Application> getApplicationsByJob(int jobId) {
        List<Application> apps = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE job_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, jobId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                apps.add(mapResultSetToApplication(rs));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving applications for job: {}", jobId, e);
        }
        return apps;
    }

    public List<Application> getApplicationsBySeeker(int seekerId) {
        List<Application> apps = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE seeker_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, seekerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                apps.add(mapResultSetToApplication(rs));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving applications for seeker: {}", seekerId, e);
        }
        return apps;
    }

    public boolean updateStatus(int applicationId, String status) {
        String query = "UPDATE applications SET status = ? WHERE application_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, applicationId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating application status. AppId: {}, Status: {}", applicationId, status, e);
            return false;
        }
    }

    private Application mapResultSetToApplication(ResultSet rs) throws SQLException {
        return new Application(
                rs.getInt("application_id"),
                rs.getInt("job_id"),
                rs.getInt("seeker_id"),
                rs.getString("status"),
                rs.getTimestamp("applied_at"));
    }
}
