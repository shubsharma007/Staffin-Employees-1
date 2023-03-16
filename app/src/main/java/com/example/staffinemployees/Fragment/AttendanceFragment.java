package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentAttendanceBinding;

public class AttendanceFragment extends Fragment {
    FragmentAttendanceBinding binding;
    String issueSelected;

    String[] shift = {"10:00 AM to 07:00 PM",
            "10:00 PM to 07:00 AM",
            "05:00 AM to 02:00 PM"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, shift);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(aa);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {;
                issueSelected = shift[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return binding.getRoot();
    }
}