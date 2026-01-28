package org.example.model;

public class Employer extends User {
    private String companyName;
    private String industry;
    private String location;

    public Employer() {
    }

    public Employer(String name, String email, String password, String companyName, String industry, String location) {
        super(name, email, password, "EMPLOYER");
        this.companyName = companyName;
        this.industry = industry;
        this.location = location;
    }

    public Employer(int userId, String name, String email, String password, String companyName, String industry,
            String location) {
        super(userId, name, email, password, "EMPLOYER");
        this.companyName = companyName;
        this.industry = industry;
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
