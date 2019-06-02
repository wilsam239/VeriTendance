package com.example.veritendance.sessionFragments;

import android.util.Pair;

import com.example.veritendance.employeeFragments.employee;

import java.util.ArrayList;
import java.util.Date;

public class session {
    /**
     * Session class
     * Contains a start Time as a String and Date
     * Contains an end time as a String and Date
     * Contains a title
     * Contains a list of attendees and scores
     */
    private String startTime;
    private String endTime;
    private String title;
    private Date startDate;
    private Date endDate;

    private  ArrayList<employee> attendees;
    private ArrayList<Pair<employee, Integer>> scores;

    /**
     * Attendees only constructor
     * @param attendees - list of attendees for the session
     */
    public session(ArrayList<employee> attendees) {
        this.attendees = new ArrayList<>();
        this.attendees.addAll(attendees);
        scores = new ArrayList<>();
    }

    /**
     * Catch all constructor
     */
    public session(String title, String startTime, String endTime, ArrayList<employee> attendees,ArrayList<Pair<employee, Integer>> scores) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scores = scores;
        this.attendees = attendees;
    }

    // Getters and Setters
    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public ArrayList<employee> getAttendees() { return attendees; }

    public void setAttendees(ArrayList<employee> attendees) { this.attendees = attendees; }

    public void removeAttendee(employee attendeeToBeRemoved) { attendees.remove(attendeeToBeRemoved);}

    public void addAttendee(employee newAttendee) { if(!attendees.contains(newAttendee)) attendees.add(newAttendee);}

    public ArrayList<Pair<employee, Integer>> getScores() { return scores; }

    public void setDefaultScores() {
        for (int i = 0; i < attendees.size(); i++) {
            scores.add(new Pair(attendees.get(i), 0));
        }
    }

    public int getPercentageComplete() {
        int total = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i).second >= 50) total++; //scores.get(i).second;
        }
        return Math.round(((float)total / scores.size())*100);
    }
    public int getAttendeeCount() { return attendees.size(); }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public void changeScore(Pair<employee, Integer> newScore, int index) { scores.set(index, newScore); }
}
