package com.example.staffinemployees.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Toast;


import com.example.staffinemployees.Adapters.HomeEventsAdapter;

import com.example.staffinemployees.databinding.FragmentMainBinding;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    int year,month,day,minute,second,hour;
    String issueSelected;

    String[] shift = {"10:00 AM to 07:00 PM",
            "10:00 PM to 07:00 AM",
            "05:00 AM to 02:00 PM"};


    @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
        binding = FragmentMainBinding.inflate(inflater, container, false);

//
//            ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, shift);
//            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            binding.spinner.setAdapter(aa);
//            binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {;
//                    issueSelected = shift[position];
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
//
//
            setDigitalClock();
   static int minute, second, hour;

    RecyclerView.LayoutManager layoutManagerH;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    LocalDateTime now = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        setDigitalClock();

        binding.recyclerViewMonthEvents.setAdapter(new HomeEventsAdapter(getContext()));
        layoutManagerH = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerViewMonthEvents.setLayoutManager(layoutManagerH);

        sharedPreferences = this.requireContext().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.getAll().containsKey("punch")) {
            binding.punchinOutBtn.setText("Punch In");
        } else {
            if (sharedPreferences.getAll().get("punch").toString().equalsIgnoreCase("punchIn")) {
                binding.punchinOutBtn.setText("Punch Out");
            } else {
                binding.punchinOutBtn.setText("Punch In");
            }
        }


        binding.punchinOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sharedPreferences.getAll().containsKey("punch")) {
                    Toast.makeText(getActivity(), "Punch In : "+ String.format("%02d", hour)+":"+ String.format("%02d", minute)+":"+String.format("%02d", second), Toast.LENGTH_SHORT).show();
                    editor.putString("punch", "punchIn");
                    editor.apply();
                    binding.punchinOutBtn.setText("Punch Out");
                    // call punch in api
                }
                else
                {
                    if (sharedPreferences.getAll().get("punch").toString().equalsIgnoreCase("punchIn")) {
                        Toast.makeText(getActivity(), "Punch Out : "+ String.format("%02d", hour)+":"+ String.format("%02d", minute)+":"+String.format("%02d", second), Toast.LENGTH_SHORT).show();
                        editor.putString("punch", "punchOut");
                        editor.apply();
                        binding.punchinOutBtn.setText("Punch In");
                        //punch out api call
                        //shared pref me daalo punch out
                        binding.punchinOutBtn.setText("Punch In");
                    } else {
                        Toast.makeText(getActivity(), "Punch In : "+ String.format("%02d", hour)+":"+ String.format("%02d", minute)+":"+String.format("%02d", second), Toast.LENGTH_SHORT).show();
                        editor.putString("punch", "punchIn");
                        editor.apply();
                        binding.punchinOutBtn.setText("Punch Out");
                        // punch in api call
                        // shared pref me punch in
                        binding.punchinOutBtn.setText("Punch Out");
                    }
                }
            }
        });


//        Integer loginId = response.body().getId();
//        editor.putInt("Id", loginId);
//        editor.putString("number", mobileno);
        editor.apply();

        return binding.getRoot();
    }

    private void setDigitalClock() {
        final Handler hander = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hander.post(new Runnable() {
                    @Override
                    public void run() {
//                        Date date = new Date();
                        // set time
                        LocalDateTime now = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            now = LocalDateTime.now();
                            hour = now.getHour();
                            minute = now.getMinute();
                            second = now.getSecond();
                        }
                        binding.hourTv.setText(String.format("%02d", hour));
                        binding.minTv.setText(String.format("%02d", minute));
                        binding.secTv.setText(String.format("%02d", second));

                        setDigitalClock();
                    }
                });
            }
        }).start();
    }

}