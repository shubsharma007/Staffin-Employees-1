package com.example.staffinemployees;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.Interface.BottomSheetListener;
import com.example.staffinemployees.Maps.MapFragBottomSheet;
import com.example.staffinemployees.Response.EmployeeResult;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Response.TotalEmployeeResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.ActivityCreateEventBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventActivity extends AppCompatActivity  implements BottomSheetListener {
    ActivityCreateEventBinding binding;
    public static final int LOCATION = 1313;
    private static final int REQUEST_CHECK_SETTINGS = 10001;

    List<String> employeesList;
    List<String> profileImages;
    List<String> myProfiles;
    List<Integer> idsList;
    boolean[] selectedIds;
    boolean[] selectedEmployees;
    boolean atleastOne = false;
    ProgressDialog progress;
    ApiInterface apiInterface;
    HashMap<String, String> map;
    List<Integer> add_member;
    List<EmployeeResult> results;
    Uri uriImage1, uriImage2, uriImage3, uriImage4;
    String image1 = null, image2 = null, image3 = null, image4 = null;
    File file1, file2, file3, file4;
    String title, description, date, location;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        requestRuntimePermissionFunc("location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(CreateEventActivity.this);
        clickListeners();
    }

    @Override
    public void onTextSelected(String text) {
        binding.locationEt.setText(text);
    }

    private void requestRuntimePermissionFunc(String location) {
        if (location.equals("location")) {
            if (ContextCompat.checkSelfPermission(CreateEventActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(CreateEventActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setMessage("this permission is required for this and this")
                        .setTitle("location required")
                        .setCancelable(false)
                        .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(CreateEventActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
                            }
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                ActivityCompat.requestPermissions(CreateEventActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "accepted", Toast.LENGTH_SHORT).show();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(CreateEventActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                builder.setMessage("this feature is unavailable , now open settings ")
                        .setTitle("location to chaiye")
                        .setCancelable(false)
                        .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                requestRuntimePermissionFunc("location");
            }
        }
    }

    private boolean isGPSEnabled() {

        LocationManager locationManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        }
        boolean isEnabled = false;

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    private void clickListeners() {
        map = new HashMap<>();
        myProfiles = new ArrayList<>();
        results = new ArrayList<>();
        add_member = new ArrayList<>();
        profileImages = new ArrayList<>();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progress = new ProgressDialog(CreateEventActivity.this);
        progress.setMessage("please wait....");
        employeesList = new ArrayList<>();
        idsList = new ArrayList<>();
        if (isNetworkAvailable()) {
            progress.show();
            Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
            callGetTotalEmployee.enqueue(new Callback<TotalEmployeeResponse>() {
                @Override
                public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                    if (response.isSuccessful()) {
                        ImageView imageview[] = new ImageView[response.body().getEmployeeResult().size()];
                        results = response.body().getEmployeeResult();
                        String[] names = new String[response.body().getEmployeeResult().size()];

                        for (int i = 0; i < response.body().getEmployeeResult().size(); i++) {
                            employeesList.add(response.body().getEmployeeResult().get(i).getFullName());
                            Log.d("dfuhksdf", employeesList.get(i));
                            names[i] = employeesList.get(i);
                            idsList.add(response.body().getEmployeeResult().get(i).getId());
                            profileImages.add(response.body().getEmployeeResult().get(i).getProfileImage());
                            map.put(response.body().getEmployeeResult().get(i).getFullName(), response.body().getEmployeeResult().get(i).getProfileImage());
                        }

                        selectedIds = new boolean[response.body().getEmployeeResult().size()];
                        selectedEmployees = new boolean[response.body().getEmployeeResult().size()];

                        binding.backBtn.setOnClickListener(view -> {
                            finish();
                        });


                        binding.locationEt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Bundle bundle = new Bundle();

//                                bundle.putString("EmployeeId", singleUnit.getEmployeeId());


                                if (isNetworkAvailable()) {
                                    if (ContextCompat.checkSelfPermission(CreateEventActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                        if (isGPSEnabled()) {

                                            Task<Location> task = fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, new CancellationTokenSource().getToken());
                                            task.addOnSuccessListener(CreateEventActivity.this, location -> {
                                                if (location != null) {
                                                    Geocoder geocoder = new Geocoder(CreateEventActivity.this, Locale.getDefault());
                                                    try {
                                                        List<Address> loc = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                                        String currentLocation = loc.get(0).getAddressLine(0) + loc.get(0).getLocality();
                                                        Log.d("Latitude", String.valueOf(location.getLatitude()));
                                                        Log.d("Logni", String.valueOf(location.getLongitude()));
                                                        Log.d("LOCATION_IS", currentLocation);


                                                        MapFragBottomSheet mapFragBottomSheet = new MapFragBottomSheet(CreateEventActivity.this);
                                                        FragmentManager fm = getSupportFragmentManager();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("latitude", String.valueOf(location.getLatitude()));
                                                        bundle.putString("longitude", String.valueOf(location.getLongitude()));
                                                        mapFragBottomSheet.setCancelable(false);
                                                        mapFragBottomSheet.setArguments(bundle);
                                                        mapFragBottomSheet.show(fm, mapFragBottomSheet.getTag());
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    Toast.makeText(CreateEventActivity.this, "Unable To Find Location", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        } else {
                                            turnOnGPS();
                                        }

                                    } else {
                                        Toast.makeText(CreateEventActivity.this, "Location Permission Required , Open Settings And Allow Permission", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(CreateEventActivity.this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        binding.addMemberBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                                builder.setTitle("Add Members");
                                builder.setIcon(R.drawable.img_dp);
                                builder.setMultiChoiceItems(names, selectedEmployees, (dialog, which, isChecked) -> {
                                    selectedEmployees[which] = isChecked;
                                    selectedIds[which] = isChecked;
                                });

                                builder.setCancelable(false);

                                builder.setNeutralButton("CLEAR ALL", (dialog, which) -> {
                                    Arrays.fill(selectedEmployees, false);
                                    Arrays.fill(selectedIds, false);
                                    binding.dynamicLl.removeAllViews();
                                });
                                builder.setNegativeButton("CANCEL", (dialog, which) -> {
                                });
                                builder.setPositiveButton("Done", (dialog, which) -> {
                                    binding.dynamicLl.removeAllViews();
                                    add_member.clear();
                                    myProfiles.clear();


                                    for (int i = 0; i < selectedEmployees.length; i++) {
                                        if (selectedEmployees[i]) {
                                            imageview[i] = new ImageView(CreateEventActivity.this);
                                            imageview[i].setImageResource(R.drawable.img_dp);
                                            View child = getLayoutInflater().inflate(R.layout.add_member_in_event, null);
                                            CircleImageView dp = child.findViewById(R.id.memberDp);
                                            for (EmployeeResult e : results) {
                                                if (Objects.equals(e.getId(), idsList.get(i))) {
                                                    myProfiles.add(e.getProfileImage());
                                                    Glide.with(CreateEventActivity.this).load(e.getProfileImage()).placeholder(R.drawable.img_dp).into(dp);
                                                }
                                            }

                                            binding.dynamicLl.addView(child);
                                        }
                                        if (selectedIds[i]) {
                                            add_member.add(idsList.get(i));
                                        }
                                    }
                                    Log.d("members Id", add_member.toString());
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
                        Log.d("OnResponseElse", response.message());
                    }
                }

                @Override
                public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                    Log.d("onFailure", t.getMessage());
                    progress.dismiss();
                    Toast.makeText(CreateEventActivity.this, "Try to add another event", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
        }


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
                } else if (binding.locationEt.getText().toString().isEmpty()) {
                    binding.locationEt.setError("enter location");
                    binding.locationEt.requestFocus();
                } else if (binding.descriptionEt.getText().toString().isEmpty()) {
                    binding.descriptionEt.setError("enter description");
                    binding.descriptionEt.requestFocus();
                } else if (binding.dateEt.getText().toString().isEmpty()) {
                    Toast.makeText(CreateEventActivity.this, "enter date", Toast.LENGTH_SHORT).show();
                } else if (!yes) {
                    Toast.makeText(CreateEventActivity.this, "add atlease 1 member for the event", Toast.LENGTH_SHORT).show();
                } else {

//                    if (binding.second.getDrawable() == null && binding.third.getDrawable() == null && binding.fourth.getDrawable() == null) {
//                        callAddEventFunc = apiInterface.addEventFunc();
//                    } else if (binding.second.getDrawable() != null && binding.third.getDrawable() == null && binding.fourth.getDrawable() == null) {
//                        callAddEventFunc = apiInterface.addEventFunc();
//                    } else if (binding.second.getDrawable() != null && binding.third.getDrawable() != null && binding.fourth.getDrawable() == null) {
//                        callAddEventFunc = apiInterface.addEventFunc();
//                    } else if (binding.second.getDrawable() != null && binding.third.getDrawable() != null && binding.fourth.getDrawable() != null) {
//                        callAddEventFunc = apiInterface.addEventFunc();
//                    }

                    progress.show();

                    file1 = new File(image1);
                    RequestBody image11 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", file1.getName(), image11);
                    Call<LoginResponse> callAddEventFunc = null;

                    title = binding.titleEt.getText().toString();
                    description = binding.descriptionEt.getText().toString();
                    location = binding.locationEt.getText().toString();
                    date = binding.dateEt.getText().toString();

                    RequestBody tit = RequestBody.create(MediaType.parse("text/plain"), title);
                    RequestBody des = RequestBody.create(MediaType.parse("text/plain"), description);
                    RequestBody loc = RequestBody.create(MediaType.parse("text/plain"), location);
                    RequestBody dat = RequestBody.create(MediaType.parse("text/plain"), date);
////////////////////////////
                    String integerListString = TextUtils.join(",", add_member);
                    RequestBody add_memb = RequestBody.create(MediaType.parse("text/plain"), integerListString);

                    String profileImagesString = TextUtils.join(",,,,,,,,,,", myProfiles);
                    RequestBody add_memb_profiles = RequestBody.create(MediaType.parse("text/plain"), profileImagesString);

                    int count = add_member.size();
                    RequestBody add_memb_count = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(count));
//////////////////////////
                    if (binding.second.getDrawable() == null && binding.third.getDrawable() == null && binding.fourth.getDrawable() == null) {

                        callAddEventFunc = apiInterface.addEventFunc(image, null, null, null, tit, loc, des, dat, add_memb, add_memb_profiles, add_memb_count);

                    } else if (binding.second.getDrawable() != null && binding.third.getDrawable() == null && binding.fourth.getDrawable() == null) {

                        file2 = new File(image2);
                        RequestBody image22 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file2.getName(), image22);

                        callAddEventFunc = apiInterface.addEventFunc(image, image1, null, null, tit, loc, des, dat, add_memb, add_memb_profiles, add_memb_count);

                    } else if (binding.second.getDrawable() != null && binding.third.getDrawable() != null && binding.fourth.getDrawable() == null) {

                        file2 = new File(image2);
                        RequestBody image22 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file2.getName(), image22);
                        file3 = new File(image3);
                        RequestBody image33 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file3.getName(), image33);

                        callAddEventFunc = apiInterface.addEventFunc(image, image1, image2, null, tit, loc, des, dat, add_memb, add_memb_profiles, add_memb_count);

                    } else if (binding.second.getDrawable() != null && binding.third.getDrawable() != null && binding.fourth.getDrawable() != null) {

                        file2 = new File(image2);
                        RequestBody image22 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image1", file2.getName(), image22);
                        file3 = new File(image3);
                        RequestBody image33 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part image2 = MultipartBody.Part.createFormData("image2", file3.getName(), image33);
                        file4 = new File(image4);
                        RequestBody image44 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part image3 = MultipartBody.Part.createFormData("image3", file4.getName(), image44);
                        callAddEventFunc = apiInterface.addEventFunc(image, image1, image2, image3, tit, loc, des, dat, add_memb, add_memb_profiles, add_memb_count);

                    }

                    callAddEventFunc.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                progress.dismiss();
                                Toast.makeText(CreateEventActivity.this, "Event Added Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateEventActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Log.d("jfsdfsd", response.message());
                                Toast.makeText(CreateEventActivity.this, "error occured", Toast.LENGTH_SHORT).show();
                                progress.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(CreateEventActivity.this, "failure", Toast.LENGTH_SHORT).show();
                            Log.d("jkdfsdf", t.getMessage());
                        }
                    });


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
                        binding.dateEt.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    },
                    year, month, day);
            datePickerDialog.show();
        });
    }

    private void turnOnGPS() {

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(CreateEventActivity.this)
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(CreateEventActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;

                                resolvableApiException.startResolutionForResult(CreateEventActivity.this, REQUEST_CHECK_SETTINGS);
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

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {
            if (binding.first.getTag().equals("empty")) {
                binding.first.setImageURI(data.getData());
                uriImage1 = data.getData();
                image1 = getRealPathFromURI(uriImage1);
                atleastOne = true;
                binding.first.setTag("filled");
            } else if (binding.second.getTag().equals("empty")) {
                binding.second.setImageURI(data.getData());
                uriImage2 = data.getData();
                image2 = getRealPathFromURI(uriImage2);
                binding.second.setTag("filled");
            } else if (binding.third.getTag().equals("empty")) {
                binding.third.setImageURI(data.getData());
                uriImage3 = data.getData();
                image3 = getRealPathFromURI(uriImage3);
                binding.third.setTag("filled");
            } else if (binding.fourth.getTag().equals("empty")) {
                binding.fourth.setImageURI(data.getData());
                uriImage4 = data.getData();
                image4 = getRealPathFromURI(uriImage4);
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