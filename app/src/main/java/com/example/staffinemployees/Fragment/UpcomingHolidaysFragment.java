package com.example.staffinemployees.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffinemployees.Adapters.HolidayAdapter;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AllHolidays;
import com.example.staffinemployees.Response.HolidayResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentUpcomingHolidaysBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingHolidaysFragment extends Fragment {
    FragmentUpcomingHolidaysBinding binding;
    Context context;
    ApiInterface apiInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpcomingHolidaysBinding.inflate(inflater, container, false);
        monthSetByDefault();
        clickListeners();
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("please wait.......");
        dialog.show();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        if (isNetworkAvailable()) {
            Call<HolidayResponse> callGetAllHolidays = apiInterface.getAllHolidays();
            callGetAllHolidays.enqueue(new Callback<HolidayResponse>() {
                @Override
                public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                    if (response.isSuccessful()) {
                        binding.holidayRecyclerView.setVisibility(View.VISIBLE);
                        binding.notFoundLayout.setVisibility(View.INVISIBLE);
                        HolidayResponse holidayResp = response.body();
                        List<AllHolidays> holidays = holidayResp.getHoliday_list();

                        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(getContext(), holidays));
                        initializeCalendar(holidays);
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                        binding.holidayRecyclerView.setVisibility(View.INVISIBLE);
                        binding.notFoundLayout.setVisibility(View.VISIBLE);
                        Log.d("kfndkfjn", response.message());
                        Toast.makeText(getActivity(), "unable to get information", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<HolidayResponse> call, Throwable t) {
                    Log.d("kfndkfjn", t.getMessage());
                    dialog.dismiss();
                    binding.holidayRecyclerView.setVisibility(View.INVISIBLE);
                    binding.notFoundLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "some failure orrured", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();

        }


        return binding.getRoot();
    }

    private void monthSetByDefault() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
        month += 1;
        int year = cal.get(Calendar.YEAR);
        switch (month) {
            case 1:
                binding.monthTv.setText("January  " + year);
                break;
            case 2:
                binding.monthTv.setText("February  " + year);
                break;
            case 3:
                binding.monthTv.setText("March  " + year);
                break;
            case 4:
                binding.monthTv.setText("April  " + year);
                break;
            case 5:
                binding.monthTv.setText("May  " + year);
                break;
            case 6:
                binding.monthTv.setText("June  " + year);
                break;
            case 7:
                binding.monthTv.setText("July  " + year);
                break;
            case 8:
                binding.monthTv.setText("August  " + year);
                break;
            case 9:
                binding.monthTv.setText("September  " + year);
                break;
            case 10:
                binding.monthTv.setText("October  " + year);
                break;
            case 11:
                binding.monthTv.setText("November  " + year);
                break;
            case 12:
                binding.monthTv.setText("December  " + year);
                break;
        }

    }

    private void clickListeners() {
        binding.rightScroll.setOnClickListener(v -> {
            binding.compactcalendarView.scrollRight();
        });
        binding.leftScroll.setOnClickListener(v -> {
            binding.compactcalendarView.scrollLeft();
        });
        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {


            @Override
            public void onDayClick(Date dateClicked) {
//                List<Event> events = binding.compactcalendarView.getEvents(dateClicked);
//
//                Bundle bundle = new Bundle();
//
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(dateClicked);
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
//                month += 1;
//                Log.d("DATE", String.valueOf(day) + month + year);
//                bundle.putString("Date", day + "-" + month + "-" + year);
//                PresentBottomSheetFragment presentBottomSheetFragment = new PresentBottomSheetFragment();
//                presentBottomSheetFragment.setArguments(bundle);
//                presentBottomSheetFragment.show(getSupportFragmentManager(), presentBottomSheetFragment.getTag());
//
//                Log.d("CLICKED", "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(firstDayOfNewMonth);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
                month += 1;
                Log.d("CLICKED", "Month was scrolled to: " + month);
                switch (month) {
                    case 1:
                        binding.monthTv.setText("January  " + year);
                        break;
                    case 2:
                        binding.monthTv.setText("February  " + year);
                        break;
                    case 3:
                        binding.monthTv.setText("March  " + year);
                        break;
                    case 4:
                        binding.monthTv.setText("April  " + year);
                        break;
                    case 5:
                        binding.monthTv.setText("May  " + year);
                        break;
                    case 6:
                        binding.monthTv.setText("June  " + year);
                        break;
                    case 7:
                        binding.monthTv.setText("July  " + year);
                        break;
                    case 8:
                        binding.monthTv.setText("August  " + year);
                        break;
                    case 9:
                        binding.monthTv.setText("September  " + year);
                        break;
                    case 10:
                        binding.monthTv.setText("October  " + year);
                        break;
                    case 11:
                        binding.monthTv.setText("November  " + year);
                        break;
                    case 12:
                        binding.monthTv.setText("December  " + year);
                        break;
                }
            }


        });

    }

    private void initializeCalendar(List<AllHolidays> allHolidays) {
        CompactCalendarView compactCalendarView = getActivity().findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        int count = 0;

        long milliTime;

        for (AllHolidays singleUnit : allHolidays) {
            String[] dateInParts = singleUnit.getDate().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            Event e;
            if (count % 3 == 0) {
                e = new Event(getResources().getColor(R.color.mainColor), milliTime, singleUnit.getHoliday_description());
            } else if (count % 5 == 0) {
                e = new Event(getResources().getColor(R.color.yellow), milliTime, singleUnit.getHoliday_description());

            } else if (count % 2 == 0) {
                e = new Event(getResources().getColor(R.color.pink), milliTime, singleUnit.getHoliday_description());

            } else {
                e = new Event(getResources().getColor(R.color.green), milliTime, singleUnit.getHoliday_description());
            }
            count++;
            binding.compactcalendarView.addEvent(e);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}