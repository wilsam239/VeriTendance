package com.example.veritendance.sessionFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.veritendance.MainActivity;

import com.example.veritendance.R;

import java.text.SimpleDateFormat;

public class sessionSummary extends Fragment implements View.OnClickListener {
    private TextView startTime;
    private TextView endTime;
    private TextView endTimeTop;
    private TextView sessionName;
    private emptySession sesh;
    SimpleDateFormat formatter= new SimpleDateFormat("dd MMM. yyyy hh:mm:ss a");


    public sessionSummary(emptySession sesh) {
        this.sesh = sesh;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_session_summary, parent, false);
        startTime = (TextView) view.findViewById(R.id.startTime);
        startTime.setText(sesh.startTimeStr);

        endTime = (TextView) view.findViewById(R.id.endTime);
        endTime.setText(sesh.endTimeStr);

        endTimeTop = (TextView) view.findViewById(R.id.endTimePlaceholderTop);
        endTimeTop.setText(formatter.format(sesh.endTime));

        sessionName = (TextView) view.findViewById(R.id.sessionName);
        sessionName.setText(sesh.startTimeStr.contains("PM") ? "Afternoon Session" : "Morning Session");

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    @Override
    public void onClick(View v) {

    }
}
