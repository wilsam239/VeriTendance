package com.example.veritendance.topicFragments;

public class topic {

    private String title;
    private String description;

    public topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public topic(String title) {
        this.title = title;
        this.description = "No description entered yet";
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

}
