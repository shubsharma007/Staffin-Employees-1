package com.example.staffinemployees.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffinemployees.Adapters.HomeEventsAdapter;
import com.example.staffinemployees.Adapters.MonthAdapter;
import com.example.staffinemployees.CreateEventActivity;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.EventsMix;
import com.example.staffinemployees.Response.MembersOfEvent;
import com.example.staffinemployees.Response.MyMonth;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentEventsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {
    FragmentEventsBinding binding;
    //    List<MyMonth> monthsList;
    MonthAdapter adapter;
    //    List<MembersOfEvent> membersOnly;
//    EventsByYearResponse.EventDetails eventDetails;
    ApiInterface apiInterface;
    ProgressDialog progress;

    List<EventsMix> eventsMixList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);

        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Please Wait....");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventsMixList = new ArrayList<>();

        if (isNetworkAvailable()) {


            binding.searchbar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            progress.show();
            Call<EventsByYearResponse> callGetEventsByYear = apiInterface.getEventsByYear(2023);
            callGetEventsByYear.enqueue(new Callback<EventsByYearResponse>() {
                @Override
                public void onResponse(Call<EventsByYearResponse> call, Response<EventsByYearResponse> response) {
                    if (response.isSuccessful()) {
                        progress.dismiss();
                        if (response.body().getEventDetails().getJanuary().size() > 0) {
                            for (EventsByYearResponse.EventDetails.January x : response.body().getEventDetails().getJanuary()) {
                                eventsMixList.add(new EventsMix(x.getId(), x.getTitleName(), x.getImage(), x.getImg1(), x.getImg2(), x.getImg3(), x.getLocation(), x.getDescription(), x.getDate(), x.getAddMember(), 1));
                            }
                        }
                        if (response.body().getEventDetails().getFebruary().size() > 0) {
                            for (EventsByYearResponse.EventDetails.February x : response.body().getEventDetails().getFebruary()) {
                                eventsMixList.add(new EventsMix(x.getId(), x.getTitleName(), x.getImage(), x.getImg1(), x.getImg2(), x.getImg3(), x.getLocation(), x.getDescription(), x.getDate(), x.getAddMember(), 2));
                            }
                        }
                        if (response.body().getEventDetails().getMarch().size() > 0) {
                            for (EventsByYearResponse.EventDetails.March x : response.body().getEventDetails().getMarch()) {
                                eventsMixList.add(new EventsMix(x.getId(), x.getTitleName(), x.getImage(), x.getImg1(), x.getImg2(), x.getImg3(), x.getLocation(), x.getDescription(), x.getDate(), x.getAddMember(), 3));
                            }
                        }
                        if (response.body().getEventDetails().getApril().size() > 0) {
                            for (EventsByYearResponse.EventDetails.April f : response.body().getEventDetails().getApril()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 4));
                            }
                        }
                        if (response.body().getEventDetails().getMay().size() > 0) {
                            for (EventsByYearResponse.EventDetails.May j : response.body().getEventDetails().getMay()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 5));
                            }
                        }
                        if (response.body().getEventDetails().getJune().size() > 0) {
                            for (EventsByYearResponse.EventDetails.June f : response.body().getEventDetails().getJune()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 6));
                            }
                        }
                        if (response.body().getEventDetails().getJuly().size() > 0) {
                            for (EventsByYearResponse.EventDetails.July j : response.body().getEventDetails().getJuly()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 7));
                            }
                        }
                        if (response.body().getEventDetails().getAugust().size() > 0) {
                            for (EventsByYearResponse.EventDetails.August f : response.body().getEventDetails().getAugust()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 8));
                            }
                        }
                        if (response.body().getEventDetails().getSeptember().size() > 0) {
                            for (EventsByYearResponse.EventDetails.September j : response.body().getEventDetails().getSeptember()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 9));
                            }
                        }
                        if (response.body().getEventDetails().getOctober().size() > 0) {
                            for (EventsByYearResponse.EventDetails.October f : response.body().getEventDetails().getOctober()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 10));
                            }
                        }
                        if (response.body().getEventDetails().getNovember().size() > 0) {
                            for (EventsByYearResponse.EventDetails.November j : response.body().getEventDetails().getNovember()) {
                                eventsMixList.add(new EventsMix(j.getId(), j.getTitleName(), j.getImage(), j.getImg1(), j.getImg2(), j.getImg3(), j.getLocation(), j.getDescription(), j.getDate(), j.getAddMember(), 11));
                            }
                        }
                        if (response.body().getEventDetails().getDecember().size() > 0) {
                            for (EventsByYearResponse.EventDetails.December f : response.body().getEventDetails().getDecember()) {
                                eventsMixList.add(new EventsMix(f.getId(), f.getTitleName(), f.getImage(), f.getImg1(), f.getImg2(), f.getImg3(), f.getLocation(), f.getDescription(), f.getDate(), f.getAddMember(), 12));
                            }
                        }
                        for (EventsMix ex : eventsMixList) {
                            Log.d("EVENTMIX " + ex.getMonth(), ex.getDate());
                        }
                        if (eventsMixList.size() > 0) {
                            adapter = new MonthAdapter(getActivity(), eventsMixList);
                            binding.EventMonthRv.setAdapter(adapter);
                        }

//                        eventDetails = response.body().getEventDetails();
//                        adapter = new MonthAdapter(getActivity(), eventDetails);
//                        binding.EventMonthRv.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity(), "Find Some Error", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                        Log.d("jnknfd", response.message());
                    }
                }

                @Override
                public void onFailure(Call<EventsByYearResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failure,Try Again", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    Log.d("kdnf", t.getMessage());
                }
            });
        } else {
            Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();
        }

        binding.btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateEventActivity.class));
            }
        });

        return binding.getRoot();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    void filter(String text) {
        ArrayList<EventsMix> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (EventsMix item : eventsMixList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getTitleName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }
}