package com.example.veritendance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.veritendance.employeeFragments.*;
import com.example.veritendance.historyPackage.historyFragment;
import com.example.veritendance.sessionFragments.newSessionFragment;

public class MainActivity extends AppCompatActivity {
    // Begin the transaction
    private Fragment currentFragment;
    public employeeFragment employeeTab = null;
    public historyFragment historyTab = null;
    public topicsFragment topicsTab = null;
    public newSessionFragment sessionTab = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Begin a fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_employees:
                    if(currentFragment != employeeTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();

                        // Create a new Employee Fragment
                        if (employeeTab == null) {
                            employeeTab = new employeeFragment();
                        }
                        currentFragment = employeeTab;
                        // Using the fragment transaction, replace the fragment container with the new fragment
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }
                    return true;
                case R.id.navigation_history:
                    if(currentFragment != historyTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();

                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        // Create a new history Fragment
                        if(historyTab == null) {
                            historyTab = new historyFragment();
                        }
                        currentFragment = historyTab;
                        // Using the fragment transaction, replace the fragment container with the new fragment
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }

                    return true;
                case R.id.navigation_session:

                    if(currentFragment != sessionTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        // Create a new session Fragment
                        if(sessionTab == null) {
                            if(historyTab == null) {
                                historyTab = new historyFragment();
                            }
                            sessionTab = new newSessionFragment(historyTab, topicsTab);
                        }
                        currentFragment = sessionTab;
                        ft.replace(R.id.fragment_container, currentFragment).commit();
                    }
                    return true;
                case R.id.navigation_topics:
                    if(currentFragment != topicsTab) {
                        // Remove the previous fragment and commit that change
                        ft.remove(currentFragment).commit();
                        // Create a new fragment transaction
                        ft = getSupportFragmentManager().beginTransaction();
                        // Create a new topics Fragment
                        if(topicsTab == null) {
                            topicsTab = new topicsFragment();
                        }
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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        currentFragment = new employeeFragment();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, currentFragment);
        // Complete the changes added above
        ft.commit();
    }
}
