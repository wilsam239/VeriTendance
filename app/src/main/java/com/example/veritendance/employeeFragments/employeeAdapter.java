package com.example.veritendance.employeeFragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.R;

import java.util.ArrayList;

public class employeeAdapter extends RecyclerView.Adapter<employeeAdapter.ViewHolder> {
    /**
     * Employee adapter
     * Used to connect the recycler view to data
     */

    private ArrayList<employee> employees;
    private employeeFragment parent;
    private Context context;

    public employeeAdapter(ArrayList<employee> employees, employeeFragment f) {
        this.employees = employees;
        parent = f;
        //context = c;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView employee_name;
        public final TextView employee_jobTitle;
        public final TextView employee_email;
        public final ImageButton edit;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            edit = view.findViewById(R.id.editEmployeeButton);
            employee_name = view.findViewById(R.id.employee_name);
            employee_jobTitle = view.findViewById(R.id.employee_jobTitle);
            employee_email = view.findViewById(R.id.employee_email);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        final employee emp = employees.get(i);
        holder.employee_name.setText(emp.getName());
        holder.employee_jobTitle.setText(emp.getOccupation());
        holder.employee_email.setText(emp.getEmail());
        holder.edit.setVisibility(View.VISIBLE);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((AppCompatActivity)parent.getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new modifyEmployee(parent, emp, i)).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (employees != null) {
            return employees.size();
        } else {
            return 0;
        }
    }


}


