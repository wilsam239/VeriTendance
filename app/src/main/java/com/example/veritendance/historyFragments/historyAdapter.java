package com.example.veritendance.historyFragments;

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

import com.example.veritendance.sessionFragments.session;
import com.example.veritendance.sessionFragments.sessionFragment;
import com.example.veritendance.R;

import java.util.ArrayList;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.ViewHolder> {
    /**
     * History adapter
     * Used to connect the recycler view to data
     */
    private historyFragment parent;
    private ArrayList<session> sessions;
    private Context context;

    /*public historyAdapter(ArrayList<sessionFragment> sessionFragments) {
        this.sessionFragments = sessionFragments;
    }*/

    public historyAdapter(ArrayList<session> sessions, Context c, historyFragment h) {
        this.sessions = sessions;
        context = c;
        parent = h;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView session_name;
        public final TextView session_date;
        public final TextView session_attendee_count;
        public final TextView session_percentage_complete;
        public final ImageButton edit;
        public final ImageButton delete;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            edit = view.findViewById(R.id.editSessionButton);
            delete = view.findViewById(R.id.deleteSessionButton);
            session_name = view.findViewById(R.id.session_name);
            session_date = view.findViewById(R.id.session_date);
            session_attendee_count = view.findViewById(R.id.session_attendee_count);
            session_percentage_complete = view.findViewById(R.id.session_percentage_complete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        final session s = sessions.get(i);
        holder.session_name.setText(s.getTitle());
        holder.session_date.setText(s.getEndTime());
        holder.session_percentage_complete.setText(Integer.toString(s.getPercentageComplete()).concat("% Complete"));
        holder.session_attendee_count.setText(Integer.toString(s.getAttendeeCount()).concat(" Attendees"));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new modifySession(parent, s, i)).commit();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.deleteSession(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (sessions != null) {
            return sessions.size();
        } else {
            return 0;
        }
    }


}
