package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentPayrollBinding;


public class PayrollFragment extends Fragment {
FragmentPayrollBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding=FragmentPayrollBinding.inflate(inflater,container,false);



      return binding.getRoot();
    }
}