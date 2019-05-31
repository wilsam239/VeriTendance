package com.example.veritendance.topicFragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.veritendance.R;

import java.util.ArrayList;

public class topicAdapter extends RecyclerView.Adapter<topicAdapter.ViewHolder> {

    private ArrayList<String> topics;

    public topicAdapter(ArrayList<String> topics) { this.topics = topics; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String topic = topics.get(i);
        viewHolder.topicName.setText(topic);
    }

    @Override
    public int getItemCount() {
        if(topics != null) return topics.size();
        else return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView topicName;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            topicName = view.findViewById(R.id.topic_name);
        }
    }

}
