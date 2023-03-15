package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.Adapters.HolidayAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentUpcomingHolidaysBinding;


public class UpcomingHolidaysFragment extends Fragment {
FragmentUpcomingHolidaysBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentUpcomingHolidaysBinding.inflate(inflater,container,false);

        binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.holidayRecyclerView.setAdapter(new HolidayAdapter(getContext()));


        return binding.getRoot();
    }
}