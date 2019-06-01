package com.example.veritendance.historyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.veritendance.R;
import com.example.veritendance.sessionFragments.*;

import java.util.ArrayList;

public class historyFragment extends Fragment {
    /**
     * History fragment
     * Shows the main_history layout
     */

    private RecyclerView sessions;
    private RecyclerView.Adapter adapter;
    private ArrayList<sessionFragment> sessionFragmentList;
    private ArrayList<session> sessionList;

    public historyFragment() {
        sessionFragmentList = new ArrayList<>();
        sessionList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_history, parent, false);
        this.sessions = view.findViewById(R.id.history);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(parent.getContext());
        this.sessions.setLayoutManager(mLayoutManager);
        TextView noSession = (TextView) view.findViewById(R.id.noHistory);
        noSession.setVisibility(sessionList.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        //adapter = new historyAdapter(sessionFragmentList);
        adapter = new historyAdapter(sessionList, this.getContext(), this);
        this.sessions.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public void appendSession(session newSession) {
        sessionList.add(newSession);
    }

    public void changeSession(session editedSession, int index) {
        sessionList.set(index, editedSession);
    }

    public void deleteSession(session sessionToBeRemoved) {
        sessionList.remove(sessionToBeRemoved);
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }
}
