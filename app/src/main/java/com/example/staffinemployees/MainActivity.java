package com.example.staffinemployees;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.Fragment.AttendanceFragment;
import com.example.staffinemployees.Fragment.BankDetailsFragment;
import com.example.staffinemployees.Fragment.ClaimExpences;
import com.example.staffinemployees.Fragment.CompanyDetailsFragment;
import com.example.staffinemployees.Fragment.EventsFragment;
import com.example.staffinemployees.Fragment.LeaveRequest;
import com.example.staffinemployees.Fragment.MainFragment;
import com.example.staffinemployees.Fragment.Payslip;
import com.example.staffinemployees.Fragment.UpcomingHolidaysFragment;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.Response.EmployeeProfileResponse;
import com.example.staffinemployees.Response.EmployeeResult;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    String dp, mail, name;
    private static long backPressed;
    private static final int TIME_DELAY = 2000;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ApiInterface apiInterface;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
//        int userId = getIntent().getIntExtra("userid", 0);
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        id = sharedPreferences.getAll().get("Id").toString();
        userProfileImage();


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();

//
//        Bundle bundle = new Bundle();
//        bundle.putInt("userId", userId);
//        Fragment fragment = new MainFragment();
//        fragment.setArguments(bundle);
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.container, fragment).commit();


        binding.textView.setText("Home");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar
                , 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);

        binding.drawernavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
                        binding.textView.setText("Home");
                        break;
                    case R.id.nav_bank_details:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BankDetailsFragment()).commit();
                        binding.textView.setText("Bank Details");
                        break;
                    case R.id.nav_company_details:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CompanyDetailsFragment()).commit();
                        binding.textView.setText("Employee Details");
                        break;
                    case R.id.nav_upcoming_holidays:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new UpcomingHolidaysFragment()).commit();
                        binding.textView.setText("Holidays");
                        break;
                    case R.id.nav_events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new EventsFragment()).commit();
                        binding.textView.setText("Events");
                        break;
                    case R.id.nav_attendance:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendanceFragment(), "attendance").commit();
                        binding.textView.setText("Attendance");
                        break;
//                    case R.id.nav_payroll:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PayrollFragment()).commit();
//                        binding.textView.setText("Payroll");
//                        break;
                    case R.id.nav_payslip:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Payslip()).commit();
                        binding.textView.setText("PaySlip");
                        break;
                    case R.id.nav_claim_expences:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ClaimExpences()).commit();
                        binding.textView.setText("Expenses");
                        break;
                    case R.id.nav_leave_request:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new LeaveRequest()).commit();
                        binding.textView.setText("Leave");
                        break;

                    case R.id.nav_logout:

                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Logout");
                        dialog.setCancelable(false);
                        dialog.setMessage("Are you sure");

                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                editor.remove("mobile");
                                editor.remove("eId");
                                editor.remove("Id");
                                editor.remove("dp");
                                editor.remove("name");
                                editor.remove("mail");

                                editor.apply();
                                finish();

                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                        break;
                }
                binding.drawerLayout.close();
                return false;
            }
        });

        binding.dpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PersonalDetailsActivity.class));
            }
        });
    }

    private void userProfileImage() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Call<EmployeeProfileResponse> employeeProfileResponseCall = apiInterface.getEmployeeProfile(Integer.parseInt(id));
        employeeProfileResponseCall.enqueue(new Callback<EmployeeProfileResponse>() {
            @Override
            public void onResponse(Call<EmployeeProfileResponse> call, Response<EmployeeProfileResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    EmployeeResult singleUser = response.body().getEmployeeResult().get(0);
                    dp = singleUser.getProfileImage();
                    mail = singleUser.getEmail();
                    name = singleUser.getFullName();
                    editor.putString("dp", dp);
                    editor.putString("mail", mail);
                    editor.putString("name", name);
                    editor.commit();
                    Glide.with(getApplicationContext()).load(singleUser.getProfileImage()).placeholder(R.drawable.img_dp).into(binding.dpImg);

                } else {
                    progressDialog.dismiss();
                    Log.d("dkfnsdf", response.message());
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeeProfileResponse> call, Throwable t) {
                Log.d("nsdfsdf", t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        if (backPressed + TIME_DELAY > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }
}