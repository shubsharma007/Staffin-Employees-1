package com.example.staffinemployees.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.staffinemployees.Adapters.HomeEventsAdapter;

import com.example.staffinemployees.databinding.FragmentMainBinding;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    static int minute, second, hour;

    RecyclerView.LayoutManager layoutManagerH;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    LocalDateTime now = null;
    int userid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        setDigitalClock();
//
//        Bundle bundle = this.getArguments();
//        assert bundle != null;
//        userid = bundle.getInt("userId");
//        Log.i("Id Aarahi hai", String.valueOf(userid));

//        binding.txtEventMonth.setText(String.valueOf(userid));


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

        if (!sharedPreferences.getAll().containsKey("break")) {
            binding.BreakTimeBtn.setText("Break Start");
        } else {
            if (sharedPreferences.getAll().get("break").toString().equalsIgnoreCase("breakStart")) {
                binding.BreakTimeBtn.setText("Break End");
            } else {
                binding.BreakTimeBtn.setText("Break Start");
            }
        }
        binding.BreakTimeBtn.setOnClickListener(v -> {

            if (!sharedPreferences.getAll().containsKey("break")) {
                //when user open first time app
                Toast.makeText(getActivity(), "Break Start : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second), Toast.LENGTH_SHORT).show();
                editor.putString("break", "breakStart");
                editor.apply();
                binding.BreakTimeBtn.setText("Break End");
                // call punch in api
            } else {
                if (sharedPreferences.getAll().get("break").toString().equalsIgnoreCase("breakStart")) {
                    Toast.makeText(getActivity(), "Break End : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second), Toast.LENGTH_SHORT).show();
                    editor.putString("break", "breakEnd");
                    editor.apply();
                    binding.BreakTimeBtn.setText("Break Start");
                    //punch out api call
                    //shared pref me daalo punch out
                } else {
                    Toast.makeText(getActivity(), "Break Start : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second), Toast.LENGTH_SHORT).show();
                    editor.putString("break", "breakStart");
                    editor.apply();
                    binding.BreakTimeBtn.setText("Break End");
                    // punch in api call
                    // shared pref me punch in

                }
            }


        });


        binding.punchinOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sharedPreferences.getAll().containsKey("punch")) {
                    Toast.makeText(getActivity(), "Punch In : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second), Toast.LENGTH_SHORT).show();
                    editor.putString("punch", "punchIn");
                    editor.apply();
                    binding.punchinOutBtn.setText("Punch Out");
                    // call punch in api
                } else {
                    if (sharedPreferences.getAll().get("punch").toString().equalsIgnoreCase("punchIn")) {
                        Toast.makeText(getActivity(), "Punch Out : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second), Toast.LENGTH_SHORT).show();
                        editor.putString("punch", "punchOut");
                        editor.apply();
                        binding.punchinOutBtn.setText("Punch In");
                        //punch out api call
                        //shared pref me daalo punch out
                    } else {
                        Toast.makeText(getActivity(), "Punch In : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second), Toast.LENGTH_SHORT).show();
                        editor.putString("punch", "punchIn");
                        editor.apply();
                        binding.punchinOutBtn.setText("Punch Out");
                        // punch in api call
                        // shared pref me punch in

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
                        Date date = new Date();
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