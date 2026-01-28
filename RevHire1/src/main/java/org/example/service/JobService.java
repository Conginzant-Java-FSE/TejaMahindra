package org.example.service;

import org.example.dao.JobDAO;
import org.example.model.Job;
import java.util.List;

public class JobService {
    private JobDAO jobDAO;

    public JobService() {
        this.jobDAO = new JobDAO();
    }

    public boolean postJob(int employerId, String title, String description, String requirements, String location,
            String salaryRange, String jobType, String experienceLevel) {
        Job job = new Job(employerId, title, description, requirements, location, salaryRange, jobType,
                experienceLevel);
        return jobDAO.postJob(job);
    }

    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    public List<Job> getJobsByEmployer(int employerId) {
        return jobDAO.getJobsByEmployer(employerId);
    }

    public Job getJobById(int jobId) {
        return jobDAO.getJobById(jobId);
    }

    public List<Job> searchJobs(String criterion, String keyword) {
        return jobDAO.searchJobs(criterion, keyword);
    }
}
