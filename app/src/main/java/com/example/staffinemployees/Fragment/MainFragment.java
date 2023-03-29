package com.example.staffinemployees.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.staffinemployees.Adapters.HomeEventsAdapter;

import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.databinding.FragmentMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    FusedLocationProviderClient fusedLocationProviderClient;

    static int minute, second, hour;

    RecyclerView.LayoutManager layoutManagerH;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    public static final int LOCATION = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        setDigitalClock();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestLocationPermission();
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());


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


    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission Needed")
                    .setMessage("Location Required For Tracking Punching Locations")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "permission Granted...", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
            }
        }
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