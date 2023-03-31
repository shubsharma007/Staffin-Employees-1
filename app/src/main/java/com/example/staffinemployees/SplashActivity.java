package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.staffinemployees.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        new CountDownTimer(3000, 2000) {

            public void onTick(long millisUntilFinished) {
//                binding.splashLogo2.animate().alpha(0).setDuration(1500);

//                binding.splashLogo.setImageResource(R.drawable.splash_logo2);
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if (!sharedPreferences.getAll().containsKey("onboarding")) {
                    startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                    editor.putString("onboarding", "yes");
                    editor.apply();
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }.start();
    }
}