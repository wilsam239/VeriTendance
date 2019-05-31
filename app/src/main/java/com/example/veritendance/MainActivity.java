package com.example.veritendance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.veritendance.employeeFragments.*;
import com.example.veritendance.historyFragments.historyFragment;
import com.example.veritendance.sessionFragments.newSessionFragment;
import com.example.veritendance.topicFragments.topicsFragment;

public class MainActivity extends AppCompatActivity {
    // Create a Fragment that will be used in navigation
    private Fragment currentFragment;
    // Create 4 different fragments to keep track of when a tab has been created
    public employeeFragment employeeTab = null;
    public historyFragment historyTab = null;
    public topicsFragment topicsTab = null;
    public newSessionFragment sessionTab = null;

    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Begin a fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_employees:
                    // Each case checks if the currentFragment is the one that is being clicked on
                    // If so, no change is required
                    if (currentFragment != employeeTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        currentFragment = employeeTab;
                        // Using the fragment transaction, replace the fragment container with the new fragment
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }
                    return true;
                case R.id.navigation_history:
                    if (currentFragment != historyTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        currentFragment = historyTab;
                        // Using the fragment transaction, replace the fragment container with the new fragment
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }

                    return true;
                case R.id.navigation_session:

                    if (currentFragment != sessionTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        // Create a new sessionFragment Fragment
                        currentFragment = sessionTab;
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }
                    return true;
                case R.id.navigation_topics:
                    if (currentFragment != topicsTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        // Create a new topics Fragment
                        currentFragment = topicsTab;
                        // Using the fragment transaction, replace the fragment container with the new fragment
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Start a fragment transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Set the navigation item
        bottomNavigationView = findViewById(R.id.navigation);
        // Set the item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // Create each tab
        employeeTab = new employeeFragment();
        historyTab = new historyFragment();
        topicsTab = new topicsFragment();
        sessionTab = new newSessionFragment(historyTab, topicsTab, employeeTab.getEmployeesList());
        // Set the current tab to the employee tab
        currentFragment = employeeTab;
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, currentFragment);
        // Complete the changes added above
        ft.commit();
    }
}
