package com.example.staffinemployees.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.staffinemployees.Response.CreatedHolidayResp;
import com.example.staffinemployees.Response.HolidayResponse;
import com.example.staffinemployees.Response.NationalCreatedMix;
import com.example.staffinemployees.Response.NationalHolidayResp;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentUpcomingHolidaysBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
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

    ApiInterface apiInterface;
    private static final String TAG = "CalendarSettingActivity";
    ProgressDialog progressDialog;
    List<NationalCreatedMix> nationalCreatedMixList;
    List<NationalCreatedMix> nationalCreatedMixListMonthly;
    List<NationalCreatedMix> nationalCreatedMixListWithoutT;
    List<NationalCreatedMix> nationalCreatedMixListMonthlyWithoutT;

    List<NationalHolidayResp.Response.Holiday> nationalHolidaysYearly;
    List<CreatedHolidayResp.Holiday> createdHolidays;
    List<NationalHolidayResp.Response.Holiday> nationalHolidaysMonthly;
    String[] dateInParts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpcomingHolidaysBinding.inflate(inflater, container, false);
        nationalCreatedMixList = new ArrayList<>();
        nationalCreatedMixListMonthly = new ArrayList<>();
        nationalCreatedMixListWithoutT = new ArrayList<>();
        nationalCreatedMixListMonthlyWithoutT = new ArrayList<>();

        nationalHolidaysYearly = new ArrayList<>();
        createdHolidays = new ArrayList<>();
        nationalHolidaysMonthly = new ArrayList<>();

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);


        if (isNetworkAvailable()) {
            monthSetByDefault();

            binding.rightScroll.setOnClickListener(v -> {
                binding.compactcalendarViewForHolidays.scrollRight();
            });
            binding.leftScroll.setOnClickListener(v -> {
                binding.compactcalendarViewForHolidays.scrollLeft();
            });

            binding.compactcalendarViewForHolidays.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {

                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(firstDayOfNewMonth);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
//                apiCallNationalAndCreatedHoliday(year);
//                apiCallNationalAndCreatedHolidayMonthly(year, month);

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

                    apiCallNationalAndCreatedHoliday(year, month);
                }

            });
        } else {
            Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();
        }


        return binding.getRoot();
    }

    private void apiCallNationalAndCreatedHoliday(int year, int month) {
        if (isNetworkAvailable()) {
            progressDialog.show();

            Call<NationalHolidayResp> callGetNationalHoliday = apiInterface.getNationalHoliday("my", year);
            callGetNationalHoliday.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<NationalHolidayResp> call, Response<NationalHolidayResp> response) {
                    if (response.isSuccessful()) {
                        nationalHolidaysYearly = response.body().getResponse().getHolidays();

                        Call<CreatedHolidayResp> callGetCreatedHolidays = apiInterface.getCreatedHolidays();
                        callGetCreatedHolidays.enqueue(new Callback<>() {
                            @Override
                            public void onResponse(Call<CreatedHolidayResp> call, Response<CreatedHolidayResp> response) {
                                if (response.isSuccessful()) {
                                    createdHolidays = response.body().getHolidayList();

                                    Call<NationalHolidayResp> callGetNationalHolidayMonthly = apiInterface.getNationalHolidayMonthly("my", month, year);
                                    callGetNationalHolidayMonthly.enqueue(new Callback<NationalHolidayResp>() {
                                        @Override
                                        public void onResponse(Call<NationalHolidayResp> call, Response<NationalHolidayResp> response) {
                                            if (response.isSuccessful()) {
                                                nationalHolidaysMonthly = response.body().getResponse().getHolidays();
                                                nationalCreatedMixList.clear();
                                                nationalCreatedMixListMonthly.clear();
                                                nationalCreatedMixListWithoutT.clear();
                                                nationalCreatedMixListMonthlyWithoutT.clear();
                                                for (NationalHolidayResp.Response.Holiday N : nationalHolidaysYearly) {
                                                    nationalCreatedMixList.add(new NationalCreatedMix(N.getName(), N.getDate().getIso(), N.getDescription(), "national"));
                                                }
                                                for (CreatedHolidayResp.Holiday C : createdHolidays) {
                                                    nationalCreatedMixList.add(new NationalCreatedMix(C.getOccassion(), C.getDate(), C.getHolidayDescription(), "created"));
                                                }

                                                for (CreatedHolidayResp.Holiday cc : createdHolidays) {
                                                    nationalCreatedMixListMonthly.add(new NationalCreatedMix(cc.getOccassion(), cc.getDate(), cc.getHolidayDescription(), "created"));
                                                }

                                                for (NationalHolidayResp.Response.Holiday hh : nationalHolidaysMonthly) {
                                                    nationalCreatedMixListMonthly.add(new NationalCreatedMix(hh.getName(), hh.getDate().getIso(), hh.getDescription(), "national"));
                                                }

                                                for (NationalCreatedMix m : nationalCreatedMixList) {
                                                    if (m.getDate().contains("T")) {
                                                        nationalCreatedMixListWithoutT.add(new NationalCreatedMix(m.getName(), m.getDate().split("T")[0], m.getDesc(), m.getType()));
                                                    } else {
                                                        nationalCreatedMixListWithoutT.add(m);
                                                    }
                                                }
                                                for (NationalCreatedMix m : nationalCreatedMixListMonthly) {
                                                    if (m.getDate().contains("T")) {
                                                        nationalCreatedMixListMonthlyWithoutT.add(new NationalCreatedMix(m.getName(), m.getDate().split("T")[0], m.getDesc(), m.getType()));
                                                    } else {
                                                        nationalCreatedMixListMonthlyWithoutT.add(m);
                                                    }
                                                }
                                                for (NationalCreatedMix mm : nationalCreatedMixListMonthlyWithoutT) {
                                                    Log.d(TAG, "nationalCreatedMixMonthly" + mm.getName() + "----" + mm.getDate() + "----" + mm.getType());
                                                }
                                                for (NationalCreatedMix m : nationalCreatedMixListWithoutT) {
                                                    Log.d(TAG, "nationalCreatedMix: " + String.valueOf(m.getName()) + "----" + m.getDate() + "----" + m.getType());
                                                }
                                                progressDialog.dismiss();
                                                initializeCalendar(nationalCreatedMixListWithoutT);
                                                ArrayList<NationalCreatedMix> fragmentData = new ArrayList<>(nationalCreatedMixListMonthlyWithoutT.size());
                                                fragmentData.addAll(nationalCreatedMixListMonthlyWithoutT);
                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("recyclerData", fragmentData);
                                                HolidayRecyclerview holidayCalendarFragment = new HolidayRecyclerview();
                                                holidayCalendarFragment.setArguments(bundle);

                                                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                                                ft.replace(R.id.containerRecyclerView, holidayCalendarFragment).commit();

                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(getContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                                                Log.d(TAG, "onResponseElse11: " + response.message());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<NationalHolidayResp> call, Throwable t) {
                                            progressDialog.dismiss();

                                            Log.d(TAG, "onFaidfsdfsdfsdflure: " + t.getMessage() + t.getCause().toString());
                                            Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onResponseElsdfsdfsdfse11: " + response.message());
                                }

                            }

                            @Override
                            public void onFailure(Call<CreatedHolidayResp> call, Throwable t) {
                                progressDialog.dismiss();
                                Log.d(TAG, "onFaifsdfsfsdfsdfaflure11: " + t.getMessage());
                                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponseElse11: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<NationalHolidayResp> call, Throwable t) {
                    progressDialog.dismiss();

                    Log.d(TAG, "onFaidfsdfsdfsdflure: " + t.getMessage());
                    Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();
        }
    }


    private void monthSetByDefault() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DATE);
        month += 1;

        int year = cal.get(Calendar.YEAR);

        apiCallNationalAndCreatedHoliday(year, month);


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


    private void initializeCalendar(List<NationalCreatedMix> allHolidays) {
        progressDialog.show();

        CompactCalendarView compactCalendarView = getActivity().findViewById(R.id.compactcalendar_viewForHolidays);
        compactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        int count = 0;
        long milliTime;

        for (NationalCreatedMix singleUnit : allHolidays) {
            Log.d("dsfsdfsdf", singleUnit.getDate());
            Event e;
            if (singleUnit.getDate().contains("T")) {
                dateInParts = singleUnit.getDate().split("T")[0].split("-");
            } else {
                dateInParts = singleUnit.getDate().split("-");
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateInParts[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateInParts[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateInParts[2]));
            milliTime = calendar.getTimeInMillis();
            if (count % 3 == 0) {
                e = new Event(getResources().getColor(R.color.mainColor), milliTime, singleUnit.getDesc());
            } else if (count % 5 == 0) {
                e = new Event(getResources().getColor(R.color.yellow), milliTime, singleUnit.getDesc());
            } else if (count % 2 == 0) {
                e = new Event(getResources().getColor(R.color.pink), milliTime, singleUnit.getDesc());
            } else {
                e = new Event(getResources().getColor(R.color.green), milliTime, singleUnit.getDesc());
            }
            count++;
            binding.compactcalendarViewForHolidays.addEvent(e);
        }
        progressDialog.dismiss();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}