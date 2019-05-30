package com.example.veritendance.sessionFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.veritendance.MainActivity;
import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.employee;
import com.example.veritendance.historyFragments.historyFragment;
import com.example.veritendance.topicFragments.topicsFragment;

import java.util.ArrayList;

public class newSessionFragment extends Fragment implements View.OnClickListener {
    public MainActivity parent;
    public historyFragment historyTab;
    public topicsFragment topicsTab;

    private ArrayList<employee> employees;

    public newSessionFragment(MainActivity m) {
        parent = m;
    }

    public newSessionFragment(historyFragment p, topicsFragment t, ArrayList<employee> employees) {
        historyTab = p;
        topicsTab = t;
        this.employees = employees;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new_session, parent, false);
        Button addEmptySession = view.findViewById(R.id.addEmptySessionButton);
        addEmptySession.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new session(this, employees)).commit();
    }
}

