package com.example.veritendance.topicFragments;

public class topic implements Comparable<topic>{
    /**
     * Topic class
     * Implements Comparable
     * Topic objects have a title and a description
     */

    private String title;
    private String description;

    /**
     * Title and description constructor
     * @param title - the topic title
     * @param description - the topic description
     */
    public topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Title only constructor
     * @param title - the topic title
     */
    public topic(String title) {
        this.title = title;
        this.description = "No description entered yet";
    }

    // Getters and Setters
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    // this class implements the Comparable interface
    // Compare a topic object to another by comparing their titles.
    @Override
    public int compareTo(topic o) { return this.getTitle().compareTo(o.getTitle()); }
}
