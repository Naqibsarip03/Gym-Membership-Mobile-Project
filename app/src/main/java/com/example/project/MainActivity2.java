package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

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

        //display userinfo
        DBSql dbSql = new DBSql(this);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvEmail = findViewById(R.id.tvEmail);

        Intent intent = getIntent();
        Integer userId = intent.getIntExtra("userId",0);

        String[] columns = {"name", "email", "phoneNo"};
        String selection = "userId = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        try (SQLiteDatabase db = new DBSql(this).getReadableDatabase()){
            Cursor cursor = db.query("tblUser", columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String phoneNo = cursor.getString(cursor.getColumnIndexOrThrow("phoneNo"));

                tvName.setText("Name: " + name);
                tvEmail.setText("Email: " + email);
                tvPhone.setText("Phone Number: " + phoneNo);

            } else {
                // Handle the case where no user with the given ID is found
                // For example, display an error message
            }
        }

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
