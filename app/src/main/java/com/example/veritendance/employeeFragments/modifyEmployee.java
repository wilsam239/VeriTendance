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

public class modifyEmployee extends Fragment implements View.OnClickListener {
    /**
     * New employee fragment
     * Allows the user to enter a new employee
     */

    employeeFragment parentFragment;
    View view;

    private EditText name;
    private EditText email;
    private EditText occupation;

    private employee editing;
    private int index;

    @SuppressLint("ValidFragment")
    public modifyEmployee(employeeFragment p, employee e, int index) {
        parentFragment = p;
        editing = e;
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_modify_employee, parent, false);
        ImageButton submitEmployee = view.findViewById(R.id.submitEmployee);
        submitEmployee.setOnClickListener(this);

        name = view.findViewById(R.id.nameInput);
        email = view.findViewById(R.id.mailInput);
        occupation = view.findViewById(R.id.occupationInput);

        name.setText(editing.getName());
        email.setText(editing.getEmail());
        occupation.setText(editing.getOccupation());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        parentFragment.changeEmployee(new employee(name.getText().toString(), email.getText().toString(), occupation.getText().toString()), index);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }
}
