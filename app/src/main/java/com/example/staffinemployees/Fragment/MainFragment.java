package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentMainBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


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


        return binding.getRoot();


    }

        private void setDigitalClock () {
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
                            minute  = now.getMinute();
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