package com.example.staffinemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.staffinemployees.databinding.ActivityPersonalDetailsBinding;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.BuildConfig;

public class PersonalDetailsActivity extends AppCompatActivity {
ActivityPersonalDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPersonalDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalDetailsActivity.this,MainActivity.class));
                finish();
            }
        });



    }
}