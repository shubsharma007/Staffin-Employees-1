package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    LinearLayout ll;
    Toast toast;
    View vieww;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        toast = new Toast(LoginActivity.this);

        // for custom toast
        vieww = getLayoutInflater().inflate(R.layout.custom_toast_layout, (ViewGroup) findViewById(R.id.toastRoot));
        ll = (LinearLayout) vieww.findViewById(R.id.toastBg);
        textView = vieww.findViewById(R.id.textToast);

        binding.forgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Your Password Has Been Sent To Your Email Successfully , Please Check Your Emails", Toast.LENGTH_SHORT).show();
            }
        });

        if (sharedPreferences.getAll().containsKey("mobile")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
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
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
                    Call<LoginResponse> call = apiInterface.postEmpLogin(number, password);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
//                                int userId = response.body().getResultLogin().getId();
//                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                intent.putExtra("userid", userId);
//                                startActivity(intent);


                                ll.setBackgroundResource(R.color.green);
                                textView.setText("Login Successful...");
                                toast.setView(vieww);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                editor.putString("mobile", number);
                                editor.putString("eId", response.body().getResultLogin().getEmployeeID());
                                editor.putString("Id", response.body().getResultLogin().getId().toString());
                                editor.apply();
                                finish();


//                                Toast.makeText(LoginActivity.this, "Login Successful...", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("ERROR1", response.message());
                                Log.d("ERROR2", String.valueOf(response.code()));
                                progressDialog.dismiss();

                                ll.setBackgroundResource(R.color.txtRed);
                                textView.setText("Login failure...");
                                toast.setView(vieww);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progressDialog.dismiss();

                            ll.setBackgroundResource(R.color.txtRed);
                            textView.setText(t.getMessage());
                            toast.setView(vieww);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();

                            Log.d("ERROR", t.getMessage());
                        }
                    });


                }
            }
        });

    }
}