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
     * New employee fragment
     * Allows the user to enter a new employee
     */

    employeeFragment parentFragment;
    View view;

    private EditText name;
    private EditText email;
    private EditText occupation;
    private EditText contract;

    private employee editing;
    private int index;

    public modifyEmployeeFragment(employeeFragment p, employee e, int index) {
        parentFragment = p;
        editing = e;
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sub_employees_modify, parent, false);
        ImageButton submitEmployee = view.findViewById(R.id.submitEmployee);
        submitEmployee.setOnClickListener(this);

        name = view.findViewById(R.id.nameInput);
        email = view.findViewById(R.id.mailInput);
        occupation = view.findViewById(R.id.occupationInput);
        contract = view.findViewById(R.id.contractInput);

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
        parentFragment.changeEmployee(new employee(name.getText().toString(), email.getText().toString(), occupation.getText().toString(),contract.getText().toString()), index);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }
}
