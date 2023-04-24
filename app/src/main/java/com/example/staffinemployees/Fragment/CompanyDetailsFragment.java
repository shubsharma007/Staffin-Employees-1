package com.example.staffinemployees.Fragment;

import static com.example.staffinemployees.Fragment.MainFragment.hour;
import static com.example.staffinemployees.Fragment.MainFragment.minute;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.PersonalDetailsActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.CompanyDetails;
import com.example.staffinemployees.Response.CompanyResponseById;
import com.example.staffinemployees.Response.EmployeeProfileResponse;
import com.example.staffinemployees.Response.EmployeeResult;
import com.example.staffinemployees.Response.OverTimeResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentCompanyDetailsBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.staffinemployees.databinding.FragmentCompanyDetailsBinding;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyDetailsFragment extends Fragment {
    FragmentCompanyDetailsBinding binding;
    Dialog adDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String id;
    ApiInterface apiInterface;
    List<String> time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompanyDetailsBinding.inflate(inflater, container, false);
        adDialog = new Dialog(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        id = sharedPreferences.getAll().get("Id").toString();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        time = new ArrayList<>();
        if (isNetworkAvailable()) {
            getUserApi();
            ClickListener();
        } else {
            Toast.makeText(getActivity(), "Please Check Your Network...", Toast.LENGTH_SHORT).show();
        }
        binding.employeeIdEt.setKeyListener(null);
        binding.annualLeaveEt.setKeyListener(null);
        binding.basicEt.setKeyListener(null);
        binding.departmentEt.setKeyListener(null);
        binding.designationEt.setKeyListener(null);
        binding.jDateEt.setKeyListener(null);
        binding.rDateEt.setKeyListener(null);
        binding.hourlyEt.setKeyListener(null);
        binding.medicalLeaveEt.setKeyListener(null);

        return binding.getRoot();
    }

    private void ClickListener() {
        binding.addOvertimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
        binding.btnNext.setOnClickListener(v -> {


//            if (binding.employeeIdEt.getText().toString().isEmpty()) {
//                binding.employeeIdEt.setError("Enter Id");
//                binding.employeeIdEt.requestFocus();
//            }
////            else if (binding.departmentEt.getSelectedItem() != "Please select Department") {
////                Toast.makeText(this, "Please select Department", Toast.LENGTH_SHORT).show();
////            } else if (binding.designationEt.getSelectedItem() != "Please select Designation") {
////                Toast.makeText(this, "Please select Department", Toast.LENGTH_SHORT).show();
////            }
//            else
            if (binding.annualLeaveEt.getText().toString().isEmpty()) {
                binding.annualLeaveEt.setError("Enter Annual Leaves");
                binding.annualLeaveEt.requestFocus();
            } else if (binding.medicalLeaveEt.getText().toString().isEmpty()) {
                binding.medicalLeaveEt.setError("Enter Medical Leaves");
                binding.medicalLeaveEt.requestFocus();
            } else if (binding.jDateEt.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Joining Date", Toast.LENGTH_SHORT).show();
            } else if (binding.rDateEt.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Relieving Date", Toast.LENGTH_SHORT).show();
            } else if (!binding.rbActive.isChecked() && !binding.rbInactive.isChecked()) {
                Toast.makeText(getActivity(), "Please Select Status", Toast.LENGTH_SHORT).show();
            } else if (binding.basicEt.getText().toString().isEmpty()) {
                binding.basicEt.setError("Enter Basic Salary");
                binding.basicEt.requestFocus();
            } else if (binding.hourlyEt.getText().toString().isEmpty()) {
                binding.hourlyEt.setError("Enter Hourly Rate");
                binding.hourlyEt.requestFocus();
            } else {


                startActivity(new Intent(getActivity(), MainActivity.class));
                requireActivity().finish();
            }

        });
        binding.jDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.jDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        binding.rDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        (view, year1, monthOfYear, dayOfMonth) -> {
                            //
                            binding.rDateEt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

    }

    private void getUserApi() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<CompanyResponseById> companyResponseByIdCall = apiInterface.getCompanyDetailsById(Integer.parseInt(id));
        companyResponseByIdCall.enqueue(new Callback<CompanyResponseById>() {
            @Override
            public void onResponse(Call<CompanyResponseById> call, Response<CompanyResponseById> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    CompanyDetails singleUser = response.body().getCompanyDetails();
                    binding.employeeIdEt.setText(singleUser.getEmployeeID());
                    binding.departmentEt.setText(singleUser.getDepartment().get(0).getName());
                    binding.designationEt.setText(singleUser.getDesignation().get(0).getDesignation());
                    binding.annualLeaveEt.setText(singleUser.getAnnualLeave().toString());
                    binding.medicalLeaveEt.setText(singleUser.getMedicalLeave());

                    String JDATE = singleUser.getJoiningDate();
                    JDATE = JDATE.split("T")[0];
                    binding.jDateEt.setText(JDATE);
                    if (singleUser.getExitDate() == null) {
                        binding.rDateEt.setText("");
                    } else {
                        String RDATE = singleUser.getExitDate();
                        RDATE = RDATE.split("T")[0];
                        binding.rDateEt.setText(RDATE);
                    }
                    if (singleUser.getStatus().equalsIgnoreCase("active")) {
                        binding.rbActive.setChecked(true);
                    } else {
                        binding.rbInactive.setChecked(true);
                    }
                    binding.basicEt.setText(String.valueOf(singleUser.getBasic().get(0).getSalary()));
                    binding.hourlyEt.setText(String.valueOf(singleUser.getHourlyRate().get(0).getSalary()));


                } else {
                    progressDialog.dismiss();
                    Log.d("dkfnsdf", response.message());
                    Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CompanyResponseById> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("dkfnsdf", t.getMessage());
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPopup() {
        adDialog.setContentView(R.layout.popup_overtime);
        adDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adDialog.show();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        TextView firstAmountEt = adDialog.findViewById(R.id.firstAmountEt);
        TextView secondAmountEt = adDialog.findViewById(R.id.secondAmountEt);
        TextView firstTv = adDialog.findViewById(R.id.firstTv);
        TextView secondTv = adDialog.findViewById(R.id.secondTv);
        TextView thirdTv = adDialog.findViewById(R.id.thirdTv);
        TextView fourthTv = adDialog.findViewById(R.id.fourthTv);

        Call<OverTimeResponse> overTimeResponseCall = apiInterface.getOverTime(Integer.parseInt(id));
        overTimeResponseCall.enqueue(new Callback<OverTimeResponse>() {
            @Override
            public void onResponse(Call<OverTimeResponse> call, Response<OverTimeResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    time = response.body().getOverTime();
//                    Log.e("sfdfdf", time.toString());
//                    Log.d("hahahaha", time.get(0));
//                    Log.d("hahahaha", time.get(1));
//                    Log.d("hahahaha", time.get(2));
//                    Log.d("hahahaha", time.get(3));
//                    Log.d("hahahaha", time.get(4));
//                    Log.d("hahahaha", time.get(5));
                    if (time.get(0) == null) {
                        firstTv.setText("no data");
                    } else {
                        firstTv.setText(time.get(0));

                    }
                    if (time.get(1) == null) {
                        secondTv.setText("no data");
                    } else {
                        secondTv.setText(time.get(1));

                    }
                    if (time.get(2) == null) {
                        firstAmountEt.setText("no data");
                    } else {
                        firstAmountEt.setText(time.get(2));
                    }
                    if (time.get(3) == null) {
                        thirdTv.setText("no data");
                    } else {
                        thirdTv.setText(time.get(3));

                    }
                    if (time.get(4) == null) {
                        fourthTv.setText("no data");
                    } else {
                        fourthTv.setText(time.get(4));

                    }
                    if (time.get(5) == null) {
                        secondAmountEt.setText("no data");
                    } else {
                        secondAmountEt.setText(time.get(5));
                    }

                } else {
                    progressDialog.dismiss();
                    Log.e("diufhyeiufhiu", response.message());
                    Toast.makeText(getActivity(), "On Response Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OverTimeResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("fdiufgidsu", t.getMessage());
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });

//        firstTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                // on below line we are getting our hour, minute.
//                hour = c.get(Calendar.HOUR_OF_DAY);
//                minute = c.get(Calendar.MINUTE);
//
//                // on below line we are initializing our Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
//                        (view, hourOfDay, minute1) -> firstTv.setText(hourOfDay + ":" + minute1), hour, minute, false);
//
//                timePickerDialog.show();
//
//            }
//        });
//        secondTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                hour = c.get(Calendar.HOUR_OF_DAY);
//                minute = c.get(Calendar.MINUTE);
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
//                        (view, hourOfDay, minute1) -> secondTv.setText(hourOfDay + ":" + minute1), hour, minute, false);
//
//                timePickerDialog.show();
//            }
//        });
//
//        thirdTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                hour = c.get(Calendar.HOUR_OF_DAY);
//                minute = c.get(Calendar.MINUTE);
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
//                        (view, hourOfDay, minute1) -> thirdTv.setText(hourOfDay + ":" + minute1), hour, minute, false);
//
//                timePickerDialog.show();
//            }
//        });
//        fourthTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                hour = c.get(Calendar.HOUR_OF_DAY);
//                minute = c.get(Calendar.MINUTE);
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
//                        (view, hourOfDay, minute1) -> fourthTv.setText(hourOfDay + ":" + minute1), hour, minute, false);
//
//                timePickerDialog.show();
//            }
//        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}