package com.example.veritendance.employeeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.veritendance.MainActivity;
import com.example.veritendance.R;

public class newEmployee extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new_employee, parent, false);
        ImageButton submitEmployee = (ImageButton) view.findViewById(R.id.submitEmployee);
        submitEmployee.setOnClickListener(this);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
    }

    @Override
    public void onClick(View v) {

    }
}
