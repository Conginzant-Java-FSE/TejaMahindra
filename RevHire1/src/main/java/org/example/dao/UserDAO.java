package org.example.dao;

import org.example.model.Employer;
import org.example.model.JobSeeker;
import org.example.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {
            logger.error("Error getting user by email: {}", email, e);
        }
        return null;
    }

    public boolean registerJobSeeker(JobSeeker seeker) {
        String insertUser = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        String insertSeeker = "INSERT INTO job_seekers (user_id, phone) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);

            int userId = -1;
            try (PreparedStatement pstmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, seeker.getName());
                pstmt.setString(2, seeker.getEmail());
                pstmt.setString(3, seeker.getPassword());
                pstmt.setString(4, "SEEKER");
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 0)
                    throw new SQLException("Creating user failed, no rows affected.");

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

            try (PreparedStatement pstmt = connection.prepareStatement(insertSeeker)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, seeker.getPhone());
                pstmt.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Error rolling back transaction during seeker registration", ex);
            }
            logger.error("Error registering job seeker", e);
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Error resetting auto-commit", e);
            }
        }
    }

    public boolean registerEmployer(Employer employer) {
        String insertUser = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        String insertEmployer = "INSERT INTO employers (user_id, company_name, industry, location) VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            int userId = -1;
            try (PreparedStatement pstmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, employer.getName());
                pstmt.setString(2, employer.getEmail());
                pstmt.setString(3, employer.getPassword());
                pstmt.setString(4, "EMPLOYER");
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 0)
                    throw new SQLException("Creating user failed, no rows affected.");

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

            try (PreparedStatement pstmt = connection.prepareStatement(insertEmployer)) {
                pstmt.setInt(1, userId);
                pstmt.setString(2, employer.getCompanyName());
                pstmt.setString(3, employer.getIndustry());
                pstmt.setString(4, employer.getLocation());
                pstmt.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Error rolling back transaction during employer registration", ex);
            }
            logger.error("Error registering employer", e);
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Error resetting auto-commit", e);
            }
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, email);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error updating password for user: {}", email, e);
            return false;
        }
    }
}
