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
import android.widget.TextView;

import com.example.veritendance.R;

import java.util.ArrayList;

public class employeeFragment extends Fragment implements View.OnClickListener {
    /**
     * Employee Screen Fragment
     * Shows the main_employees layout
     */
    private RecyclerView employees;
    private RecyclerView.Adapter adapter;
    private ArrayList<employee> employeeList;

    public employeeFragment() {
        employeeList = new ArrayList<>();
        initialiseEmployees();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_employees, parent, false);

        // Create the floating action button and set it a listener
        FloatingActionButton addNewEmployee = view.findViewById(R.id.newEmployeeButton);
        addNewEmployee.setOnClickListener(this);

        TextView noEmployees = view.findViewById(R.id.noEmployees);
        noEmployees.setVisibility(employeeList.size() == 0 ? View.VISIBLE : View.INVISIBLE);

        // Create the recycler view and connect it to adapter and layout manager
        this.employees = view.findViewById(R.id.employees);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.employees.setLayoutManager(mLayoutManager);
        adapter = new employeeAdapter(employeeList, this);
        this.employees.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new newEmployeeFragment(this)).commit();
    }

    // Returnb the list of employees
    public ArrayList<employee> getEmployeesList() {
        return employeeList;
    }

    // Append a new employee to the list
    public void appendEmployee(employee newEmployee) {
        this.employeeList.add(newEmployee);
    }

    // Modify the employee at the parsed index
    public void changeEmployee(employee changedEmployee, int i) { this.employeeList.set(i, changedEmployee); }

    public void removeEmployee(employee employeeToBeRemoved) {
        // Remove an employee and refresh the fragment
        this.employeeList.remove(employeeToBeRemoved);
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    public void initialiseEmployees() {
        // Create a few employees for startup
        this.employeeList.add(new employee("Sam Williamson", "sam.williamson@best.com.au", "Support Officer"));
        this.employeeList.add(new employee("Steve Rogers", "steve.rogers@avengers.com.au", "Captain America"));
        this.employeeList.add(new employee("Tony Stark", "tony.stark@starkindustries.com.au", "Iron Man"));
        this.employeeList.add(new employee("Peter Parker", "peter.parker@starkindustries.com.au", "Student/Intern"));

    }
}
