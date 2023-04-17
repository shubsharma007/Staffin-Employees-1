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
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progress = new ProgressDialog(InsideEvent.this);
        progress.setMessage("please wait...");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Call<TotalEmployeeResponse> callGetTotalEmployee = apiInterface.getTotalEmployee();
        progress.show();
        callGetTotalEmployee.enqueue(new Callback<TotalEmployeeResponse>() {
            @Override
            public void onResponse(Call<TotalEmployeeResponse> call, Response<TotalEmployeeResponse> response) {
                if (response.isSuccessful()) {
                    employees = new String[response.body().getEmployeeResult().size()];

                    for (int i = 0; i < response.body().getEmployeeResult().size(); i++) {
                        employees[i] = response.body().getEmployeeResult().get(i).getFullName();
                    }
                    ArrayAdapter<String> arr;
                    arr = new ArrayAdapter<String>(InsideEvent.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, employees);
                    binding.listView.setAdapter(arr);
                    progress.dismiss();
                    binding.image1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            previewImage();
                        }
                    });
                    binding.image2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            binding.image1.performClick();
                        }
                    });
                    binding.image3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            binding.image1.performClick();
                        }
                    });
                    binding.image4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            binding.image1.performClick();
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
    }

    private void previewImage() {
        Dialog imagePreview = new Dialog(InsideEvent.this);
        imagePreview.setContentView(R.layout.preview_image);
        ImageView image = imagePreview.findViewById(R.id.previewImage);
        image.setImageResource(R.drawable.image_employee);
        imagePreview.show();
    }
}