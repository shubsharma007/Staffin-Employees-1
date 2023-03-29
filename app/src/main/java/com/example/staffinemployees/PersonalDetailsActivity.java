package com.example.staffinemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.staffinemployees.databinding.ActivityPersonalDetailsBinding;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.BuildConfig;

public class PersonalDetailsActivity extends AppCompatActivity {
    ActivityPersonalDetailsBinding binding;
    static Boolean dpImageBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!dpImageBoolean) {
                    Toast.makeText(getApplicationContext(), "Please Upload your Profile Image", Toast.LENGTH_SHORT).show();
                } else if (binding.employeeIdEt.getText().toString().isEmpty()) {
                    binding.employeeIdEt.setError("Enter Your Name");
                    binding.employeeIdEt.requestFocus();
                } else if (binding.departmentEt.getText().toString().isEmpty()) {
                    binding.departmentEt.setError("Enter Your Father's Name");
                    binding.departmentEt.requestFocus();
                } else if (binding.dobEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Select Your DOB", Toast.LENGTH_SHORT).show();
                } else if (!binding.rbMale.isChecked() && !binding.rbFemale.isChecked() && !binding.rbOther.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please Select Your Gender", Toast.LENGTH_SHORT).show();
                } else if (binding.mobileEt.getText().toString().trim().isEmpty()) {
                    binding.mobileEt.setError("Enter Mobile Number");
                    binding.mobileEt.requestFocus();
                } else if (binding.mobileEt.getText().toString().trim().length() < 10) {
                    binding.mobileEt.setError("Enter Correct Mobile Number");
                    binding.mobileEt.requestFocus();
                } else if (binding.emailEt.getText().toString().trim().isEmpty() ||
                        !binding.emailEt.getText().toString().trim().contains("@") ||
                        !binding.emailEt.getText().toString().trim().contains(".")) {
                    binding.emailEt.setError("Enter Correct Email");
                    binding.emailEt.requestFocus();
                } else if (binding.localAddEt.getText().toString().isEmpty()) {
                    binding.localAddEt.setError("Enter Your Address");
                    binding.localAddEt.requestFocus();
                } else if (binding.permAddEt.getText().toString().isEmpty()) {
                    binding.permAddEt.setError("Enter Your Permanent Local Address");
                    binding.permAddEt.requestFocus();
                } else {
                    startActivity(new Intent(PersonalDetailsActivity.this, MainActivity.class));
                    finish();
                }
            }
        });


    }
}