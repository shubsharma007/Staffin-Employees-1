package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentRequestLeaveBinding;

import java.util.ArrayList;
import java.util.List;

public class RequestLeaveFragment extends Fragment {
    FragmentRequestLeaveBinding binding;
    List<String> leaves;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRequestLeaveBinding.inflate(getLayoutInflater());

        leaves = new ArrayList<>();
        leaves.add("please select leave type");
        leaves.add("Paid Leave");
        leaves.add("Unpaid Leave");
        leaves.add("Sick Leave");


        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, leaves);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.leaveTypeSpinner.setAdapter(aa);

        binding.leaveTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                issueSelected = strArray[position];
                Toast.makeText(getContext(), leaves.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return binding.getRoot();
    }
}