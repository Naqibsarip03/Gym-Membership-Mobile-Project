package com.example.project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Load default fragment
        loadFragment(new HomepageFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Handle navigation item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Use if-else instead of switch
            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomepageFragment();
            } else if (item.getItemId() == R.id.nav_trainer) {
                selectedFragment = new PersonalTrainerFragment();
            } else if (item.getItemId() == R.id.nav_bmi) {
                selectedFragment = new BMIFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
