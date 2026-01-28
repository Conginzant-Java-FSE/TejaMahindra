package org.example.service;

import org.example.dao.ResumeDAO;
import org.example.dao.UserDAO;
import org.example.model.Resume;
import org.example.model.User;

public class UserService {
    private UserDAO userDAO;
    private ResumeDAO resumeDAO;

    public UserService() {
        this.userDAO = new UserDAO();
        this.resumeDAO = new ResumeDAO();
    }

    public User getUserProfile(String email) {
        return userDAO.getUserByEmail(email);
    }

    public boolean upsertResume(int seekerId, String summary, String education, String experience, String skills) {
        Resume resume = new Resume(seekerId, summary, education, experience, skills);
        return resumeDAO.saveResume(resume);
    }

    public Resume getResume(int seekerId) {
        return resumeDAO.getResumeBySeekerId(seekerId);
    }
}
