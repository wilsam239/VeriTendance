package com.example.veritendance.sessionFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.veritendance.MainActivity;

import com.example.veritendance.R;

public class sessionSummary extends Fragment implements View.OnClickListener {
    private TextView startTime;
    private emptySession sesh;
    public void sessionSummary(emptySession sesh) {
        this.sesh = sesh;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_session_summary, parent, false);

        startTime = (TextView) view.findViewById(R.id.startTime);
        startTime.setText(sesh.startTimeStr);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    @Override
    public void onClick(View v) {

    }
}
