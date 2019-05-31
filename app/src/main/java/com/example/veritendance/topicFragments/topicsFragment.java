package com.example.veritendance.topicFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.veritendance.R;

import java.util.ArrayList;

public class topicsFragment extends Fragment {
    private RecyclerView topics;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> topicList;

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
        View view = inflater.inflate(R.layout.activity_topics, parent, false);

        this.topics = view.findViewById(R.id.topics);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.topics.setLayoutManager(mLayoutManager);
        adapter = new topicAdapter(topicList);
        this.topics.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public void appendTopic(String newTopic) {
        if (!topicList.contains(newTopic)) topicList.add(newTopic);
    }
}
