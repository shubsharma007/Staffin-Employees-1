package com.example.staffinemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.staffinemployees.Fragment.BankDetailsFragment;
import com.example.staffinemployees.Fragment.CompanyDetailsFragment;
import com.example.staffinemployees.Fragment.EventsFragment;
import com.example.staffinemployees.Fragment.MainFragment;
import com.example.staffinemployees.Fragment.UpcomingHolidaysFragment;
import com.example.staffinemployees.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
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