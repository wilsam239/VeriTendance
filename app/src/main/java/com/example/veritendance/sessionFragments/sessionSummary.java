package com.example.veritendance.sessionFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.MainActivity;
import com.example.veritendance.historyFragments.historyFragment;

import com.example.veritendance.R;
import com.example.veritendance.topicFragments.topicsFragment;

import java.text.SimpleDateFormat;

@SuppressLint("ValidFragment")
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
    private CheckBox save;
    private session concludedSession;
    private sessionFragment parent;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd MMM. yyyy hh:mm:ss a");

    public MainActivity main;
    public historyFragment h;
    public topicsFragment t;

    public sessionSummary(sessionFragment frag, session s, historyFragment h, topicsFragment t) {
        concludedSession = s;
        this.h = h;
        this.t = t;
        parent = frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_session_summary, parent, false);

        save = (CheckBox) view.findViewById(R.id.saveSession);

        startTime = view.findViewById(R.id.startTime);
        startTime.setText(concludedSession.getStartTime());

        endTime = view.findViewById(R.id.endTime);
        endTime.setText(concludedSession.getEndTime());

        endTimeTop = view.findViewById(R.id.endTimePlaceholderTop);
        endTimeTop.setText(formatter.format(concludedSession.getEndDate()));

        sessionName = view.findViewById(R.id.sessionName);
        sessionName.setText(concludedSession.getStartTime().contains("PM") ? "Afternoon Session" : "Morning Session");


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
        concludedSession.setTitle(sessionNameTop.getText().toString());
        concludedSession.setDefaultScores();

        if(save.isChecked()) t.appendTopic(sessionNameTop.getText().toString());
        h.appendSession(concludedSession);

        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parent.parentFragment).commit();
    }

}
