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

import com.example.veritendance.MainActivity;
import com.example.veritendance.R;

public class newEmployee extends Fragment implements View.OnClickListener {
    employeeFragment parentFragment;
    View view;
    EditText name;
    EditText email;
    EditText occupation;

    @SuppressLint("ValidFragment")
    public newEmployee(employeeFragment p) {
        parentFragment = p;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_new_employee, parent, false);
        ImageButton submitEmployee = (ImageButton) view.findViewById(R.id.submitEmployee);
        submitEmployee.setOnClickListener(this);
        name = (EditText) view.findViewById(R.id.nameInput);
        email = (EditText) view.findViewById(R.id.mailInput);
        occupation = (EditText) view.findViewById(R.id.occupationInput);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
    }

    @Override
    public void onClick(View v) {
        parentFragment.appendEmployee(new employee(name.getText().toString(), email.getText().toString(), occupation.getText().toString()));
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new employeeFragment()).commit();
    }
}
