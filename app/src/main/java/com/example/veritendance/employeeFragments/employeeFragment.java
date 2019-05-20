package com.example.veritendance.employeeFragments;

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

import com.example.veritendance.MainActivity;
import com.example.veritendance.R;
import com.example.veritendance.sessionFragments.emptySession;

import java.util.ArrayList;
import java.util.List;

public class employeeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView employees;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layourManager;
    private List employeesList = new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_employees, parent, false);
        FloatingActionButton addNewEmployee = (FloatingActionButton) view.findViewById(R.id.newEmployeeButton);
        addNewEmployee.setOnClickListener(this);
        employees = (RecyclerView) view.findViewById(R.id.employees);




        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
    }
    @Override
    public void onClick(View v) {
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new newEmployee()).commit();
    }
}
