package com.example.veritendance.sessionFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.MainActivity;
import com.example.veritendance.historyFragments.historyFragment;

import com.example.veritendance.R;
import com.example.veritendance.topicFragments.topicsFragment;

import java.text.SimpleDateFormat;

public class sessionSummary extends Fragment implements View.OnClickListener {
    /**
     * Session Summary fragment
     * Shows the activity_session_summary layout
     */
    private TextView startTime;
    private TextView endTime;
    private TextView endTimeTop;
    private TextView sessionName;
    private TextView sessionNameTop;
    private session concludedSession;
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM. yyyy hh:mm:ss a");
    MainActivity main;
    public historyFragment h;
    public topicsFragment t;

    public sessionSummary(session sesh, historyFragment h, topicsFragment t) {
        this.concludedSession = sesh;
        this.h = h;
        this.t = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_session_summary, parent, false);

        startTime = view.findViewById(R.id.startTime);
        startTime.setText(concludedSession.getStartTimeStr());

        endTime = view.findViewById(R.id.endTime);
        endTime.setText(concludedSession.getEndTimeStr());

        endTimeTop = view.findViewById(R.id.endTimePlaceholderTop);
        endTimeTop.setText(formatter.format(concludedSession.getEndTime()));

        sessionName = view.findViewById(R.id.sessionName);
        sessionName.setText(concludedSession.getStartTimeStr().contains("PM") ? "Afternoon Session" : "Morning Session");


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

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        concludedSession.setSessionName(sessionNameTop.getText().toString());
        concludedSession.setScores();
        h.appendSession(concludedSession);
        t.appendTopic(sessionNameTop.getText().toString());

        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, concludedSession.parentFragment).commit();
    }

}
