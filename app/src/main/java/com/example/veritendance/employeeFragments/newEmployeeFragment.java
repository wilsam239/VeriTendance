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
public class newEmployeeFragment extends Fragment implements View.OnClickListener {
    /**
     * New employee fragment
     * Allows the user to enter a new employee
     * Shows the sub_employees_new layout
     */

    // Parent fragment
    employeeFragment parentFragment;

    // The fields to be displayed on the screen
    EditText name;
    EditText email;
    EditText occupation;
    EditText contract;

    /**
     * Constructor
     * @param p - parent employeeFragment
     */
    public newEmployeeFragment(employeeFragment p) {
        parentFragment = p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_employees_new, parent, false);

        // Create the button and set its listener
        ImageButton submitEmployee = view.findViewById(R.id.submitEmployee);
        submitEmployee.setOnClickListener(this);

        // Create the fields to be displayed for editing
        name = view.findViewById(R.id.nameInput);
        email = view.findViewById(R.id.mailInput);
        occupation = view.findViewById(R.id.occupationInput);
        contract = view.findViewById(R.id.contractInput);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Append the employee based on the field inputs
        parentFragment.appendEmployee(new employee(name.getText().toString(), email.getText().toString(), occupation.getText().toString(),contract.getText().toString()));
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }
}
