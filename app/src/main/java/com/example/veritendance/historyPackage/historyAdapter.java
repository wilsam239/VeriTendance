package com.example.veritendance.historyPackage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.veritendance.sessionFragments.session;
import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.employee;
import com.example.veritendance.employeeFragments.employeeAdapter;

import java.util.ArrayList;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.ViewHolder> {
    private ArrayList<session> sessions;

    public historyAdapter(ArrayList<session> sessions) {
        this.sessions = sessions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView session_name;
        public final TextView session_date;
        public final TextView session_attendee_count;
        public final TextView session_percentage_complete;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            session_name = view.findViewById(R.id.session_name);
            session_date = view.findViewById(R.id.session_date);
            session_attendee_count = view.findViewById(R.id.session_attendee_count);
            session_percentage_complete = view.findViewById(R.id.session_percentage_complete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        session s = sessions.get(i);
        holder.session_name.setText(s.getSessionName());
        holder.session_date.setText(s.getEndTimeStr());
        holder.session_percentage_complete.setText(Integer.toString(s.getPercentageComplete()).concat("% Complete"));
        holder.session_attendee_count.setText(Integer.toString(s.getAttendeeCount()).concat(" Attendees"));
    }

    @Override
    public int getItemCount() {
        if(sessions != null) {
            return sessions.size();
        } else {
            return 0;
        }
    }


}
