package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.phoneEt.getText().toString().trim().isEmpty()) {
                    binding.phoneEt.setError("Enter Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.phoneEt.getText().toString().trim().length() < 10) {
                    binding.phoneEt.setError("Enter Correct Mobile Number");
                    binding.phoneEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                    binding.passwordEt.setError("Enter Password");
                    binding.passwordEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().length() < 4) {
                    binding.passwordEt.setError("Enter password min 4 words");
                    binding.passwordEt.requestFocus();
                } else {
                    String number = binding.phoneEt.getText().toString();
                    String password = binding.passwordEt.getText().toString();

                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
                    Call<LoginResponse> call = apiInterface.postEmpLogin(number, password);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                                Toast.makeText(LoginActivity.this, "Successful Login...", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("ERROR1",response.message());
                                Log.d("ERROR2", String.valueOf(response.code()));
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "response not successful...        ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("ERROR", t.getMessage());
                        }
                    });


                }
            }
        });

    }
}