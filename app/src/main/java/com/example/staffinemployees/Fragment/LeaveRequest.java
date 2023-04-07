package com.example.staffinemployees.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.LoginActivity;
import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentLeaveRequestBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeaveRequest extends Fragment {

    FragmentLeaveRequestBinding binding;
    List<String> leaves;
    static boolean fullDay = true;
    String leaveSelected = "";
    ApiInterface apiInterface;
    String Id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeaveRequestBinding.inflate(inflater, container, false);

        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Id = sharedPreferences.getAll().get("Id").toString();
        Log.i("Id AArahi AHI", Id);

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        leaves = new ArrayList<>();

        //        leaves.add("please select leave type");
        leaves.add("Paid Leave");
        leaves.add("Unpaid Leave");
        leaves.add("Sick Leave");


        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, leaves);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.leaveTypeSpinner.setAdapter(aa);

        binding.leaveTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                issueSelected = strArray[position];
                leaveSelected = leaves.get(position);
//                Toast.makeText(getContext(), leaves.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.descriptionCounterTv.setText(String.valueOf(s.length()) + "/" + "150");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        };
        binding.descriptionEt.addTextChangedListener(mTextEditorWatcher);

        binding.fromDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.fromDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        binding.toDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.toDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        binding.fullDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fullDay) {
                    fullDay = true;
                    binding.fullDayBtn.setBackgroundResource(R.drawable.bg_left_blue);
                    binding.fullDayBtn.setTextColor(Color.WHITE);
                    binding.halfDayBtn.setBackgroundResource(R.drawable.bg_right_white);
                    binding.halfDayBtn.setTextColor(Color.BLACK);
                }
            }
        });

        binding.halfDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullDay) {
                    fullDay = false;
                    binding.halfDayBtn.setBackgroundResource(R.drawable.bg_right_blue);
                    binding.halfDayBtn.setTextColor(Color.WHITE);
                    binding.fullDayBtn.setBackgroundResource(R.drawable.bg_left_white);
                    binding.fullDayBtn.setTextColor(Color.BLACK);
                }
            }
        });

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leaveSelected.equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Select leave type", Toast.LENGTH_SHORT).show();
                } else if (binding.fromDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Enter date", Toast.LENGTH_SHORT).show();
                } else if (binding.descriptionEt.getText().toString().isEmpty()) {
                    binding.descriptionEt.setError("enter description");
                    binding.descriptionEt.requestFocus();
                } else {
                    String jdate = binding.fromDateEt.getText().toString();
                    String day;
                    if (!fullDay) {
                        day = "Half Day";
                    } else {
                        day = "Full Day";
                    }
                    String leaveType = binding.leaveTypeSpinner.getSelectedItem().toString();
                    String reason = binding.descriptionEt.getText().toString();

                    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();


                    Call<LoginResponse> leaveRequestCall = apiInterface.postLeaveRequest(jdate, day, leaveType, reason, Integer.parseInt(Id));
                    leaveRequestCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "Request submitted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();

                        }
                    });


                }

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fullDay = true;
    }

}