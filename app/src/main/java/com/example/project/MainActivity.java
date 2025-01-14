package com.example.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityMainBinding;
import com.example.project.sqlite.DBSql;
import com.example.project.sqlite.UserModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBSql dbSql = new DBSql(this);
        SQLiteDatabase db = dbSql.getWritableDatabase();

        // Retrieve intent data safely
        Intent intent = getIntent();
        String username = intent.hasExtra("username") ? intent.getStringExtra("username") : "";
        String password = intent.hasExtra("password") ? intent.getStringExtra("password") : "";

        // Check if the intent data is valid and set it to the fields
        if (!username.isEmpty() && !password.isEmpty()) {
            binding.etUsername.setText(username);
            binding.etPassword.setText(password);
        }

        binding.btnLogin.setOnClickListener(this::fnLogin);
    }

    public void fnLogin(View view){
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            binding.etUsername.setText("");
            binding.etPassword.setText("");
        }else{
            DBSql dbSql = new DBSql(this);
            UserModel user = dbSql.getUserAuthentication(username, password);
            if (user != null) {
                Toast.makeText(getApplicationContext(), "Welcome, " + user.getUsername(), Toast.LENGTH_SHORT).show();

                // Access user details
                int userId = user.getUserId();

                // Do something with the data, such as navigating to another screen
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void fnSignup(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}