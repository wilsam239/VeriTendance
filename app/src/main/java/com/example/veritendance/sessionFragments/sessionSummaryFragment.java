package com.example.veritendance.sessionFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.MainActivity;
import com.example.veritendance.employeeFragments.employee;
import com.example.veritendance.historyFragments.historyFragment;

import com.example.veritendance.R;
import com.example.veritendance.historyFragments.pairsAdapter;
import com.example.veritendance.topicFragments.topicsFragment;

import java.text.SimpleDateFormat;

@SuppressLint("ValidFragment")
public class sessionSummaryFragment extends Fragment implements View.OnClickListener {
    /**
     * Session Summary fragment
     * Shows the sub_session_summary layout
     */

    // Recycler view stuff
    private RecyclerView attendees;
    private RecyclerView.Adapter adapter;

    // Items in the view
    private TextView startTime;
    private TextView endTime;
    private TextView endTimeTop;
    private TextView sessionName;
    private TextView sessionNameTop;
    private TextView attendessLabel;
    private CheckBox save;

    // Session being summarised
    private session concludedSession;
    // Parent fragment
    private sessionFragment parent;

    // Date formatter
    private SimpleDateFormat formatter = new SimpleDateFormat("dd MMM. yyyy hh:mm:ss a");

    // The history and topics fragment
    public historyFragment h;
    public topicsFragment t;

    /**
     * Catch all constructor
     * @param frag - parent fragment
     * @param s - session being summarised
     * @param h - History fragment
     * @param t - Topics fragment
     */
    public sessionSummaryFragment(sessionFragment frag, session s, historyFragment h, topicsFragment t) {
        concludedSession = s;
        this.h = h;
        this.t = t;
        parent = frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_session_summary, parent, false);

        // Create all the TextViews and buttons
        save = (CheckBox) view.findViewById(R.id.saveSession);

        startTime = view.findViewById(R.id.startTime);
        startTime.setText(concludedSession.getStartTime());

        endTime = view.findViewById(R.id.endTime);
        endTime.setText(concludedSession.getEndTime());

        endTimeTop = view.findViewById(R.id.endTimePlaceholderTop);
        endTimeTop.setText(formatter.format(concludedSession.getEndDate()));

        sessionName = view.findViewById(R.id.sessionName);
        // If the session start time has PM set it to "Afternoon Session"
        sessionName.setText(concludedSession.getStartTime().contains("PM") ? "Afternoon Session" : "Morning Session");

        attendessLabel = view.findViewById(R.id.summaryAttendeesLabel);
        attendessLabel.setText(String.valueOf(concludedSession.getAttendeeCount()).concat(" Attendees"));

        sessionNameTop = view.findViewById(R.id.sessionPlaceholder);
        sessionNameTop.setText(((TextView) view.findViewById(R.id.sessionName)).getText().toString());

        // Creates a TextWatcher so that when the sessionName is changed, it updates the top label
        TextWatcher sessionNameTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sessionNameTop.setText(s.toString());
            }
        };

        sessionName.addTextChangedListener(sessionNameTextWatcher);

        ImageButton submitSession = view.findViewById(R.id.submitSessionSummary);
        submitSession.setOnClickListener(this);

        // Set recycler view and assign a layout manager and adapter
        this.attendees = view.findViewById(R.id.summaryAttendees);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.attendees.setLayoutManager(mLayoutManager);
        adapter = new pairsAdapter(concludedSession.getScores(), this);
        this.attendees.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Set the title of the session to the label at top of screen
        concludedSession.setTitle(sessionNameTop.getText().toString());

        // If the user chcked the save session as topic, add it to the topic list
        if(save.isChecked()) t.appendTopic(sessionNameTop.getText().toString());
        // Append the session to list of completed sessions
        h.appendSession(concludedSession);

        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parent.parentFragment).commit();
    }

    public void changeScores(Pair<employee, Integer> newScore, int index) {
        // Modify scores at the index
        concludedSession.changeScore(newScore, index);
    }
}
