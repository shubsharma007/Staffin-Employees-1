package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.Response.TotalEmployeeResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.ActivityInsideEventBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsideEvent extends AppCompatActivity {
    ActivityInsideEventBinding binding;
    ApiInterface apiInterface;
    private static final String TAG = "InsideEvent";
    String[] employees;

    List<String> invitedMembers;
    ProgressDialog progress;
    String image, image1, image2, image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progress = new ProgressDialog(InsideEvent.this);
        progress.setMessage("please wait...");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        invitedMembers = new ArrayList<>();


        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String date = getIntent().getStringExtra("date");
        String location = getIntent().getStringExtra("location");
        ArrayList<String> members = getIntent().getStringArrayListExtra("members");
        ArrayList<String> interested = getIntent().getStringArrayListExtra("interested");


        Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
        progress.show();
        callGetTotalEmployee.enqueue(new Callback<TotalEmployeeResponse>() {
            @Override
            public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                if (response.isSuccessful()) {
                    employees = new String[response.body().getEmployeeResult().size()];
                    invitedMembers.clear();
                    for (int i = 0; i < response.body().getEmployeeResult().size(); i++) {
                        employees[i] = response.body().getEmployeeResult().get(i).getFullName();

                        for (String member : members) {
                            Log.d("nfsdfsdf", member);
                            if (String.valueOf(response.body().getEmployeeResult().get(i).getId()).equals(member))
                                invitedMembers.add(response.body().getEmployeeResult().get(i).getFullName());
                        }
                    }

                    if (!(getIntent().getStringExtra("image").equals("") || getIntent().getStringExtra("image") == null)) {
                        image = getIntent().getStringExtra("image");
                        Glide.with(getApplicationContext()).load(image).into(binding.image1);
                    } else {
                        binding.image1.setImageResource(R.drawable.img_event_placeholder);
                    }
                    if (!(getIntent().getStringExtra("image1").equals("") || getIntent().getStringExtra("image1") == null)) {
                        image1 = getIntent().getStringExtra("image1");
                        Glide.with(getApplicationContext()).load(image1).into(binding.image2);
                    } else {
                        binding.image2.setImageResource(R.drawable.img_event_placeholder);
                    }
                    if (!(getIntent().getStringExtra("image2").equals("") || getIntent().getStringExtra("image2") == null)) {
                        image2 = getIntent().getStringExtra("image2");
                        Glide.with(getApplicationContext()).load(image2).into(binding.image3);
                    } else {
                        binding.image3.setImageResource(R.drawable.img_event_placeholder);
                    }
                    if (!(getIntent().getStringExtra("image3").equals("") || getIntent().getStringExtra("image3") == null)) {
                        image3 = getIntent().getStringExtra("image3");
                        Glide.with(getApplicationContext()).load(image3).into(binding.image4);
                    } else {
                        binding.image4.setImageResource(R.drawable.img_event_placeholder);
                    }

                    binding.title.setText(title);
                    binding.desc.setText(desc);
                    binding.date.setText(date);
                    binding.location.setText(location);

                    if (interested != null) {
                        ArrayAdapter<String> interest;
                        interest = new ArrayAdapter<>(InsideEvent.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, interested);
                        binding.listViewInterested.setAdapter(interest);
                        binding.listViewInterested.setVisibility(View.VISIBLE);
                        binding.interestedTv.setVisibility(View.VISIBLE);
                    } else {
                        binding.listViewInterested.setVisibility(View.GONE);
                        binding.interestedTv.setVisibility(View.GONE);
                    }
                    ArrayAdapter<String> arr;
                    arr = new ArrayAdapter<>(InsideEvent.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, invitedMembers);
                    binding.listView.setAdapter(arr);


                    progress.dismiss();
                    binding.image1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            previewImage(image);
                        }
                    });
                    binding.image2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            previewImage(image1);
                        }
                    });
                    binding.image3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            previewImage(image2);
                        }
                    });
                    binding.image4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            previewImage(image3);
                        }
                    });


                } else {
                    progress.dismiss();
                    Log.d(TAG, "onResponse: " + response.message());
                    Toast.makeText(InsideEvent.this, "some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TotalEmployeeResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(InsideEvent.this, "failed to fetch data", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void previewImage(String img) {
        Dialog imagePreview = new Dialog(InsideEvent.this);
        imagePreview.setContentView(R.layout.preview_image);
        ImageView image = imagePreview.findViewById(R.id.previewImage);
        Glide.with(getApplicationContext()).load(img).placeholder(R.drawable.img_event_placeholder).into(image);
        imagePreview.show();
    }
}