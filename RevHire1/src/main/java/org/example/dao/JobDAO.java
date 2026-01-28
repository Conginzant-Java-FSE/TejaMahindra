package org.example.dao;

import org.example.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    private static final Logger logger = LogManager.getLogger(JobDAO.class);
    private Connection connection;

    public JobDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean postJob(Job job) {
        String query = "INSERT INTO jobs (employer_id, title, description, requirements, location, salary_range, job_type, experience_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, job.getEmployerId());
            pstmt.setString(2, job.getTitle());
            pstmt.setString(3, job.getDescription());
            pstmt.setString(4, job.getRequirements());
            pstmt.setString(5, job.getLocation());
            pstmt.setString(6, job.getSalaryRange());
            pstmt.setString(7, job.getJobType());
            pstmt.setString(8, job.getExperienceLevel());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error posting job: {}", job.getTitle(), e);
            return false;
        }
    }

    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs ORDER BY posted_at DESC";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                jobs.add(mapResultSetToJob(rs));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all jobs", e);
        }
        return jobs;
    }

    public List<Job> getJobsByEmployer(int employerId) {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE employer_id = ? ORDER BY posted_at DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                jobs.add(mapResultSetToJob(rs));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving jobs by employer: {}", employerId, e);
        }
        return jobs;
    }

    public Job getJobById(int jobId) {
        String query = "SELECT * FROM jobs WHERE job_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, jobId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToJob(rs);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving job by ID: {}", jobId, e);
        }
        return null;
    }

    public List<Job> searchJobs(String criterion, String keyword) {
        List<Job> jobs = new ArrayList<>();
        String query = "";

        switch (criterion.toLowerCase()) {
            case "title":
                query = "SELECT * FROM jobs WHERE title LIKE ? ORDER BY posted_at DESC";
                break;
            case "location":
                query = "SELECT * FROM jobs WHERE location LIKE ? ORDER BY posted_at DESC";
                break;
            case "company":
                query = "SELECT j.* FROM jobs j JOIN employers e ON j.employer_id = e.user_id WHERE e.company_name LIKE ? ORDER BY j.posted_at DESC";
                break;
            case "type":
                query = "SELECT * FROM jobs WHERE job_type LIKE ? ORDER BY posted_at DESC";
                break;
            case "experience":
                query = "SELECT * FROM jobs WHERE experience_level LIKE ? ORDER BY posted_at DESC";
                break;
            default:
                query = "SELECT * FROM jobs WHERE title LIKE ? ORDER BY posted_at DESC";
        }

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                jobs.add(mapResultSetToJob(rs));
            }
        } catch (SQLException e) {
            logger.error("Error searching jobs by {}: {}", criterion, keyword, e);
        }
        return jobs;
    }

    private Job mapResultSetToJob(ResultSet rs) throws SQLException {
        return new Job(
                rs.getInt("job_id"),
                rs.getInt("employer_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("requirements"),
                rs.getString("location"),
                rs.getString("salary_range"),
                rs.getString("job_type"),
                rs.getString("experience_level"),
                rs.getTimestamp("posted_at"));
    }
}
