package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.Adapters.EventAdapter;
import com.example.staffinemployees.Adapters.HomeEventsAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentMainBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    int minute, second, hour;

    RecyclerView.LayoutManager layoutManagerH;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.recyclerViewMonthEvents.setAdapter(new HomeEventsAdapter(getContext()));
        layoutManagerH=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        binding.recyclerViewMonthEvents.setLayoutManager(layoutManagerH);



        setDigitalClock();
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