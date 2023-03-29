package com.example.staffinemployees.Fragment;

import static com.example.staffinemployees.Fragment.MainFragment.hour;
import static com.example.staffinemployees.Fragment.MainFragment.minute;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentCompanyDetailsBinding;

import java.util.Calendar;
import java.util.Objects;
import androidx.fragment.app.Fragment;

import com.example.staffinemployees.databinding.FragmentCompanyDetailsBinding;


public class CompanyDetailsFragment extends Fragment {
    FragmentCompanyDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompanyDetailsBinding.inflate(inflater, container, false);
        adDialog = new Dialog(getActivity());


        binding.addOvertimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        binding.btnNext.setOnClickListener(v -> {


//            if (binding.employeeIdEt.getText().toString().isEmpty()) {
//                binding.employeeIdEt.setError("Enter Id");
//                binding.employeeIdEt.requestFocus();
//            }
////            else if (binding.departmentEt.getSelectedItem() != "Please select Department") {
////                Toast.makeText(this, "Please select Department", Toast.LENGTH_SHORT).show();
////            } else if (binding.designationEt.getSelectedItem() != "Please select Designation") {
////                Toast.makeText(this, "Please select Department", Toast.LENGTH_SHORT).show();
////            }
//            else
            if (binding.annualLeaveEt.getText().toString().isEmpty()) {
                binding.annualLeaveEt.setError("Enter Annual Leaves");
                binding.annualLeaveEt.requestFocus();
            } else if (binding.medicalLeaveEt.getText().toString().isEmpty()) {
                binding.medicalLeaveEt.setError("Enter Medical Leaves");
                binding.medicalLeaveEt.requestFocus();
            } else if (binding.jDateEt.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Joining Date", Toast.LENGTH_SHORT).show();
            } else if (binding.rDateEt.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Relieving Date", Toast.LENGTH_SHORT).show();
            } else if (!binding.rbActive.isChecked() && !binding.rbInactive.isChecked()) {
                Toast.makeText(getActivity(), "Please Select Status", Toast.LENGTH_SHORT).show();
            } else if (binding.basicEt.getText().toString().isEmpty()) {
                binding.basicEt.setError("Enter Basic Salary");
                binding.basicEt.requestFocus();
            } else if (binding.hourlyEt.getText().toString().isEmpty()) {
                binding.hourlyEt.setError("Enter Hourly Rate");
                binding.hourlyEt.requestFocus();
            } else {
                startActivity(new Intent(getActivity(), MainActivity.class));
                requireActivity().finish();
            }

        });
        binding.jDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.jDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        binding.rDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.rDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        binding.employeeIdEt.setKeyListener(null);
        binding.annualLeaveEt.setKeyListener(null);
        binding.basicEt.setKeyListener(null);
        binding.departmentEt.setKeyListener(null);
        binding.designationEt.setKeyListener(null);
        binding.jDateEt.setKeyListener(null);
        binding.rDateEt.setKeyListener(null);
        binding.hourlyEt.setKeyListener(null);
        binding.medicalLeaveEt.setKeyListener(null);

        return binding.getRoot();
    }
}