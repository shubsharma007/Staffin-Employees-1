package com.example.staffinemployees;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.Response.TotalEmployeeResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.ActivityCreateEventBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.net.PlacesClient;
//import com.google.android.libraries.places.widget.Autocomplete;
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventActivity extends AppCompatActivity {
    ActivityCreateEventBinding binding;

    List<String> employeesList;
    boolean[] selectedEmployees;
    boolean atleastOne = false;
    ProgressDialog progress;
    ApiInterface apiInterface;
    HashMap<String, String> map;

//    EditText placeSearch_Tv;
//    private String apiKey = "AIzaSyBHdfJUk9pq_4V1fY337xHCc1dA9ebeueM";
//    int AUTOCOMPLETE_REQUEST_CODE = 1;
//    List<com.google.android.libraries.places.api.model.Place.Field> fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        placeSearch_Tv = findViewById(R.id.location_ET);
//        Places.initialize(getApplicationContext(), apiKey);

        clickListeners();
//        googlePlaces();
//        placeSearch_Tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
//                FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);
//                PlacesClient placesClient = Places.createClient(getApplicationContext());
//
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                placesClient.findCurrentPlace(request).addOnSuccessListener((response) -> {
//                    for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
//                        Place place = placeLikelihood.getPlace();
//                Log.i( "Place found: ",  place.getName() + " (" + place.getAddress() + ") " + place.getLatLng());
//                    }
//                }).addOnFailureListener((exception) -> {
//                    if (exception instanceof ApiException) {
//                        ApiException apiException = (ApiException) exception;
//                Log.e( "Place not found: ", String.valueOf(apiException.getStatusCode()));
//                    }
//                });
//            }
//        });

    }   // onCreate end

//    private void googlePlaces() {
//        int AUTOCOMPLETE_REQUEST_CODE = 1;
//
//        List<com.google.android.libraries.places.api.model.Place.Field> fields;
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), apiKey);
//        }
//        PlacesClient placesClient = Places.createClient(this);
//        fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
//        placeSearch_Tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Autocomplete.IntentBuilder(
//                        AutocompleteActivityMode.FULLSCREEN, fields)
//                        .build(getApplicationContext());
//                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
//            }
//        });
//
//    }

    private void clickListeners() {
        map = new HashMap<>();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progress = new ProgressDialog(CreateEventActivity.this);
        progress.setMessage("please wait....");
        employeesList = new ArrayList<>();
        if (isNetworkAvailable()) {
            progress.show();
            Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
            callGetTotalEmployee.enqueue(new Callback<TotalEmployeeResponse>() {
                @Override
                public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                    if (response.isSuccessful()) {
                        ImageView imageview[] = new ImageView[response.body().getEmployeeResult().size()];

                        String[] names = new String[response.body().getEmployeeResult().size()];

                        for (int i = 0; i < response.body().getEmployeeResult().size(); i++) {
                            employeesList.add(response.body().getEmployeeResult().get(i).getFullName());
                            Log.d("dfuhksdf", employeesList.get(i));
                            names[i] = employeesList.get(i);
                            map.put(response.body().getEmployeeResult().get(i).getFullName(), response.body().getEmployeeResult().get(i).getProfileImage());
                        }


                        selectedEmployees = new boolean[response.body().getEmployeeResult().size()];


                        binding.addMemberBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);

                                builder.setTitle("Add Members");
                                builder.setIcon(R.drawable.img_dp);

                                builder.setMultiChoiceItems(names, selectedEmployees, (dialog, which, isChecked) -> {
                                    selectedEmployees[which] = isChecked;

                                });

                                builder.setCancelable(false);

                                builder.setNeutralButton("CLEAR ALL", (dialog, which) -> {
                                    Arrays.fill(selectedEmployees, false);
                                    binding.dynamicLl.removeAllViews();
                                });
                                builder.setNegativeButton("CANCEL", (dialog, which) -> {
                                });
                                builder.setPositiveButton("Done", (dialog, which) -> {
                                    binding.dynamicLl.removeAllViews();
                                    for (int i = 0; i < selectedEmployees.length; i++) {
                                        if (selectedEmployees[i]) {
                                            imageview[i] = new ImageView(CreateEventActivity.this);
                                            imageview[i].setImageResource(R.drawable.img_dp);
                                            View child = getLayoutInflater().inflate(R.layout.add_member_in_event, null);
                                            child.findViewById(R.id.memberDp);
                                            binding.dynamicLl.addView(child);
                                        }
                                    }
                                });

                                builder.create();
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });

                        progress.dismiss();
                    } else {
                        progress.dismiss();
                        Toast.makeText(CreateEventActivity.this, "some error occured", Toast.LENGTH_SHORT).show();
                        Log.d("jkdfsdf", response.message());
                    }
                }

                @Override
                public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                    Log.d("nfkjsf", t.getMessage());
                    progress.dismiss();
                    Toast.makeText(CreateEventActivity.this, "Unable to Load", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.descriptionCounterTv.setText(String.valueOf(s.length()) + "/" + "150");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        };
        binding.descriptionEt.addTextChangedListener(mTextEditorWatcher);

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean yes = false;
                for (boolean single : selectedEmployees) {
                    if (single) {
                        yes = true;
                    }
                }

                if (binding.titleEt.getText().toString().isEmpty()) {
                    binding.titleEt.setError("enter title");
                    binding.titleEt.requestFocus();
                } else if (!atleastOne) {
                    Toast.makeText(CreateEventActivity.this, "Upload atleast 1 image", Toast.LENGTH_SHORT).show();
                } else if (binding.locationET.getText().toString().isEmpty()) {
                    binding.locationET.setError("enter location");
                    binding.locationET.requestFocus();
                } else if (binding.descriptionEt.getText().toString().isEmpty()) {
                    binding.descriptionEt.setError("enter description");
                    binding.descriptionEt.requestFocus();
                } else if (binding.dateEt.getText().toString().isEmpty()) {
                    Toast.makeText(CreateEventActivity.this, "enter date", Toast.LENGTH_SHORT).show();
                } else if (!yes) {
                    Toast.makeText(CreateEventActivity.this, "add atlease 1 member for the event", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                    Toast.makeText(CreateEventActivity.this, "Event Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.uploadImg.setOnClickListener(v -> {
            Intent imgIntent = new Intent(Intent.ACTION_PICK);
            imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imgIntent, 100);
        });

        binding.img1.setOnClickListener(v -> {
            if (binding.first.getTag().equals("filled") && binding.second.getTag().equals("filled") && binding.third.getTag().equals("filled") && binding.fourth.getTag().equals("filled")) {
                binding.first.setImageDrawable(binding.second.getDrawable());
                binding.second.setImageDrawable(binding.third.getDrawable());
                binding.third.setImageDrawable(binding.fourth.getDrawable());
                binding.fourth.setTag("empty");
                binding.fourth.setImageDrawable(null);
            } else if (binding.first.getTag().equals("filled") && binding.second.getTag().equals("filled") && binding.third.getTag().equals("filled") && binding.fourth.getTag().equals("empty")) {
                binding.first.setImageDrawable(binding.second.getDrawable());
                binding.second.setImageDrawable(binding.third.getDrawable());
                binding.third.setTag("empty");
                binding.third.setImageDrawable(null);
            } else if (binding.first.getTag().equals("filled") && binding.second.getTag().equals("filled") && binding.third.getTag().equals("empty") && binding.fourth.getTag().equals("empty")) {
                binding.first.setImageDrawable(binding.second.getDrawable());
                binding.second.setTag("empty");
                binding.second.setImageDrawable(null);
            } else if (binding.first.getTag().equals("filled") && binding.second.getTag().equals("empty") && binding.third.getTag().equals("empty") && binding.fourth.getTag().equals("empty")) {
                binding.first.setTag("empty");
                binding.first.setImageDrawable(null);
            }

            if (binding.first.getDrawable() == null && binding.second.getDrawable() == null && binding.third.getDrawable() == null && binding.fourth.getDrawable() == null) {
                atleastOne = false;
            }
        });

        binding.img2.setOnClickListener(v -> {
            if (binding.second.getTag().equals("filled") && binding.third.getTag().equals("filled") && binding.fourth.getTag().equals("filled")) {
                binding.second.setImageDrawable(binding.third.getDrawable());
                binding.third.setImageDrawable(binding.fourth.getDrawable());
                binding.fourth.setTag("empty");
                binding.fourth.setImageDrawable(null);
            } else if (binding.second.getTag().equals("filled") && binding.third.getTag().equals("filled") && binding.fourth.getTag().equals("empty")) {
                binding.second.setImageDrawable(binding.third.getDrawable());
                binding.third.setTag("empty");
                binding.third.setImageDrawable(null);
            } else if (binding.second.getTag().equals("filled") && binding.third.getTag().equals("empty") && binding.fourth.getTag().equals("empty")) {
                binding.second.setTag("empty");
                binding.second.setImageDrawable(null);
            }
        });

        binding.img3.setOnClickListener(v -> {
            if (binding.third.getTag().equals("filled") && binding.fourth.getTag().equals("filled")) {
                binding.third.setImageDrawable(binding.fourth.getDrawable());
                binding.fourth.setTag("empty");
                binding.fourth.setImageDrawable(null);
            } else if (binding.third.getTag().equals("filled") && binding.fourth.getTag().equals("empty")) {
                binding.third.setTag("empty");
                binding.third.setImageDrawable(null);
            }
        });

        binding.img4.setOnClickListener(v -> {
            if (binding.fourth.getTag().equals("filled")) {
                binding.fourth.setTag("empty");
                binding.fourth.setImageDrawable(null);
            }
        });
        binding.dateEt.setOnClickListener(v -> {

            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        //
                        binding.dateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {
            if (binding.first.getTag().equals("empty")) {
                binding.first.setImageURI(data.getData());
                atleastOne = true;
                binding.first.setTag("filled");
            } else if (binding.second.getTag().equals("empty")) {
                binding.second.setImageURI(data.getData());
                binding.second.setTag("filled");
            } else if (binding.third.getTag().equals("empty")) {
                binding.third.setImageURI(data.getData());
                binding.third.setTag("filled");
            } else if (binding.fourth.getTag().equals("empty")) {
                binding.fourth.setImageURI(data.getData());
                binding.fourth.setTag("filled");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray("selecteds", selectedEmployees);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedEmployees = savedInstanceState.getBooleanArray("selecteds");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}