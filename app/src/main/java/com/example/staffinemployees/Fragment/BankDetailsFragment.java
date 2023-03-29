package com.example.staffinemployees.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentBankDetailsBinding;

import java.util.Objects;


public class BankDetailsFragment extends Fragment {
    FragmentBankDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding.nextBtn.setOnClickListener(v -> {
            if (binding.holderEt.getText().toString().isEmpty()) {
                binding.holderEt.setError("Enter Holder Name");
                binding.holderEt.requestFocus();
            } else if (binding.accNoEt.getText().toString().trim().isEmpty()) {
                binding.accNoEt.setError("Enter Account Number");
                binding.accNoEt.requestFocus();
            } else if (binding.ifscEt.getText().toString().trim().isEmpty()) {
                binding.ifscEt.setError("Enter IFSC Code");
                binding.ifscEt.requestFocus();
            } else if (binding.bankEt.getText().toString().trim().isEmpty()) {
                binding.bankEt.setError("Enter Bank Name");
                binding.bankEt.requestFocus();
            } else {
                startActivity(new Intent(getActivity(), MainActivity.class));
                requireActivity().finish();

            }
        });


        return binding.getRoot();
    }
}