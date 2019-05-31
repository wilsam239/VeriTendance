package com.example.veritendance.sessionFragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class sessionFragment extends Fragment implements View.OnClickListener {
    private RecyclerView attendeesView;
    private RecyclerView.Adapter adapter;
    private session currentSession;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm a");

    private ArrayList<employee> attendees; // = new ArrayList<>();

    protected TextView sessionPlaceholder;
    public newSessionFragment parentFragment;

    @SuppressLint("ValidFragment")
    public sessionFragment(newSessionFragment p, ArrayList<employee> e) {
        parentFragment = p;
        attendees = e;
        currentSession = new session(e);
    }

    public sessionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_empty_session, parent, false);
        currentSession.setStartDate(new Date(System.currentTimeMillis()));
        currentSession.setStartTime(formatter.format(currentSession.getStartDate()));

        sessionPlaceholder = view.findViewById(R.id.fragmentTitle);
        sessionPlaceholder.setText(currentSession.getStartTime().contains("PM") ? "Afternoon Session" : "Morning Session");

        Button finishSession = view.findViewById(R.id.finishSession);

        //FloatingActionButton addAttendee = (FloatingActionButton) view.findViewById(R.id.newAttendeeButton);
        //addAttendee.setOnClickListener(this);

        finishSession.setTextColor(Color.parseColor("#ff0000"));
        if (currentSession.getAttendees().size() != 0) {
            finishSession.setTextColor(Color.parseColor("#000000"));
            finishSession.setOnClickListener(this);
        }

        this.attendeesView = view.findViewById(R.id.attendees);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.attendeesView.setLayoutManager(mLayoutManager);

        adapter = new employeeAdapter(attendees);
        this.attendeesView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.newAttendeeButton:

                break;*/
            case R.id.finishSession:
                currentSession.setEndDate(new Date(System.currentTimeMillis()));
                currentSession.setEndTime(formatter.format(currentSession.getEndDate()));
                //endTime = new Date(System.currentTimeMillis());
                //endTimeStr = formatter.format(endTime);
                // Begin a fragment transaction
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new sessionSummary(this, currentSession, parentFragment.historyTab, parentFragment.topicsTab)).commit();
                break;
        }
    }
}
