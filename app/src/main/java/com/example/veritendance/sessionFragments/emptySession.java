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

import com.example.veritendance.MainActivity;
import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class emptySession extends Fragment implements View.OnClickListener {
    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yy hh:mm a");
    private Date startTime;
    private String startTimeStr;
    private Date endTime;
    private String endTimeStr;
    private List attendees = new ArrayList<employee>();
    protected TextView sessionPlaceholder;
    MainActivity main;

    //public emptySession(MainActivity m) { main = m;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_empty_session, parent, false);
        startTime = new Date(System.currentTimeMillis());
        startTimeStr = formatter.format(startTime);
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

    public Date getStartTime() {
        return startTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }
}
