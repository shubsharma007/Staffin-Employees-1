package com.example.staffinemployees.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.Adapters.HolidayAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentUpcomingHolidaysBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class UpcomingHolidaysFragment extends Fragment {
    FragmentUpcomingHolidaysBinding binding;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpcomingHolidaysBinding.inflate(inflater, container, false);

        initializeCalendar();

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

        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(getContext()));


        return binding.getRoot();
    }

    private void initializeCalendar() {
        long milliTime;


        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2023);
        calendar1.set(Calendar.MONTH, 3);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        milliTime = calendar1.getTimeInMillis();
        Event ev1 = new Event(getResources().getColor(R.color.txtRed), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2023);
        calendar2.set(Calendar.MONTH, 3);
        calendar2.set(Calendar.DAY_OF_MONTH, 2);
        milliTime = calendar2.getTimeInMillis();
        Event ev2 = new Event(getResources().getColor(R.color.yellow), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.YEAR, 2023);
        calendar3.set(Calendar.MONTH, 3);
        calendar3.set(Calendar.DAY_OF_MONTH, 3);
        milliTime = calendar3.getTimeInMillis();
        Event ev3 = new Event(getResources().getColor(R.color.green), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev3);

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(Calendar.YEAR, 2023);
        calendar4.set(Calendar.MONTH, 3);
        calendar4.set(Calendar.DAY_OF_MONTH, 4);
        milliTime = calendar4.getTimeInMillis();
        Event ev4 = new Event(getResources().getColor(R.color.bluetext), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev4);

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(Calendar.YEAR, 2023);
        calendar5.set(Calendar.MONTH, 3);
        calendar5.set(Calendar.DAY_OF_MONTH, 5);
        milliTime = calendar5.getTimeInMillis();
        Event ev5 = new Event(getResources().getColor(R.color.txtGray), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev5);

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(Calendar.YEAR, 2023);
        calendar6.set(Calendar.MONTH, 3);
        calendar6.set(Calendar.DAY_OF_MONTH, 6);
        milliTime = calendar6.getTimeInMillis();
        Event ev6 = new Event(getResources().getColor(R.color.txtPurple), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev6);


        Calendar calendar7 = Calendar.getInstance();
        calendar7.set(Calendar.YEAR, 2023);
        calendar7.set(Calendar.MONTH, 3);
        calendar7.set(Calendar.DAY_OF_MONTH, 7);
        milliTime = calendar7.getTimeInMillis();
        Event ev7 = new Event(getResources().getColor(R.color.black), milliTime, "Teachers' Professional Day");
        binding.compactcalendarView.addEvent(ev7);


        Calendar calendar10 = Calendar.getInstance();
        calendar10.set(Calendar.YEAR, 2023);
        calendar10.set(Calendar.MONTH, 3);
        calendar10.set(Calendar.DAY_OF_MONTH, 29);
        milliTime = calendar10.getTimeInMillis();
        Event ev10 = new Event(getResources().getColor(R.color.purple_200), milliTime, "Ram Navami");
        binding.compactcalendarView.addEvent(ev10);


    }

}