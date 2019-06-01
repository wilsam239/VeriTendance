package com.example.veritendance.topicFragments;

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

public class topicAdapter extends RecyclerView.Adapter<topicAdapter.ViewHolder> {

    private ArrayList<topic> topics;
    private topicsFragment parent;

    public topicAdapter(ArrayList<topic> topics, topicsFragment t) {
        this.topics = topics;
        this.parent = t;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final topic t = topics.get(i);
        viewHolder.topicName.setText(t.getTitle());
        viewHolder.deleteTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.removeTopic(t);
            }
        });
        viewHolder.modifyTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((AppCompatActivity)parent.getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new modifyTopicFragment(parent, t, i)).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(topics != null) return topics.size();
        else return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView topicName;
        public final ImageButton modifyTopic;
        public final ImageButton deleteTopic;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            topicName = view.findViewById(R.id.topic_name);
            deleteTopic = view.findViewById(R.id.deleteTopic);
            modifyTopic = view.findViewById(R.id.editTopic);
        }
    }

}
