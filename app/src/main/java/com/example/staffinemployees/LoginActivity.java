package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.staffinemployees.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.phoneEt.getText().toString().trim().isEmpty()) {
                    binding.phoneEt.setError("Enter Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.phoneEt.getText().toString().trim().length() < 10) {
                    binding.phoneEt.setError("Enter Correct Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                    binding.passwordEt.setError("Enter Password");
                    binding.passwordEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().length() < 6) {
                    binding.passwordEt.setError("Enter password min 6 words");
                    binding.passwordEt.requestFocus();
                } else {



                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }
}