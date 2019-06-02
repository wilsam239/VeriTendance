package com.example.veritendance.employeeFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.veritendance.R;

@SuppressLint("ValidFragment")
public class modifyEmployeeFragment extends Fragment implements View.OnClickListener {
    /**
     * Modify employee fragment
     * Allows the user to modify an existing employee
     * Shows the sub_employees_modify layout
     */

    // Parent fragment
    employeeFragment parentFragment;

    // The fields to be displayed on the screen
    private EditText name;
    private EditText email;
    private EditText occupation;
    private EditText contract;

    // Employee being edited and their index
    private employee editing;
    private int index;

    /**
     * Constructor
     * @param p - parent employeeFragment
     * @param e - employee being edited
     * @param index - index of the employee
     */
    public modifyEmployeeFragment(employeeFragment p, employee e, int index) {
        parentFragment = p;
        editing = e;
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_employees_modify, parent, false);

        // Create the button and set its listener
        ImageButton submitEmployee = view.findViewById(R.id.submitEmployee);
        submitEmployee.setOnClickListener(this);

        // Create the fields to be displayed and edited
        name = view.findViewById(R.id.nameInput);
        email = view.findViewById(R.id.mailInput);
        occupation = view.findViewById(R.id.occupationInput);
        contract = view.findViewById(R.id.contractInput);

        // Set the text in the fields to be the employee being edited's detailed
        name.setText(editing.getName());
        email.setText(editing.getEmail());
        occupation.setText(editing.getOccupation());
        contract.setText(editing.getContract());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Change the employee at the index with a new employee created from the user input
        parentFragment.changeEmployee(new employee(name.getText().toString(), email.getText().toString(), occupation.getText().toString(),contract.getText().toString()), index);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }
}
