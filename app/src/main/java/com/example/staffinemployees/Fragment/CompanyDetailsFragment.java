package com.example.staffinemployees.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.staffinemployees.databinding.FragmentCompanyDetailsBinding;


public class CompanyDetailsFragment extends Fragment {
    FragmentCompanyDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompanyDetailsBinding.inflate(inflater, container, false);
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