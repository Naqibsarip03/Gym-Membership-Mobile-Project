package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.sqlite.DBSql;
import com.example.project.sqlite.Membership;
import com.example.project.sqlite.UserModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -1);

        DBSql dbSql = new DBSql(this);
        UserModel user = dbSql.getUserDetails(userId);

        if (user != null) {
            if(user.getMembershipId() != null)
                binding.tvStatusMembership.setText("You currently have a membership with ID: " + user.getMembershipId());
            else
                binding.tvStatusMembership.setText("You currently have no membership");

        }

        // Retrieve membership data from the database
        List<Membership> memberships = dbSql.getAllMemberships();

        // Set the adapter
        MembershipAdapter adapter = new MembershipAdapter(memberships);
        binding.viewPager.setAdapter(adapter);
    }
}