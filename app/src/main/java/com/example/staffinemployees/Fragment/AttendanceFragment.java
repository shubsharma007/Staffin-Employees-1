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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}