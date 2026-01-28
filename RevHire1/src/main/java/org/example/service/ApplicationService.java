package org.example.service;

import org.example.dao.ApplicationDAO;
import org.example.model.Application;
import java.util.List;

public class ApplicationService {
    private ApplicationDAO applicationDAO;

    public ApplicationService() {
        this.applicationDAO = new ApplicationDAO();
    }

    public boolean applyForJob(int jobId, int seekerId) {

        return applicationDAO.applyForJob(jobId, seekerId);
    }

    public List<Application> getApplicationsForJob(int jobId) {
        return applicationDAO.getApplicationsByJob(jobId);
    }

    public List<Application> getMyApplications(int seekerId) {
        return applicationDAO.getApplicationsBySeeker(seekerId);
    }

    public boolean updateApplicationStatus(int applicationId, String status) {
        return applicationDAO.updateStatus(applicationId, status);
    }
}
