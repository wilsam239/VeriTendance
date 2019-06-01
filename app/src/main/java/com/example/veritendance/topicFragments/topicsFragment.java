package com.example.veritendance.topicFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.veritendance.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        Collections.sort(topicList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_topics, parent, false);

        TextView noTopics = view.findViewById(R.id.noTopicsMsg);
        noTopics.setVisibility(topicList.size() == 0 ? View.VISIBLE : View.INVISIBLE);

        this.topics = view.findViewById(R.id.topics);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.topics.setLayoutManager(mLayoutManager);
        adapter = new topicAdapter(topicList, this);
        this.topics.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public void appendTopic(String newTopic) {
        if (!topicList.contains(newTopic)) topicList.add(newTopic);
        Collections.sort(topicList);
    }

    public void removeTopic(String oldTopic) {
        topicList.remove(oldTopic);
        Collections.sort(topicList);
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }
}
