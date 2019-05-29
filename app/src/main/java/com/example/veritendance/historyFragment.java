package com.example.veritendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.veritendance.sessionFragments.*;

import java.util.ArrayList;
import java.util.List;

public class historyFragment extends Fragment {
    private RecyclerView sessions;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<session> sessionList;

    public historyFragment() {sessionList = new ArrayList<>();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_history, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
    }

    public List getSessionList() {
        return sessionList;
    }

    public void appendSession(session newSession) {
        this.sessionList.add(newSession);
    }
}
