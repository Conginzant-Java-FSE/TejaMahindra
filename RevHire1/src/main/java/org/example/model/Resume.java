package org.example.model;

public class Resume {
    private int resumeId;
    private int seekerId;
    private String summary;
    private String education;
    private String experience;
    private String skills;

    public Resume() {
    }

    public Resume(int seekerId, String summary, String education, String experience, String skills) {
        this.seekerId = seekerId;
        this.summary = summary;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public Resume(int resumeId, int seekerId, String summary, String education, String experience, String skills) {
        this.resumeId = resumeId;
        this.seekerId = seekerId;
        this.summary = summary;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(int seekerId) {
        this.seekerId = seekerId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
