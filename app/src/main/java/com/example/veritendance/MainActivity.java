package com.example.veritendance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Begin the transaction
    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_employees:
                    mTextMessage.setText(R.string.title_employees);
                    ft.replace(R.id.fragment_container, new employeeFragment());
                    // Complete the changes added above
                    ft.commit();
                    return true;
                case R.id.navigation_history:
                    mTextMessage.setText(R.string.title_history);
                    ft.replace(R.id.fragment_container, new historyFragment());
                    // Complete the changes added above
                    ft.commit();
                    return true;
                case R.id.navigation_session:
                    mTextMessage.setText(R.string.title_session);
                    ft.replace(R.id.fragment_container, new newSessionFragment());
                    // Complete the changes added above
                    ft.commit();
                    return true;
                case R.id.navigation_topics:
                    mTextMessage.setText(R.string.title_topics);
                    ft.replace(R.id.fragment_container, new topicsFragment());
                    // Complete the changes added above
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, new employeeFragment());
        // Complete the changes added above
        ft.commit();
    }

}
