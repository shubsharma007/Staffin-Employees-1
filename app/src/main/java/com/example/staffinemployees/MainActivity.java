package com.example.staffinemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.staffinemployees.Fragment.AttendanceFragment;
import com.example.staffinemployees.Fragment.BankDetailsFragment;
import com.example.staffinemployees.Fragment.ClaimExpences;
import com.example.staffinemployees.Fragment.CompanyDetailsFragment;
import com.example.staffinemployees.Fragment.EventsFragment;
import com.example.staffinemployees.Fragment.LeaveRequest;
import com.example.staffinemployees.Fragment.MainFragment;
import com.example.staffinemployees.Fragment.Payslip;
import com.example.staffinemployees.Fragment.UpcomingHolidaysFragment;
import com.example.staffinemployees.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;



    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int userId = getIntent().getIntExtra("userid", 0);
        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


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
                        binding.textView.setText("Company Details");
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AttendanceFragment()).commit();
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
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        editor.remove("mobile");
                        editor.apply();
                        finish();
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


}