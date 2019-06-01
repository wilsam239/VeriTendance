package com.example.veritendance.historyFragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.employee;
import com.example.veritendance.sessionFragments.sessionSummaryFragment;

import java.util.ArrayList;

public class pairsAdapter extends RecyclerView.Adapter<pairsAdapter.ViewHolder> {

    private ArrayList<Pair<employee, Integer>> pairs;
    private modifySessionFragment parentM = null;
    private sessionSummaryFragment parentS = null;

    public pairsAdapter(ArrayList<Pair<employee, Integer>> pairs, modifySessionFragment m) {
        this.pairs = pairs;
        parentM = m;
    }

    public pairsAdapter(ArrayList<Pair<employee, Integer>> pairs, sessionSummaryFragment s) {
        this.pairs = pairs;
        parentS = s;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pair, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        final Pair<employee, Integer> att = pairs.get(i);
        holder.employee_name.setText(att.first.getName());
        holder.employee_score.setText(att.second.toString());
        // Creates a TextWatcher so that when the sessionName is changed, it updates the top label
        TextWatcher scoreTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!holder.employee_score.getText().toString().equals("")) {
                    int score = Integer.parseInt(holder.employee_score.getText().toString());
                    if(parentM != null) parentM.changeScores(new Pair(pairs.get(i).first, score <= 100 ? score : 100), i);
                    if(parentS != null) parentS.changeScores(new Pair(pairs.get(i).first, score <= 100 ? score : 100), i);
                }
            }
        };

        holder.employee_score.addTextChangedListener(scoreTextWatcher);
    }

    @Override
    public int getItemCount() {
        if (pairs != null) return pairs.size();
        else return 0;
    }
}
