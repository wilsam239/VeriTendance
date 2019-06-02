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
import com.example.veritendance.R;

import java.util.ArrayList;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.ViewHolder> {
    /**
     * History adapter
     * Used to connect the recycler view to data
     * The adapter used for the history screen
     */

    // Parent fragment
    private historyFragment parent;
    // Session list
    private ArrayList<session> sessions;


    /**
     * Constructor
     * @param sessions - list of sessions
     * @param h - parent historyFragment
     */
    public historyAdapter(ArrayList<session> sessions, historyFragment h) {
        this.sessions = sessions;
        parent = h;
    }

    /**
     * The view holder object
     * Sets all the items that will appear/disappear from the view
     */
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
        /**
         * Set each item to the session's parameters
         * Also sets onClickListeners for buttons
         */
        final session s = sessions.get(i);
        holder.session_name.setText(s.getTitle());
        holder.session_date.setText(s.getEndTime());
        holder.session_percentage_complete.setText(Integer.toString(s.getPercentageComplete()).concat("% Complete"));
        holder.session_attendee_count.setText(Integer.toString(s.getAttendeeCount()).concat(" Attendees"));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the edit button was selected, replace the current fragment with a modify session fragment
                FragmentManager fm = ((AppCompatActivity)parent.getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new modifySessionFragment(parent, s, i)).commit();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the delete session button was selected, delete the session from the list
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
