package org.example.model;

import java.sql.Timestamp;

public class Application {
    private int applicationId;
    private int jobId;
    private int seekerId;
    private String status;
    private Timestamp appliedAt;

    public Application() {
    }

    public Application(int jobId, int seekerId) {
        this.jobId = jobId;
        this.seekerId = seekerId;
        this.status = "APPLIED";
    }

    public Application(int applicationId, int jobId, int seekerId, String status, Timestamp appliedAt) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.seekerId = seekerId;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(int seekerId) {
        this.seekerId = seekerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Timestamp appliedAt) {
        this.appliedAt = appliedAt;
    }
}
