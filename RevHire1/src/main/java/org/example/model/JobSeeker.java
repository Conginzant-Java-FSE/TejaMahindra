package org.example.model;

public class JobSeeker extends User {
    private String phone;

    public JobSeeker() {
    }

    public JobSeeker(String name, String email, String password, String phone) {
        super(name, email, password, "SEEKER");
        this.phone = phone;
    }

    public JobSeeker(int userId, String name, String email, String password, String phone) {
        super(userId, name, email, password, "SEEKER");
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
