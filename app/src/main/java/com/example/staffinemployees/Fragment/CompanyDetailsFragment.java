package com.example.staffinemployees.Fragment;

import static com.example.staffinemployees.Fragment.MainFragment.hour;
import static com.example.staffinemployees.Fragment.MainFragment.minute;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.staffinemployees.R;
import com.example.staffinemployees.databinding.FragmentCompanyDetailsBinding;

import java.util.Calendar;


public class CompanyDetailsFragment extends Fragment {
    FragmentCompanyDetailsBinding binding;
    Dialog adDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompanyDetailsBinding.inflate(inflater, container, false);
        adDialog = new Dialog(getActivity());


        binding.addOvertimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });


        return binding.getRoot();
    }

    private void showPopup() {


        adDialog.setContentView(R.layout.popup_overtime);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.show();

        EditText firstAmountEt = adDialog.findViewById(R.id.firstAmountEt);
        EditText secondAmountEt = adDialog.findViewById(R.id.secondAmountEt);
        AppCompatButton submitBtn = adDialog.findViewById(R.id.submitBtn);
        TextView firstTv = adDialog.findViewById(R.id.firstTv);
        TextView secondTv = adDialog.findViewById(R.id.secondTv);
        TextView thirdTv = adDialog.findViewById(R.id.thirdTv);
        TextView fourthTv = adDialog.findViewById(R.id.fourthTv);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstTv.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Start Time For 1st Overtime", Toast.LENGTH_SHORT).show();
                } else if (secondTv.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter End Time For 1st Overtime", Toast.LENGTH_SHORT).show();
                } else if (thirdTv.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Start Time For 2nd Overtime", Toast.LENGTH_SHORT).show();
                } else if (fourthTv.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter End Time For 2nd Overtime", Toast.LENGTH_SHORT).show();
                } else if (firstAmountEt.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Amount For 1st Overtime", Toast.LENGTH_SHORT).show();
                } else if (secondAmountEt.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Amount For 2nd Overtime", Toast.LENGTH_SHORT).show();
                } else {

                    adDialog.dismiss();
                }


            }
        });


        firstTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        (view, hourOfDay, minute1) -> firstTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();

            }
        });
        secondTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        (view, hourOfDay, minute1) -> secondTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();
            }
        });

        thirdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        (view, hourOfDay, minute1) -> thirdTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();
            }
        });

        fourthTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        (view, hourOfDay, minute1) -> fourthTv.setText(hourOfDay + ":" + minute1), hour, minute, false);

                timePickerDialog.show();
            }
        });


    }

}