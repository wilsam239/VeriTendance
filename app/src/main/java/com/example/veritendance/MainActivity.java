package com.example.veritendance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.veritendance.employeeFragments.employeeFragment;
import com.example.veritendance.sessionFragments.newSessionFragment;

public class MainActivity extends AppCompatActivity {
    // Begin the transaction

    private Fragment currentFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Begin a fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Remove the previous fragment and commit that change
            ft.remove(currentFragment).commit();
            switch (item.getItemId()) {
                case R.id.navigation_employees:
                    // Create a new fragment transaction
                    ft = getSupportFragmentManager().beginTransaction();
                    // Create a new Employee Fragment
                    currentFragment = new employeeFragment();
                    // Using the fragment transaction, replace the fragment container with the new fragment
                    ft.replace(R.id.fragment_container, currentFragment).commit();
                    return true;
                case R.id.navigation_history:
                    // Create a new fragment transaction
                    ft = getSupportFragmentManager().beginTransaction();
                    // Create a new history Fragment
                    currentFragment = new historyFragment();
                    // Using the fragment transaction, replace the fragment container with the new fragment
                    ft.replace(R.id.fragment_container, currentFragment).commit();
                    return true;
                case R.id.navigation_session:
                    // Create a new fragment transaction
                    ft = getSupportFragmentManager().beginTransaction();
                    // Create a new session Fragment
                    currentFragment = new newSessionFragment();
                    ft.replace(R.id.fragment_container, currentFragment).commit();
                    return true;
                case R.id.navigation_topics:
                    // Create a new fragment transaction
                    ft = getSupportFragmentManager().beginTransaction();
                    // Create a new topics Fragment
                    currentFragment = new topicsFragment();
                    // Using the fragment transaction, replace the fragment container with the new fragment
                    ft.replace(R.id.fragment_container, currentFragment).commit();
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
