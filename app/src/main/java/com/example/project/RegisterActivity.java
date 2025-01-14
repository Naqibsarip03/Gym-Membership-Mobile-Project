package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.databinding.ActivityRegisterBinding;
import com.example.project.sqlite.DBSql;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUp.setOnClickListener(this::fnRegister);
    }
    public void fnLogin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void fnRegister(View view){
        String username = binding.etUsername2.getText().toString();
        String password = binding.etPassword2.getText().toString();
        String rePassword = binding.etRePassword2.getText().toString();
        String email = binding.etEmail.getText().toString();
        String phone = binding.etPhone.getText().toString();

        if(username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || email.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(rePassword)){
            binding.etPassword2.setText("");
            binding.etRePassword2.setText("");
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        }else{
            DBSql dbSql = new DBSql(this);
            try {
                int result = dbSql.insertUser(username, password, email, phone);

                if(result > 0){
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
            }


            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
        }

    }
}