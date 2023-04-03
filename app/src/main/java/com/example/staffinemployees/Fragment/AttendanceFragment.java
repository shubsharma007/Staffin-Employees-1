package com.example.staffinemployees.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.staffinemployees.Adapters.AttendanceAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AttendanceResponse;
import com.example.staffinemployees.databinding.FragmentAttendanceBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AttendanceFragment extends Fragment {
    FragmentAttendanceBinding binding;

    List<AttendanceResponse> attendanceResponseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);


        if (getArguments() != null) {
            String month = getArguments().get("month").toString() + " " + getArguments().get("year").toString();
            binding.monthTv.setText(month);
        } else {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
            month += 1;
            int year = cal.get(Calendar.YEAR);
            switch (month) {
                case 1:
                    binding.monthTv.setText("January " + year);
                    break;
                case 2:
                    binding.monthTv.setText("February " + year);
                    break;
                case 3:
                    binding.monthTv.setText("March " + year);
                    break;
                case 4:
                    binding.monthTv.setText("April " + year);
                    break;
                case 5:
                    binding.monthTv.setText("May " + year);
                    break;
                case 6:
                    binding.monthTv.setText("June " + year);
                    break;
                case 7:
                    binding.monthTv.setText("July " + year);
                    break;
                case 8:
                    binding.monthTv.setText("August " + year);
                    break;
                case 9:
                    binding.monthTv.setText("September " + year);
                    break;
                case 10:
                    binding.monthTv.setText("October " + year);
                    break;
                case 11:
                    binding.monthTv.setText("November " + year);
                    break;
                case 12:
                    binding.monthTv.setText("December " + year);
                    break;
            }
        }


        binding.txtRight.setOnClickListener(v -> {

            String monthOnly[] = binding.monthTv.getText().toString().split(" ");
            String monthToPass = "";
            String yearToPass = "";
            if (monthOnly[0].trim().equalsIgnoreCase("December")) {
                int x = Integer.parseInt(monthOnly[1].trim()) + 1;
                yearToPass = String.valueOf(x);
            } else {
                yearToPass = String.valueOf(Integer.parseInt(monthOnly[1].trim()));
            }
            switch (monthOnly[0].trim()) {
                case "January":
                    monthToPass = "February";
                    break;
                case "February":
                    monthToPass = "March";
                    break;
                case "March":
                    monthToPass = "April";
                    break;
                case "April":
                    monthToPass = "May";
                    break;
                case "May":
                    monthToPass = "June";
                    break;
                case "June":
                    monthToPass = "July";
                    break;
                case "July":
                    monthToPass = "August";
                    break;
                case "August":
                    monthToPass = "September";
                    break;
                case "September":
                    monthToPass = "October";
                    break;
                case "October":
                    monthToPass = "November";
                    break;
                case "November":
                    monthToPass = "December";
                    break;
                case "December":
                    monthToPass = "January";
                    break;
            }

            AttendanceFragment oldFragment = (AttendanceFragment) getParentFragmentManager().findFragmentById(R.id.container);
            AttendanceFragment newFragment = new AttendanceFragment();

            Bundle bundle = new Bundle();
            bundle.putString("month", monthToPass);
            bundle.putString("year", yearToPass);
            newFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction()
                    .remove(oldFragment)
                    .add(R.id.container, newFragment)
                    .commit();

        });


        binding.txtLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthOnly[] = binding.monthTv.getText().toString().split(" ");
                String monthToPass = "";
                String yearToPass = "";
                if (monthOnly[0].trim().equalsIgnoreCase("January")) {
                    int x = Integer.parseInt(monthOnly[1].trim()) - 1;
                    yearToPass = String.valueOf(x);
                } else {
                    yearToPass = String.valueOf(Integer.parseInt(monthOnly[1].trim()));
                }
                switch (monthOnly[0].trim()) {
                    case "January":
                        monthToPass = "December";
                        break;
                    case "February":
                        monthToPass = "January";
                        break;
                    case "March":
                        monthToPass = "February";
                        break;
                    case "April":
                        monthToPass = "March";
                        break;
                    case "May":
                        monthToPass = "April";
                        break;
                    case "June":
                        monthToPass = "May";
                        break;
                    case "July":
                        monthToPass = "June";
                        break;
                    case "August":
                        monthToPass = "July";
                        break;
                    case "September":
                        monthToPass = "August";
                        break;
                    case "October":
                        monthToPass = "September";
                        break;
                    case "November":
                        monthToPass = "October";
                        break;
                    case "December":
                        monthToPass = "November";
                        break;
                }

                AttendanceFragment oldFragment = (AttendanceFragment) getParentFragmentManager().findFragmentById(R.id.container);
                AttendanceFragment newFragment = new AttendanceFragment();

                Bundle bundle = new Bundle();
                bundle.putString("month", monthToPass);
                bundle.putString("year", yearToPass);
                newFragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction()
                        .remove(oldFragment)
                        .add(R.id.container, newFragment)
                        .commit();

            }
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