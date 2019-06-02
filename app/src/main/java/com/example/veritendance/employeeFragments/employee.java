package com.example.veritendance.employeeFragments;

public class employee {
    /**
     * Employee object
     * Contains a name, email, contractand occupation
     */

    private String name;
    private String email;
    private String occupation;
    private String contract;

    /**
     * Default Constructor
     */
    public employee() {
        name = "John Doe";
        email = "john.doe@best.com.au";
        occupation = "Job Coach";
        contract = "jobactive";
    }

    /**
     *  Constructor taking all 4 parameters
     * @param name - Employee Name
     * @param email - Employee Email
     * @param occupation - Employee occupation
     * @param contract - Employee Contract
     */
    public employee(String name, String email, String occupation, String contract) {
        this.name = name;
        this.email = email;
        this.occupation = occupation;
        this.contract = contract;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

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

    public String getContract() { return contract; }

    public void setContract(String contract) { this.contract = contract; }
}
