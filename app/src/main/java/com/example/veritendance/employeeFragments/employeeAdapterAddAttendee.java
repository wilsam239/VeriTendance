package com.example.veritendance.employeeFragments;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.R;
import com.example.veritendance.sessionFragments.sessionFragment;

import java.util.ArrayList;

public class employeeAdapterAddAttendee extends RecyclerView.Adapter<employeeAdapterAddAttendee.ViewHolder> {
    /**
     * Employee adapter
     * Used to connect the recycler view to data
     */

    private ArrayList<employee> employees;
    private sessionFragment parent;

    public employeeAdapterAddAttendee(ArrayList<employee> employees, sessionFragment p) {
        this.employees = new ArrayList<>();
        this.employees.addAll(employees);
        parent = p;
        this.employees.removeAll(parent.getAttendees());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView employee_name;
        public final TextView employee_jobTitle;
        public final TextView employee_email;
        public final ImageButton addAttendee;
        public final ImageButton removeAttendee;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            employee_name = view.findViewById(R.id.employee_name);
            employee_jobTitle = view.findViewById(R.id.employee_jobTitle);
            employee_email = view.findViewById(R.id.employee_email);
            addAttendee = view.findViewById(R.id.addAttendeeButton);
            removeAttendee = view.findViewById(R.id.removeAttendeeButton);
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
        holder.addAttendee.setVisibility(View.VISIBLE);
        holder.removeAttendee.setVisibility(View.INVISIBLE);
        holder.addAttendee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.addAttendee(emp);
                parent.addAdapter = false;
                parent.getFragmentManager().beginTransaction().detach(parent).attach(parent).commit();
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


