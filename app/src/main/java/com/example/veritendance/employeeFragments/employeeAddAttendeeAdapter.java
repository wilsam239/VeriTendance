package com.example.veritendance.employeeFragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.R;
import com.example.veritendance.sessionFragments.sessionFragment;

import java.util.ArrayList;

public class employeeAddAttendeeAdapter extends RecyclerView.Adapter<employeeAddAttendeeAdapter.ViewHolder> {
    /**
     * Employee add attendee adapter
     * Used to connect the recycler view to data
     * This is the adapter used for the in session screen, it allows attendees to be added.
     */

    private ArrayList<employee> employees;
    private sessionFragment parent;

    /**\
     *
     * @param employees - the list of employees to display
     * @param p - the parent sessionFragment
     */
    public employeeAddAttendeeAdapter(ArrayList<employee> employees, sessionFragment p) {
        this.employees = new ArrayList<>();
        this.employees.addAll(employees);
        parent = p;
        this.employees.removeAll(parent.getAttendees());
    }

    /**
     * The view holder object
     * Sets all the items that will appear/disappear from the view
     */
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
        /**
         * Set each item to the employees parameters
         * Also sets onClickListeners for buttons
         */
        final employee emp = employees.get(i);
        holder.employee_name.setText(emp.getName());
        holder.employee_jobTitle.setText(emp.getOccupation());
        holder.employee_email.setText(emp.getEmail());
        holder.addAttendee.setVisibility(View.VISIBLE);
        holder.removeAttendee.setVisibility(View.INVISIBLE);
        holder.addAttendee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the addAttendee button was selected, add the attendee to the session and refresh the screen
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


