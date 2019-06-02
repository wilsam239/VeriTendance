package com.example.veritendance.sessionFragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressLint("ValidFragment")
public class sessionFragment extends Fragment implements View.OnClickListener {
    /**
     * Session screen fragment
     * Shows the sub_session_started layout
     */

    // Recycler view stuff
    private RecyclerView attendeesView;
    private RecyclerView.Adapter adapter;

    // The session in progress
    private session currentSession;
    // Date formatter
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm a");

    // List of attendees
    private ArrayList<employee> attendees = new ArrayList<>();

    // Text label
    protected TextView sessionPlaceholder;
    // The parent fragment
    public newSessionFragment parentFragment;
    // Bool to assist determining which adapter to use
    public boolean addAdapter = false;

    /**
     * Constructor
     * @param p - parent newSessionFragment
     * @param e - list of employees
     */
    public sessionFragment(newSessionFragment p, ArrayList<employee> e) {
        parentFragment = p;
        attendees.addAll(e);
        currentSession = new session(attendees);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_session_started, parent, false);

        // Set the start date of the session
        currentSession.setStartDate(new Date(System.currentTimeMillis()));
        currentSession.setStartTime(formatter.format(currentSession.getStartDate()));

        // Set the text in the session placeholder
        sessionPlaceholder = view.findViewById(R.id.fragmentTitle);
        sessionPlaceholder.setText(currentSession.getStartTime().contains("PM") ? "Afternoon Session" : "Morning Session");

        // Create the no session message label and set its visibility
        TextView noSession = (TextView) view.findViewById(R.id.noAttendeesMsg);
        noSession.setVisibility(currentSession.getAttendeeCount() == 0 ? View.VISIBLE : View.INVISIBLE);

        // Create the button to finish a session
        Button finishSession = view.findViewById(R.id.finishSession);

        // Create the add attendee button and show or hide it ass necessary
        FloatingActionButton addAttendee = (FloatingActionButton) view.findViewById(R.id.newAttendeeFloatingButton);
        if(currentSession.getAttendees().equals(parentFragment.getEmployees())) {
            addAttendee.hide();
        } else {
            addAttendee.show();
            addAttendee.setOnClickListener(this);
        }

        // Set the colour and listener of the finish session button based on the attendee count
        finishSession.setTextColor(Color.parseColor("#ff0000"));
        if (currentSession.getAttendeeCount() != 0) {
            finishSession.setTextColor(Color.parseColor("#000000"));
            finishSession.setOnClickListener(this);
        }

        // Set the recycler view and assign lkayout manager and adapter
        this.attendeesView = view.findViewById(R.id.attendees);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.attendeesView.setLayoutManager(mLayoutManager);

        if(!addAdapter) adapter = new employeeRemoveNoEditAdapter(currentSession.getAttendees(), this);
        this.attendeesView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Add attendeee or finish session depending on button pressed
        switch (v.getId()) {
            case R.id.newAttendeeFloatingButton:
                adapter = new employeeAddAttendeeAdapter(parentFragment.getEmployees(), this);
                this.attendeesView.setAdapter(adapter);
                FloatingActionButton addAttendee = (FloatingActionButton) v.findViewById(R.id.newAttendeeFloatingButton);
                addAttendee.hide();
                addAdapter = true;
                break;
            case R.id.finishSession:
                currentSession.setDefaultScores();
                currentSession.setEndDate(new Date(System.currentTimeMillis()));
                currentSession.setEndTime(formatter.format(currentSession.getEndDate()));
                // Begin a fragment transaction
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new sessionSummaryFragment(this, currentSession, parentFragment.historyTab, parentFragment.topicsTab)).commit();
                break;
        }
    }

    // Add attendee to session
    public void addAttendee(employee newAttendee) {
        currentSession.addAttendee(newAttendee);
    }
    // Remove attendee from session and refresh screen
    public void removeAttendee(employee attendeeToBeRemoved) {
        currentSession.removeAttendee(attendeeToBeRemoved);
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    // return the attendees
    public ArrayList<employee> getAttendees() {
        return currentSession.getAttendees();
    }
}
