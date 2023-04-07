package com.example.staffinemployees.Fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.Adapters.HomeEventsAdapter;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.Response.Punch;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentMainBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Path;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    private LocationRequest locationRequest;


    FusedLocationProviderClient fusedLocationProviderClient;

    static int minute, second, hour;

    RecyclerView.LayoutManager layoutManagerH;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String currentLocation = null;

    String Id, eId;
    String currentMonth, currentYear, currentDate;
    private static final int REQUEST_CHECK_SETTINGS = 10001;
    public static final int LOCATION = 100;

    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        sharedPreferences = this.requireContext().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        setDigitalClock();

        Id = sharedPreferences.getAll().get("Id").toString();
        eId = sharedPreferences.getAll().get("eId").toString();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
        month += 1;
        switch (month) {
            case 1:
                binding.txtEventMonth.setText("January" + " " + "Events");
                break;
            case 2:
                binding.txtEventMonth.setText("February" + " " + "Events");
                break;
            case 3:
                binding.txtEventMonth.setText("March" + " " + "Events");
                break;
            case 4:
                binding.txtEventMonth.setText("April" + " " + "Events");
                break;
            case 5:
                binding.txtEventMonth.setText("May" + " " + "Events");
                break;
            case 6:
                binding.txtEventMonth.setText("June" + " " + "Events");
                break;
            case 7:
                binding.txtEventMonth.setText("July" + " " + "Events");
                break;
            case 8:
                binding.txtEventMonth.setText("August" + " " + "Events");
                break;
            case 9:
                binding.txtEventMonth.setText("September" + " " + "Events");
                break;
            case 10:
                binding.txtEventMonth.setText("October" + " " + "Events");
                break;
            case 11:
                binding.txtEventMonth.setText("November" + " " + "Events");
                break;
            case 12:
                binding.txtEventMonth.setText("December" + " " + "Events");
                break;
        }
//
//        Bundle bundle = this.getArguments();
//        assert bundle != null;
//        userid = bundle.getInt("userId");
//        Log.i("Id Aarahi hai", String.valueOf(userid));

//        binding.txtEventMonth.setText(String.valueOf(userid));

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestLocationPermission();
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());


        binding.recyclerViewMonthEvents.setAdapter(new HomeEventsAdapter(getContext()));
        layoutManagerH = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerViewMonthEvents.setLayoutManager(layoutManagerH);


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


        // break punch in punch out click
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

        // attendance punch in punch out click
        binding.punchinOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable()) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (isGPSEnabled()) {
                            Task<Location> task = fusedLocationProviderClient.getCurrentLocation(
                                    LocationRequest.PRIORITY_HIGH_ACCURACY,
                                    new CancellationTokenSource().getToken());
                            task.addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                                        try {
                                            List<Address> loc = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                            currentLocation = loc.get(0).getAddressLine(0) + loc.get(0).getLocality();
                                            Log.d("Latitude", String.valueOf(location.getLatitude()));
                                            Log.d("Logni", String.valueOf(location.getLongitude()));
                                            Log.d("LOCATION_IS", currentLocation);

                                            Date date = new Date();
                                            Calendar cal = Calendar.getInstance();
                                            cal.setTime(date);
                                            cal.get(Calendar.MONTH);

                                            String punchDate = currentYear + "-" + currentMonth + "-" + currentDate;
                                            String punchTime = String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
                                            //current location send krna he remaining in api

                                            if (!sharedPreferences.getAll().containsKey("punch")) {
                                                Call<Punch> callPunchIn = apiInterface.punchIn(Integer.parseInt(Id), punchDate, "present", punchTime);
                                                progressDialog.show();
                                                callPunchIn.enqueue(new Callback<Punch>() {
                                                    @Override
                                                    public void onResponse(Call<Punch> call, Response<Punch> response) {
                                                        if (response.isSuccessful()) {
                                                            Toast.makeText(getActivity(), "Punch In : " + punchTime, Toast.LENGTH_SHORT).show();
                                                            editor.putString("punch", "punchIn");
                                                            editor.apply();
                                                            binding.punchinOutBtn.setText("Punch Out");
                                                            progressDialog.dismiss();
                                                            Log.d("PUNCHDATE", punchDate);
                                                            Log.d("PUNCHTIME", punchTime);

                                                        } else if (response.code() == 400) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(getActivity(), "You Have Already Punched In", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(getContext(), "some error occured", Toast.LENGTH_SHORT).show();
                                                            Log.d("fuh", response.message());
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Punch> call, Throwable t) {
                                                        progressDialog.dismiss();
                                                        Toast.makeText(getContext(), "some failure occured", Toast.LENGTH_SHORT).show();
                                                        Log.d("ndf", t.getMessage());
                                                    }
                                                });


                                            } else {
                                                if (sharedPreferences.getAll().get("punch").toString().equalsIgnoreCase("punchIn")) {


                                                    Call<Punch> callPunchOut = apiInterface.punchOut(Integer.parseInt(Id), punchTime);
                                                    progressDialog.show();
                                                    callPunchOut.enqueue(new Callback<Punch>() {
                                                        @Override
                                                        public void onResponse(Call<Punch> call, Response<Punch> response) {
                                                            if (response.isSuccessful()) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(getActivity(), "Punch Out : " + punchTime, Toast.LENGTH_SHORT).show();
                                                                editor.putString("punch", "punchOut");
                                                                editor.apply();
                                                                binding.punchinOutBtn.setText("Punch In");

                                                                Log.d("PUNCHDATE", punchDate);
                                                                Log.d("PUNCHTIME", punchTime);
                                                            } else if (response.code() == 400) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(getActivity(), "You Have Already Punched Out", Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                progressDialog.dismiss();

                                                                Toast.makeText(getContext(), "some error occured", Toast.LENGTH_SHORT).show();
                                                                Log.d("fuh", response.message());
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Punch> call, Throwable t) {
                                                            Toast.makeText(getContext(), "some failure occured", Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();
                                                            Log.d("ndf", t.getMessage());
                                                        }
                                                    });


                                                    //punch out api call


                                                    //shared pref me daalo punch out
                                                } else {

                                                    Call<Punch> callPunchIn = apiInterface.punchIn(Integer.parseInt(Id), punchDate, "present", punchTime);
                                                    progressDialog.show();
                                                    callPunchIn.enqueue(new Callback<Punch>() {
                                                        @Override
                                                        public void onResponse(Call<Punch> call, Response<Punch> response) {

                                                            if (response.isSuccessful()) {
                                                                Toast.makeText(getActivity(), "Punch In : " + punchTime, Toast.LENGTH_SHORT).show();
                                                                progressDialog.dismiss();

                                                                editor.putString("punch", "punchIn");
                                                                editor.apply();
                                                                binding.punchinOutBtn.setText("Punch Out");

                                                                Log.d("PUNCHDATE", punchDate);
                                                                Log.d("PUNCHTIME", punchTime);
                                                                // punch in api call
                                                                // shared pref me punch in
                                                            } else if (response.code() == 400) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(getActivity(), "You Have Already Punched In", Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                Toast.makeText(getContext(), "some error occured", Toast.LENGTH_SHORT).show();

                                                                progressDialog.dismiss();
                                                                Log.d("fuh", response.message());
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Punch> call, Throwable t) {
                                                            Toast.makeText(getContext(), "some failure occured", Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();

                                                            Log.d("ndf", t.getMessage());
                                                        }
                                                    });
                                                }
                                            }

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Unable To Find Location", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            turnOnGPS();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Location Permission Required , Open Settings And Allow Permission", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();
                }


            }
        });
//        Integer loginId = response.body().getId();
//        editor.putInt("Id", loginId);
//        editor.putString("number", mobileno);
        editor.apply();
        return binding.getRoot();
    }


    private void turnOnGPS() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getActivity())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(getActivity(), "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {

        LocationManager locationManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            locationManager = (LocationManager) getContext().getSystemService(getContext().LOCATION_SERVICE);
        }
        boolean isEnabled = false;

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
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
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "permission Granted...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Permission Denied...", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_CHECK_SETTINGS) {

            switch (grantResults[0]) {
                case Activity.RESULT_OK:
                    Toast.makeText(getContext(), "GPS is tured on", Toast.LENGTH_SHORT).show();

                case Activity.RESULT_CANCELED:
                    Toast.makeText(getContext(), "GPS required to be tured on", Toast.LENGTH_SHORT).show();
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

                            currentMonth = String.valueOf(now.getMonthValue());
                            currentDate = String.valueOf(now.getDayOfMonth());
                            currentYear = String.valueOf(now.getYear());
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}