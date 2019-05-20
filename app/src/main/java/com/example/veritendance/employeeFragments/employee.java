package com.example.veritendance.employeeFragments;

public class employee {

    private String name;
    private String email;
    private String occupation;

    public employee() {
        name = "John Doe";
        email = "john.doe@best.com.au";
        occupation = "Job Coach";
    }

    public employee(String name, String email, String occupation) {
        this.name = name;
        this.email = email;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

}
