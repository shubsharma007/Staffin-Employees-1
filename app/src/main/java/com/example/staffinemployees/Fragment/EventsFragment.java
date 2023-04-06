package com.example.staffinemployees.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffinemployees.Adapters.MonthAdapter;
import com.example.staffinemployees.CreateEventActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.MembersOfEvent;
import com.example.staffinemployees.Response.MyMonth;
import com.example.staffinemployees.databinding.FragmentEventsBinding;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {
    FragmentEventsBinding binding;
    List<MyMonth> monthsList;
    List<MembersOfEvent> membersOnly;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);

        monthsList = new ArrayList<>();
        membersOnly = new ArrayList<>();
        membersOnly.add(new MembersOfEvent(1, "fjksdnf"));
        membersOnly.add(new MembersOfEvent(2, "fjksdnf"));
        monthsList.add(new MyMonth(1, "yogesh birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
        monthsList.add(new MyMonth(2, "shubham birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));
        monthsList.add(new MyMonth(3, "sunil birthday", "bfjisnsdjkf", "shajapur", "at hanuman mandir shajapur", "08-08-1999", membersOnly));



        binding.EventMonthRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.EventMonthRv.setAdapter(new MonthAdapter(monthsList,getContext()));

        binding.btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getActivity(), CreateEventActivity.class));
            }
        });

        binding.searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return binding.getRoot();
    }

}