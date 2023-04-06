package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.os.Bundle;

import com.example.staffinemployees.Adapters.HolidayAdapter;
import com.example.staffinemployees.databinding.ActivityCalendarSettingBinding;

public class CalendarSettingActivity extends AppCompatActivity {
    ActivityCalendarSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//
//        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(CalendarSettingActivity.this));

    }
}