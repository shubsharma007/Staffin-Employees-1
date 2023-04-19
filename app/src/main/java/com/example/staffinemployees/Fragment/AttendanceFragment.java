package com.example.staffinemployees.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.Adapters.AttendanceAdapter;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AttendanceResponse;
import com.example.staffinemployees.Response.GetMonthlyAttendance;
import com.example.staffinemployees.Response.PresentAbsentMix;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentAttendanceBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceFragment extends Fragment {
    FragmentAttendanceBinding binding;
    ApiInterface apiInterface;
    List<AttendanceResponse> attendanceResponseList;
    SharedPreferences sharedPreferences;
    int Id;
    ProgressDialog progress;
    int totalDays, presentCount, absentCount;

    List<PresentAbsentMix> presentAbsentMixList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        Id = Integer.parseInt(sharedPreferences.getAll().get("Id").toString());
        Glide.with(getActivity()).load(sharedPreferences.getAll().get("dp")).into(binding.imageView2);
        binding.textView8.setText(sharedPreferences.getAll().get("name").toString());
        binding.textView9.setText(sharedPreferences.getAll().get("mail").toString());

        progress = new ProgressDialog(getActivity());
        progress.setMessage("please wait...");
        presentAbsentMixList = new ArrayList<>();
        if (getArguments() != null) {
            String month = getArguments().get("month").toString() + " " + getArguments().get("year").toString();
            binding.monthTv.setText(month);

            String apiMonth = getMonthNumber(String.valueOf(getArguments().get("month")));
            String apiYear = getArguments().get("year").toString();
            // id
            progress.show();
            Call<GetMonthlyAttendance> changedFragmentGetAttendanceById = apiInterface.getMonthlyAttendanceByEid(Integer.parseInt(apiMonth), Integer.parseInt(apiYear), Id);
            changedFragmentGetAttendanceById.enqueue(new Callback<GetMonthlyAttendance>() {
                @Override
                public void onResponse(Call<GetMonthlyAttendance> call, Response<GetMonthlyAttendance> response) {
                    if (response.isSuccessful()) {
                        progress.dismiss();
                        totalDays = response.body().getTotalWorkingDays();
                        presentCount = response.body().getPresentDay();
                        absentCount = response.body().getAbsent();
                        binding.all.setText(String.valueOf(totalDays));
                        binding.present.setText(String.valueOf(presentCount));
                        binding.absent.setText(String.valueOf(absentCount));

                        for (GetMonthlyAttendance.AbsentDate a : response.body().getAbsentDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(a.getDate(), "absent", "", ""));
                        }
                        for (GetMonthlyAttendance.PresentDate p : response.body().getPresentDate()) {
                            String clockOut = p.getClock_out();
                            String clockIn = p.getClock_in();
                            presentAbsentMixList.add(new PresentAbsentMix(p.getDate(), "present", clockIn, clockOut));
                        }
                        for (GetMonthlyAttendance.HalfDayDate h : response.body().getHalfdayDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(h.getDate(), "halfDay", h.getClock_in(), h.getClock_out()));
                        }
                        for (GetMonthlyAttendance.LateComingDate l : response.body().getLateComingDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(l.getDate(), "late", l.getClock_in(), l.getClock_out()));
                        }
                        for (GetMonthlyAttendance.PaidLeaveDate p : response.body().getPaidLeaveDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(p.getDate(), "paidLeave", "", ""));
                        }

                        for (PresentAbsentMix pa : presentAbsentMixList) {
                            Log.d("PresentAbsentMix", pa.getStatus() + " - " + pa.getDate());
                        }
                        if (presentAbsentMixList.size() > 0) {
                            binding.recyclerView.setVisibility(View.VISIBLE);
                            binding.noData.setVisibility(View.GONE);
                            binding.recyclerView.setAdapter(new AttendanceAdapter(presentAbsentMixList, getContext()));
                        } else {
                            binding.recyclerView.setVisibility(View.GONE);
                            binding.noData.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Log.d("kfndkfjn", response.message());
                        progress.dismiss();
                        Toast.makeText(getActivity(), "some error orrured", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetMonthlyAttendance> call, Throwable t) {
                    Log.d("kfndkfjn", t.getMessage());
                    progress.dismiss();
                    Toast.makeText(getActivity(), "some failure orrured", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
            month += 1;
            int year = cal.get(Calendar.YEAR);
            progress.show();
            Call<GetMonthlyAttendance> callGetMonthlyAttendanceByEid = apiInterface.getMonthlyAttendanceByEid(month, year, Id);
            callGetMonthlyAttendanceByEid.enqueue(new Callback<GetMonthlyAttendance>() {
                @Override
                public void onResponse(Call<GetMonthlyAttendance> call, Response<GetMonthlyAttendance> response) {
                    if (response.isSuccessful()) {
                        progress.dismiss();
                        totalDays = response.body().getTotalWorkingDays();
                        presentCount = response.body().getPresentDay();
                        absentCount = response.body().getAbsent();
                        binding.all.setText(String.valueOf(totalDays));
                        binding.present.setText(String.valueOf(presentCount));
                        binding.absent.setText(String.valueOf(absentCount));

                        for (GetMonthlyAttendance.AbsentDate a : response.body().getAbsentDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(a.getDate(), "absent", "", ""));
                        }
                        for (GetMonthlyAttendance.PresentDate p : response.body().getPresentDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(p.getDate(), "present", p.getClock_in(), p.getClock_out()));
                        }
                        for (GetMonthlyAttendance.HalfDayDate h : response.body().getHalfdayDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(h.getDate(), "halfDay", h.getClock_in(), h.getClock_out()));
                        }
                        for (GetMonthlyAttendance.LateComingDate l : response.body().getLateComingDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(l.getDate(), "late", l.getClock_in(), l.getClock_out()));
                        }
                        for (GetMonthlyAttendance.PaidLeaveDate p : response.body().getPaidLeaveDate()) {
                            presentAbsentMixList.add(new PresentAbsentMix(p.getDate(), "paidLeave", "", ""));
                        }


                        for (PresentAbsentMix pa : presentAbsentMixList) {
                            Log.d("PresentAbsentMix", pa.getStatus() + " - " + pa.getDate());
                        }
                        if (presentAbsentMixList.size() > 0) {
                            binding.recyclerView.setVisibility(View.VISIBLE);
                            binding.noData.setVisibility(View.GONE);
                            binding.recyclerView.setAdapter(new AttendanceAdapter(presentAbsentMixList, getContext()));
                        } else {
                            binding.recyclerView.setVisibility(View.GONE);
                            binding.noData.setVisibility(View.VISIBLE);
                        }


                    } else {
                        Log.d("kfndkfjn", response.message());
                        progress.dismiss();
                        Toast.makeText(getActivity(), "some error orrured", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetMonthlyAttendance> call, Throwable t) {
                    Log.d("kfndkfjn", t.getMessage());
                    progress.dismiss();
                    Toast.makeText(getActivity(), "some failure orrured", Toast.LENGTH_SHORT).show();
                }
            });
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


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    private String getMonthNumber(String month) {
        switch (month) {
            case "January":
                return "1";

            case "February":
                return "2";

            case "March":
                return "3";

            case "April":
                return "4";

            case "May":
                return "5";

            case "June":
                return "6";

            case "July":
                return "7";

            case "August":
                return "8";

            case "September":
                return "9";

            case "October":
                return "10";

            case "November":
                return "11";

            case "December":
                return "12";
            default:
                return "0";
        }
    }

}