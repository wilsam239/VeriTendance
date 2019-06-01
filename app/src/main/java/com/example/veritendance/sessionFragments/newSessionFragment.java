package com.example.veritendance.sessionFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    @SuppressLint("ValidFragment")
    public newSessionFragment(historyFragment p, topicsFragment t, ArrayList<employee> employees) {
        historyTab = p;
        topicsTab = t;
        this.employees = employees;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_session, parent, false);
        TextView noEmployee = view.findViewById(R.id.noEmployeesSession);
        noEmployee.setVisibility(employees.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        Button addEmptySession = view.findViewById(R.id.addEmptySessionButton);
        addEmptySession.setVisibility(employees.size() == 0 ? View.INVISIBLE : View.VISIBLE);
        if(employees.size() != 0) addEmptySession.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new sessionFragment(this, employees)).commit();
    }
}

