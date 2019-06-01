package com.example.veritendance.topicFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.veritendance.R;
import com.example.veritendance.employeeFragments.employee;
import com.example.veritendance.employeeFragments.employeeFragment;
@SuppressLint("ValidFragment")
public class modifyTopicFragment extends Fragment implements View.OnClickListener {
    /**
     * New employee fragment
     * Allows the user to enter a new employee
     */

    topicsFragment parentFragment;
    View view;

    private EditText title;
    private EditText description;

    private topic editing;
    private int index;

    public modifyTopicFragment(topicsFragment p, topic t, int index) {
        parentFragment = p;
        editing = t;
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sub_topics_modify, parent, false);
        ImageButton submitTopic = view.findViewById(R.id.submitTopic);
        submitTopic.setOnClickListener(this);

        title = view.findViewById(R.id.titleInput);
        description = view.findViewById(R.id.descriptionInput);

        title.setText(editing.getTitle());
        description.setText(editing.getDescription());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        parentFragment.changeTopic(new topic(title.getText().toString(), description.getText().toString()), index);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }
}
