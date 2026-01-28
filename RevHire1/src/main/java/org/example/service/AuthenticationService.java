package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.Employer;
import org.example.model.JobSeeker;
import org.example.model.User;

public class AuthenticationService {
    private UserDAO userDAO;

    public AuthenticationService() {
        this.userDAO = new UserDAO();
    }

    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && org.mindrot.jbcrypt.BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean registerJobSeeker(String name, String email, String password, String phone) {
        if (userDAO.getUserByEmail(email) != null) {
            return false;
        }
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        JobSeeker seeker = new JobSeeker(name, email, hashedPassword, phone);
        return userDAO.registerJobSeeker(seeker);
    }

    public boolean registerEmployer(String name, String email, String password, String companyName, String industry,
            String location) {
        if (userDAO.getUserByEmail(email) != null) {
            return false;
        }
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        Employer employer = new Employer(name, email, hashedPassword, companyName, industry, location);
        return userDAO.registerEmployer(employer);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && org.mindrot.jbcrypt.BCrypt.checkpw(oldPassword, user.getPassword())) {
            String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(newPassword,
                    org.mindrot.jbcrypt.BCrypt.gensalt());
            return userDAO.updatePassword(email, hashedPassword);
        }
        return false;
    }

    public boolean resetPassword(String email, String newPassword) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(newPassword,
                    org.mindrot.jbcrypt.BCrypt.gensalt());
            return userDAO.updatePassword(email, hashedPassword);
        }
        return false;
    }
}
