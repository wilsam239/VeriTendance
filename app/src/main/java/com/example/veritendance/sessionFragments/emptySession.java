package com.example.veritendance.sessionFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.veritendance.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class emptySession extends Fragment implements View.OnClickListener {
    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yy hh:mm a");
    private TextView startTime;
    public String startTimeStr;
    public Date endTime;
    public String endTimeStr;
    protected TextView sessionPlaceholder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_empty_session, parent, false);
        startTimeStr = formatter.format(new Date(System.currentTimeMillis()));
        sessionPlaceholder = (TextView) view.findViewById(R.id.fragmentTitle);
        sessionPlaceholder.setText(startTimeStr.contains("PM") ? "Afternoon Session" : "Morning Session");
        Button finishSession = (Button) view.findViewById(R.id.finishSession);
        finishSession.setOnClickListener(this);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    @Override
    public void onClick(View v) {
        endTime = new Date(System.currentTimeMillis());
        endTimeStr = formatter.format(endTime);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new sessionSummary(this)).commit();
    }
}
