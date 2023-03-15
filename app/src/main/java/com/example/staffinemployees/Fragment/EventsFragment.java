package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.Adapters.MonthAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentEventsBinding;

public class EventsFragment extends Fragment {
FragmentEventsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentEventsBinding.inflate(inflater,container,false);

        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.EventMonthRv.setAdapter(new MonthAdapter(getContext()));

        return binding.getRoot();
    }
}