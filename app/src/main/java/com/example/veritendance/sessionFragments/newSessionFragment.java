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

@SuppressLint("ValidFragment")
public class newSessionFragment extends Fragment implements View.OnClickListener {

    /**
     * New Session Screen Fragment
     * Shows the main_session layout
     */

    public MainActivity parent;
    public historyFragment historyTab;
    public topicsFragment topicsTab;

    public ArrayList<employee> getEmployees() {
        return employees;
    }

    private ArrayList<employee> employees;
    private ArrayList<employee> jobactiveStaff;
    private ArrayList<employee> DESStaff;


    public newSessionFragment(historyFragment p, topicsFragment t, ArrayList<employee> employees) {
        historyTab = p;
        topicsTab = t;
        this.employees = employees;
        this.jobactiveStaff = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if(this.employees.get(i).getContract().equals("jobactive")) jobactiveStaff.add(this.employees.get(i));
        }
        this.DESStaff = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if(this.employees.get(i).getContract().equals("DES")) DESStaff.add(this.employees.get(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_session, parent, false);

        TextView noEmployee = view.findViewById(R.id.noEmployeesSession);
        noEmployee.setVisibility(employees.size() == 0 ? View.VISIBLE : View.INVISIBLE);

        Button addEmptySession = view.findViewById(R.id.addEmptySessionButton);
        Button addjobactiveSession = view.findViewById(R.id.addjobactiveSessionButton);
        Button addDESSession = view.findViewById(R.id.addDESSessionButton);

        addEmptySession.setVisibility(employees.size() == 0 ? View.INVISIBLE : View.VISIBLE);
        addjobactiveSession.setVisibility(jobactiveStaff.size() == 0 ? View.INVISIBLE : View.VISIBLE);
        addDESSession.setVisibility(DESStaff.size() == 0 ? View.INVISIBLE : View.VISIBLE);

        if(employees.size() != 0) addEmptySession.setOnClickListener(this);
        if(jobactiveStaff.size() != 0) addjobactiveSession.setOnClickListener(this);
        if(DESStaff.size() != 0) addDESSession.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.addEmptySessionButton:
                ft.replace(R.id.fragment_container, new sessionFragment(this, employees)).commit();
                break;
            case R.id.addjobactiveSessionButton:
                ft.replace(R.id.fragment_container, new sessionFragment(this, jobactiveStaff)).commit();
                break;
            case R.id.addDESSessionButton:
                ft.replace(R.id.fragment_container, new sessionFragment(this, DESStaff)).commit();
                break;
        }
    }
}

