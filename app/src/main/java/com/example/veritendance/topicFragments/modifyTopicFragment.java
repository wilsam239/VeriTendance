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
     * Modify Topic fragment
     * Shows the sub_topics_modify layout
     */

    // The parent fragment
    topicsFragment parentFragment;

    // The edit text variables
    private EditText title;
    private EditText description;

    // The topic being edited and its index
    private topic editing;
    private int index;

    /**
     * Constructor
     * @param p - the parent topic fragment
     * @param t - the topic being edited
     * @param index - the index of the topic being edited
     */
    public modifyTopicFragment(topicsFragment p, topic t, int index) {
        parentFragment = p;
        editing = t;
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_topics_modify, parent, false);

        // Create the image button and set it a listener
        ImageButton submitTopic = view.findViewById(R.id.submitTopic);
        submitTopic.setOnClickListener(this);

        // Create the title Edit Text and description Edit text
        title = view.findViewById(R.id.titleInput);
        description = view.findViewById(R.id.descriptionInput);

        // Set the text in the edit text fields
        title.setText(editing.getTitle());
        description.setText(editing.getDescription());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        // Change the topic at index with the new entered text
        parentFragment.changeTopic(new topic(title.getText().toString(), description.getText().toString()), index);
        // Begin a fragment transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parentFragment).commit();
    }
}
