# RevHire - Job Portal Console Application

RevHire is a straightforward, console-based job portal application designed to connect job seekers with employers.

## Features

### Role-Based Access
- **Job Seekers**: Create profile, upload resume, search jobs, apply for jobs, track applications.
- **Employers**: Register company, post jobs, view applications, shortlist/reject candidates.

### Tech Stack
- **Language**: Java 21
- **Database**: MySQL
- **Build Tool**: Maven

## Setup Instructions

### 1. Database Configuration
1.  Ensure you have MySQL installed and running.
2.  Create a database named `revhire1`:
    ```sql
    CREATE DATABASE revhire;
    ```

3.  Update the database credentials in `src/main/java/org/example/dao/DBConnection.java`:
    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/revhire";
    private static final String USER = "YOUR_USERNAME";
    private static final String PASSWORD = "YOUR_PASSWORD";
    ```

### 2. Build and Run
1.  Open the project in your favorite IDE (IntelliJ IDEA recommended).
2.  Import as a Maven project.
3.  Run `mvn clean install` to build.
4.  Run the `org.example.Main` class to start the application.

## Project Structure
- `org.example.model`: Data entities (User, Job, Application, etc.)
- `org.example.dao`: Data Access Objects for database interaction.
- `org.example.service`: Business logic layer.
- `org.example.ui`: Console user interface menus.

