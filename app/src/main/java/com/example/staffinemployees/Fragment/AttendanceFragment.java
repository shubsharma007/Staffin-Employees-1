package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.staffinemployees.Adapters.AttendanceAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AttendanceResponse;
import com.example.staffinemployees.databinding.FragmentAttendanceBinding;

import java.util.ArrayList;
import java.util.List;

public class AttendanceFragment extends Fragment {
    FragmentAttendanceBinding binding;

    List<AttendanceResponse> attendanceResponseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "Mai Hu Naa", Toast.LENGTH_SHORT).show();
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        binding.txtRight.setOnClickListener(v -> {
//            Fragment frg = null;
//
//            frg = getParentFragmentManager().findFragmentByTag("attendance");
//            final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//            Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
//            ft.detach(frg);
//
//            ft.attach(frg);
//            ft.commit();
        });
        attendanceResponseList = new ArrayList<>();
        attendanceResponseList.add(new AttendanceResponse("Thu", "22", "Present", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Fri", "23", "Absent", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Sat", "24", "Present", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Sun", "25", "Absent", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Mon", "26", "Present", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Tue", "27", "Absent", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Wed", "28", "Present", "12:00", "08:00"));
        attendanceResponseList.add(new AttendanceResponse("Thu", "29", "Absent", "12:00", "08:00"));


        binding.recyclerView.setAdapter(new AttendanceAdapter(attendanceResponseList, getContext()));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }
}