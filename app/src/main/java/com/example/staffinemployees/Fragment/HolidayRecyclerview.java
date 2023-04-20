package com.example.staffinemployees.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.Adapters.HolidayAdapter;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.NationalCreatedMix;
import com.example.staffinemployees.databinding.FragmentHolidayRecyclerviewBinding;

import java.util.List;

public class HolidayRecyclerview extends Fragment {
    FragmentHolidayRecyclerviewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.example.staffinemployees.databinding.FragmentHolidayRecyclerviewBinding.inflate(inflater, container, false);

        List<NationalCreatedMix> mix = (List<NationalCreatedMix>) this.getArguments().getSerializable("recyclerData");

        if (mix.size() == 0) {
            binding.holidayRecyclerView.setVisibility(View.GONE);
            binding.notFoundLayout.setVisibility(View.VISIBLE);
        } else {
            binding.holidayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.holidayRecyclerView.setAdapter(new HolidayAdapter(getActivity(), mix));

            binding.holidayRecyclerView.setVisibility(View.VISIBLE);
            binding.notFoundLayout.setVisibility(View.GONE);
        }

        return binding.getRoot();
    }
}