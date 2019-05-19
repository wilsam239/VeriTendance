package com.example.veritendance.employeeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.veritendance.R;

public class employeeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_employees, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
    }
}
