package com.example.veritendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class topicsFragment extends Fragment {
    ArrayList<String> topicList;

    public topicsFragment() {
        topicList = new ArrayList<>();
        topicList.add("PPS JobReady using the Support Tracker");
        topicList.add("PPS - ESS Web");
        topicList.add("PaTH Internships");
        topicList.add("WfD Activity Management and Monitoring");
        topicList.add("Risk Assessment - Jobseeker");
        topicList.add("WfD Risk Assessment (Place)");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_topics, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
    }

    public void appendTopic(String newTopic) {
        if(!topicList.contains(newTopic)) topicList.add(newTopic);
    }
}
