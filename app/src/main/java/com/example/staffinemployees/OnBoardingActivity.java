package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.staffinemployees.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {
    ActivityOnBoardingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}