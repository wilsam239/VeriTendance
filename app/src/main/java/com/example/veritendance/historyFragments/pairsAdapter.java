package com.example.veritendance.historyFragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.employee;

import java.util.ArrayList;

public class pairsAdapter extends RecyclerView.Adapter<pairsAdapter.ViewHolder> {

    private ArrayList<Pair<employee, Integer>> pairs;
    private modifySession parent;
    private Context context;

    public pairsAdapter(ArrayList<Pair<employee, Integer>> pairs, modifySession parent, Context context) {
        this.pairs = pairs;
        this.parent = parent;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView employee_name;
        public final EditText employee_score;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            employee_name = view.findViewById(R.id.attendeeNamePair);
            employee_score = view.findViewById(R.id.attendeeScorePair);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pair, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Pair<employee, Integer> att = pairs.get(i);
        holder.employee_name.setText(att.first.getName());
        holder.employee_score.setText(att.second.toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
