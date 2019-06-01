package com.example.veritendance.historyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.employee;
import com.example.veritendance.sessionFragments.session;

import java.util.ArrayList;


public class modifySession extends Fragment implements View.OnClickListener {
    /**
     * Modify Session fragment
     * Allows the user to modify a past session
     */
    private RecyclerView pairs;
    private RecyclerView.Adapter adapter;
    private ArrayList<Pair<employee, Integer>> scores;

    public historyFragment parentFragment;
    //private View view;

    private EditText title;
    private EditText startDate;
    private EditText endDate;
    private ImageButton submitSession;

    private session editing;
    private int index;

    public modifySession(historyFragment p, session s, int index) {
        parentFragment = p;
        editing = s;
        this.index = index;
        scores = editing.getScores();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_session_modify, parent, false);



        title = view.findViewById(R.id.sessionNameInput);
        startDate = view.findViewById(R.id.editStartDate);
        endDate = view.findViewById(R.id.editEndDate);
        submitSession = view.findViewById(R.id.submitModifiedSession);

        submitSession.setOnClickListener(this);

        title.setText(editing.getTitle());
        startDate.setText(editing.getStartTime());
        endDate.setText(editing.getEndTime());

        this.pairs = view.findViewById(R.id.attendeesScores);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.pairs.setLayoutManager(mLayoutManager);
        adapter = new pairsAdapter(scores, this);
        this.pairs.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        parentFragment.changeSession(new session(title.getText().toString(), startDate.getText().toString(), endDate.getText().toString(),editing.getAttendees(), editing.getScores()), index);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }

    public void changeScores(Pair<employee, Integer> newScore, int index) {
        editing.changeScore(newScore, index);
    }
}
