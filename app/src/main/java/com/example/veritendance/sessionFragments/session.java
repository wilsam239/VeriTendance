package com.example.veritendance.sessionFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.veritendance.MainActivity;
import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class session extends Fragment implements View.OnClickListener {
    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yy hh:mm a");
    private Date endTime;
    private Date startTime;

    private String startTimeStr;
    private String endTimeStr;
    private String sessionName;
    private ArrayList<employee> attendees = new ArrayList<>();
    private ArrayList<Pair<employee, Integer>> scores = new ArrayList<>();

    protected TextView sessionPlaceholder;
    MainActivity main;
    public newSessionFragment parentFragment;

    public session(newSessionFragment p) { parentFragment = p;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_empty_session, parent, false);
        startTime = new Date(System.currentTimeMillis());
        startTimeStr = formatter.format(startTime);
        sessionPlaceholder = (TextView) view.findViewById(R.id.fragmentTitle);
        sessionPlaceholder.setText(startTimeStr.contains("PM") ? "Afternoon Session" : "Morning Session");
        Button finishSession = (Button) view.findViewById(R.id.finishSession);
        finishSession.setTextColor(Color.parseColor("#ff0000"));
        if(attendees.size() != 0) {
            finishSession.setTextColor(Color.parseColor("#000000"));
            finishSession.setOnClickListener(this);
        }

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    @Override
    public void onClick(View v) {
        endTime = new Date(System.currentTimeMillis());
        endTimeStr = formatter.format(endTime);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new sessionSummary(this, parentFragment.historyTab, parentFragment.topicsTab)).commit();
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getEndTimeStr() { return endTimeStr; }

    public String getSessionName() { return sessionName; }

    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public void setScores() {
        for(int i = 0; i < attendees.size(); i++) {
            scores.add(new Pair(attendees.get(i), 0));
        }
    }

    public int getPercentageComplete() {
        int total = 0;
        for(int i = 0; i < scores.size(); i++) {
            if(scores.get(i).second >= 50) total += scores.get(i).second;
        }
        return total/scores.size();
    }

    public int getAttendeeCount() { return attendees.size(); }
}
