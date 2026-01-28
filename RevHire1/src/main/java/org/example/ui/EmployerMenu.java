package org.example.ui;

import org.example.model.Application;
import org.example.model.Job;
import org.example.model.Resume;
import org.example.model.User;
import org.example.service.ApplicationService;
import org.example.service.JobService;
import org.example.service.UserService;

import java.util.List;

import org.example.service.AuthenticationService;

public class EmployerMenu {
    private User user;
    private JobService jobService;
    private ApplicationService appService;
    private UserService userService;
    private AuthenticationService authService;

    public EmployerMenu(User user) {
        this.user = user;
        this.jobService = new JobService();
        this.appService = new ApplicationService();
        this.userService = new UserService();
        this.authService = new AuthenticationService();
    }

    public void show() {
        while (true) {
            ConsoleUtils.printHeader("Employer Portal - " + user.getName());
            System.out.println("1. Post a Job");
            System.out.println("2. View My Jobs");
            System.out.println("3. Change Password");
            System.out.println("4. Logout");

            int choice = ConsoleUtils.readInt("Enter your choice");

            switch (choice) {
                case 1:
                    handlePostJob();
                    break;
                case 2:
                    handleViewMyJobs();
                    break;
                case 3:
                    handleChangePassword();
                    break;
                case 4:
                    return;
                default:
                    ConsoleUtils.printError("Invalid choice.");
            }
        }
    }

    private void handlePostJob() {
        ConsoleUtils.printHeader("Post a Job");
        String title = ConsoleUtils.readString("Job Title");
        String description = ConsoleUtils.readString("Description");
        String requirements = ConsoleUtils.readString("Requirements");
        String location = ConsoleUtils.readString("Location");
        String salaryRange = ConsoleUtils.readString("Salary Range");
        String jobType = ConsoleUtils.readString("Job Type");
        String experienceLevel = ConsoleUtils.readString("Experience Level");

        if (jobService.postJob(user.getUserId(), title, description, requirements, location, salaryRange, jobType,
                experienceLevel)) {
            ConsoleUtils.printSuccess("Job posted successfully!");
        } else {
            ConsoleUtils.printError("Failed to post job.");
        }
    }

    private void handleViewMyJobs() {
        ConsoleUtils.printHeader("My Jobs");
        List<Job> jobs = jobService.getJobsByEmployer(user.getUserId());
        if (jobs.isEmpty()) {
            System.out.println("You haven't posted any jobs.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s\n", "ID", "Title", "Location");
        for (Job job : jobs) {
            System.out.printf("%-5d %-20s %-15s\n", job.getJobId(), job.getTitle(), job.getLocation());
        }

        System.out.println("\nEnter Job ID to manage applications, or 0 to go back.");
        int jobId = ConsoleUtils.readInt("Job ID");
        if (jobId == 0)
            return;

        boolean isMine = false;
        for (Job j : jobs)
            if (j.getJobId() == jobId)
                isMine = true;

        if (!isMine) {
            ConsoleUtils.printError("Invalid Job ID.");
            return;
        }

        handleManageApplications(jobId);
    }

    private void handleManageApplications(int jobId) {
        ConsoleUtils.printHeader("Applications for Job ID: " + jobId);
        List<Application> apps = appService.getApplicationsForJob(jobId);
        if (apps.isEmpty()) {
            System.out.println("No applications received yet.");
            return;
        }

        System.out.printf("%-5s %-10s %-15s %-20s\n", "AppID", "SeekerID", "Status", "Applied At");
        for (Application app : apps) {
            System.out.printf("%-5d %-10d %-15s %-20s\n", app.getApplicationId(), app.getSeekerId(), app.getStatus(),
                    app.getAppliedAt());
        }

        System.out.println("\nEnter Application ID to view Resume or Update Status, or 0 to go back.");
        int appId = ConsoleUtils.readInt("Application ID");
        if (appId == 0)
            return;

        Application selectedApp = null;
        for (Application app : apps)
            if (app.getApplicationId() == appId)
                selectedApp = app;

        if (selectedApp == null) {
            ConsoleUtils.printError("Invalid Application ID.");
            return;
        }

        System.out.println("1. View Seeker Resume");
        System.out.println("2. Accept Application (Shortlist)");
        System.out.println("3. Reject Application");

        int action = ConsoleUtils.readInt("Choice");
        switch (action) {
            case 1:
                Resume resume = userService.getResume(selectedApp.getSeekerId());
                if (resume != null) {
                    System.out.println("\n--- Resumé ---");
                    System.out.println("Summary: " + resume.getSummary());
                    System.out.println("Education: " + resume.getEducation());
                    System.out.println("Experience: " + resume.getExperience());
                    System.out.println("Skills: " + resume.getSkills());
                } else {
                    System.out.println("No resume found for this seeker.");
                }
                break;
            case 2:
                if (appService.updateApplicationStatus(appId, "SHORTLISTED")) {
                    ConsoleUtils.printSuccess("Application Shortlisted.");
                }
                break;
            case 3:
                if (appService.updateApplicationStatus(appId, "REJECTED")) {
                    ConsoleUtils.printSuccess("Application Rejected.");
                }
                break;
            default:
                break;
        }
    }

    private void handleChangePassword() {
        ConsoleUtils.printHeader("Change Password");
        String oldPassword = ConsoleUtils.readPassword("Old Password");
        String newPassword = ConsoleUtils.readPassword("New Password");

        if (authService.changePassword(user.getEmail(), oldPassword, newPassword)) {
            ConsoleUtils.printSuccess("Password changed successfully.");
        } else {
            ConsoleUtils.printError("Failed to change password. Old password might be incorrect.");
        }
    }
}
