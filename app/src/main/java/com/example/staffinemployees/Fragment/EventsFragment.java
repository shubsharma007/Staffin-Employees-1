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

import com.example.staffinemployees.Adapters.MonthAdapter;
import com.example.staffinemployees.CreateEventActivity;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.EventsByYearResponse;
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
    EventsByYearResponse.EventDetails eventDetails;
    ApiInterface apiInterface;
    ProgressDialog progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Please Wait....");
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (isNetworkAvailable()) {
            progress.show();
            Call<EventsByYearResponse> callGetEventsByYear = apiInterface.getEventsByYear(2023);
            callGetEventsByYear.enqueue(new Callback<EventsByYearResponse>() {
                @Override
                public void onResponse(Call<EventsByYearResponse> call, Response<EventsByYearResponse> response) {
                    if (response.isSuccessful()) {
                        progress.dismiss();
                        eventDetails = response.body().getEventDetails();
                        adapter = new MonthAdapter(getActivity(), eventDetails);
                        binding.EventMonthRv.setAdapter(adapter);

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
}