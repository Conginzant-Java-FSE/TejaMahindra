package org.example.model;

import java.sql.Timestamp;

public class Job {
    private int jobId;
    private int employerId;
    private String title;
    private String description;
    private String requirements;
    private String location;
    private String salaryRange;
    private String jobType;
    private String experienceLevel;
    private Timestamp postedAt;

    public Job() {
    }

    public Job(int employerId, String title, String description, String requirements, String location,
            String salaryRange, String jobType, String experienceLevel) {
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryRange = salaryRange;
        this.jobType = jobType;
        this.experienceLevel = experienceLevel;
    }

    public Job(int jobId, int employerId, String title, String description, String requirements, String location,
            String salaryRange, String jobType, String experienceLevel, Timestamp postedAt) {
        this.jobId = jobId;
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryRange = salaryRange;
        this.jobType = jobType;
        this.experienceLevel = experienceLevel;
        this.postedAt = postedAt;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Timestamp getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Timestamp postedAt) {
        this.postedAt = postedAt;
    }
}
