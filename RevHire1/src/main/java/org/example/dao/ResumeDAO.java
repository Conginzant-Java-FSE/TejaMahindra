package org.example.dao;

import org.example.model.Resume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class ResumeDAO {
    private static final Logger logger = LogManager.getLogger(ResumeDAO.class);
    private Connection connection;

    public ResumeDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean saveResume(Resume resume) {

        if (getResumeBySeekerId(resume.getSeekerId()) != null) {
            return updateResume(resume);
        } else {
            return createResume(resume);
        }
    }

    public boolean createResume(Resume resume) {
        String query = "INSERT INTO resumes (seeker_id, summary, education, experience, skills) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, resume.getSeekerId());
            pstmt.setString(2, resume.getSummary());
            pstmt.setString(3, resume.getEducation());
            pstmt.setString(4, resume.getExperience());
            pstmt.setString(5, resume.getSkills());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error creating resume: {}", resume.getSeekerId(), e);
            return false;
        }
    }

    public boolean updateResume(Resume resume) {
        String query = "UPDATE resumes SET summary = ?, education = ?, experience = ?, skills = ? WHERE seeker_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, resume.getSummary());
            pstmt.setString(2, resume.getEducation());
            pstmt.setString(3, resume.getExperience());
            pstmt.setString(4, resume.getSkills());
            pstmt.setInt(5, resume.getSeekerId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error updating resume: {}", resume.getSeekerId(), e);
            return false;
        }
    }

    public Resume getResumeBySeekerId(int seekerId) {
        String query = "SELECT * FROM resumes WHERE seeker_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, seekerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Resume(
                        rs.getInt("resume_id"),
                        rs.getInt("seeker_id"),
                        rs.getString("summary"),
                        rs.getString("education"),
                        rs.getString("experience"),
                        rs.getString("skills"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving resume by seekerId: {}", seekerId, e);
        }
        return null;
    }
}
