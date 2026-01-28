package org.example.ui;

import org.example.model.User;
import org.example.service.AuthenticationService;

public class MainMenu {
    private AuthenticationService authService;

    public MainMenu() {
        this.authService = new AuthenticationService();
    }

    public void show() {
        while (true) {
            ConsoleUtils.printHeader("RevHire - Welcome");
            System.out.println("1. Login");
            System.out.println("2. Register as Job Seeker");
            System.out.println("3. Register as Employer");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");

            int choice = ConsoleUtils.readInt("Enter your choice");

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegisterSeeker();
                    break;
                case 3:
                    handleRegisterEmployer();
                    break;
                case 4:
                    handleForgotPassword();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    ConsoleUtils.printError("Invalid choice.");
            }
        }
    }

    private void handleLogin() {
        ConsoleUtils.printHeader("Login");
        String email = ConsoleUtils.readString("Email");
        String password = ConsoleUtils.readPassword("Password");

        User user = authService.login(email, password);
        if (user != null) {
            ConsoleUtils.printSuccess("Login successful! Welcome, " + user.getName());
            if ("SEEKER".equalsIgnoreCase(user.getRole())) {
                new SeekerMenu(user).show();
            } else if ("EMPLOYER".equalsIgnoreCase(user.getRole())) {
                new EmployerMenu(user).show();
            }
        } else {
            ConsoleUtils.printError("Invalid credentials.");
        }
    }

    private void handleRegisterSeeker() {
        ConsoleUtils.printHeader("Register Job Seeker");
        String name = ConsoleUtils.readString("Name");
        String email = ConsoleUtils.readString("Email");
        String password = ConsoleUtils.readPassword("Password");
        String phone = ConsoleUtils.readString("Phone Number");

        if (authService.registerJobSeeker(name, email, password, phone)) {
            ConsoleUtils.printSuccess("Registration successful! Please login.");
        } else {
            ConsoleUtils.printError("Registration failed. Email might already exist.");
        }
    }

    private void handleRegisterEmployer() {
        ConsoleUtils.printHeader("Register Employer");
        String name = ConsoleUtils.readString("Name");
        String email = ConsoleUtils.readString("Email");
        String password = ConsoleUtils.readPassword("Password");
        String companyName = ConsoleUtils.readString("Company Name");
        String industry = ConsoleUtils.readString("Industry");
        String location = ConsoleUtils.readString("Location");

        if (authService.registerEmployer(name, email, password, companyName, industry, location)) {
            ConsoleUtils.printSuccess("Registration successful! Please login.");
        } else {
            ConsoleUtils.printError("Registration failed. Email might already exist.");
        }
    }

    private void handleForgotPassword() {
        ConsoleUtils.printHeader("Forgot Password");
        String email = ConsoleUtils.readString("Enter your registered email");
        String newPassword = ConsoleUtils.readPassword("Enter new password");

        if (authService.resetPassword(email, newPassword)) {
            ConsoleUtils.printSuccess("Password reset successful! Please login with new password.");
        } else {
            ConsoleUtils.printError("Email not found.");
        }
    }
}
