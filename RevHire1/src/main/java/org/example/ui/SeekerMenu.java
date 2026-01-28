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

public class SeekerMenu {
    private User user;
    private UserService userService;
    private JobService jobService;
    private ApplicationService appService;
    private AuthenticationService authService;

    public SeekerMenu(User user) {
        this.user = user;
        this.userService = new UserService();
        this.jobService = new JobService();
        this.appService = new ApplicationService();
        this.authService = new AuthenticationService();
    }

    public void show() {
        while (true) {
            ConsoleUtils.printHeader("Job Seeker Portal - " + user.getName());
            System.out.println("1. Create/Update Resume");
            System.out.println("2. Search Jobs");
            System.out.println("3. View My Applications");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");

            int choice = ConsoleUtils.readInt("Enter your choice");

            switch (choice) {
                case 1:
                    handleResume();
                    break;
                case 2:
                    handleSearchJobs();
                    break;
                case 3:
                    handleViewApplications();
                    break;
                case 4:
                    handleChangePassword();
                    break;
                case 5:
                    return;
                default:
                    ConsoleUtils.printError("Invalid choice.");
            }
        }
    }

    private void handleResume() {
        ConsoleUtils.printHeader("Manage Resume");
        Resume currentResume = userService.getResume(user.getUserId());
        if (currentResume != null) {
            System.out.println("Current Summary: " + currentResume.getSummary());
        }

        String summary = ConsoleUtils.readString("Summary");
        String education = ConsoleUtils.readString("Education");
        String experience = ConsoleUtils.readString("Experience");
        String skills = ConsoleUtils.readString("Skills");

        if (userService.upsertResume(user.getUserId(), summary, education, experience, skills)) {
            ConsoleUtils.printSuccess("Resume updated successfully.");
        } else {
            ConsoleUtils.printError("Failed to update resume.");
        }
    }

    private void handleSearchJobs() {
        ConsoleUtils.printHeader("Search Jobs");

        System.out.println("1. List All Jobs");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Location");
        System.out.println("4. Search by Type");
        System.out.println("5. Search by Company");
        System.out.println("6. Search by Experience");

        int choice = ConsoleUtils.readInt("Enter choice");
        List<Job> jobs = null;

        switch (choice) {
            case 1:
                jobs = jobService.getAllJobs();
                break;
            case 2:
                String title = ConsoleUtils.readString("Enter title keyword");
                jobs = jobService.searchJobs("title", title);
                break;
            case 3:
                String location = ConsoleUtils.readString("Enter location");
                jobs = jobService.searchJobs("location", location);
                break;
            case 4:
                String type = ConsoleUtils.readString("Enter job type");
                jobs = jobService.searchJobs("type", type);
                break;
            case 5:
                String company = ConsoleUtils.readString("Enter company name");
                jobs = jobService.searchJobs("company", company);
                break;
            case 6:
                String experience = ConsoleUtils.readString("Enter experience level");
                jobs = jobService.searchJobs("experience", experience);
                break;
            default:
                jobs = jobService.getAllJobs();
        }

        if (jobs == null || jobs.isEmpty()) {
            System.out.println("No jobs found.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-15s\n", "ID", "Title", "Location", "Type");
        for (Job job : jobs) {
            System.out.printf("%-5d %-20s %-15s %-15s\n", job.getJobId(), job.getTitle(), job.getLocation(),
                    job.getJobType());
        }

        System.out.println("\nEnter Job ID to view details and apply, or 0 to go back.");
        int jobId = ConsoleUtils.readInt("Job ID");
        if (jobId == 0)
            return;

        Job job = jobService.getJobById(jobId);
        if (job != null) {
            System.out.println("\n--- Job Details ---");
            System.out.println("Title: " + job.getTitle());
            System.out.println("Description: " + job.getDescription());
            System.out.println("Requirements: " + job.getRequirements());
            System.out.println("Salary: " + job.getSalaryRange());

            String action = ConsoleUtils.readString("Apply for this job? (y/n)");
            if ("y".equalsIgnoreCase(action)) {
                if (appService.applyForJob(jobId, user.getUserId())) {
                    ConsoleUtils.printSuccess("Applied successfully!");
                } else {
                    ConsoleUtils.printError("Failed to apply. You may have already applied.");
                }
            }
        } else {
            ConsoleUtils.printError("Job not found.");
        }
    }

    private void handleViewApplications() {
        ConsoleUtils.printHeader("My Applications");
        List<Application> apps = appService.getMyApplications(user.getUserId());
        if (apps.isEmpty()) {
            System.out.println("You haven't applied to any jobs.");
            return;
        }

        System.out.printf("%-5s %-10s %-15s\n", "AppID", "JobID", "Status");
        for (Application app : apps) {
            System.out.printf("%-5d %-10d %-15s\n", app.getApplicationId(), app.getJobId(), app.getStatus());
        }

        System.out.println("\nEnter Application ID to withdraw, or 0 to go back.");
        int appId = ConsoleUtils.readInt("Application ID");
        if (appId == 0)
            return;

        boolean isMine = false;
        for (Application app : apps) {
            if (app.getApplicationId() == appId) {
                isMine = true;
                if ("WITHDRAWN".equalsIgnoreCase(app.getStatus())) {
                    ConsoleUtils.printError("Application is already withdrawn.");
                    return;
                }
            }
        }

        if (!isMine) {
            ConsoleUtils.printError("Invalid Application ID.");
            return;
        }

        if (appService.updateApplicationStatus(appId, "WITHDRAWN")) {
            ConsoleUtils.printSuccess("Application withdrawn successfully.");
        } else {
            ConsoleUtils.printError("Failed to withdraw application.");
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
