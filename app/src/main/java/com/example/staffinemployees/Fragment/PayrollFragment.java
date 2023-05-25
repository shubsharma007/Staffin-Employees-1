package com.example.staffinemployees.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffinemployees.Adapters.AttendanceAdapter;
import com.example.staffinemployees.Adapters.PaySlipAdapter;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AllPayroll;
import com.example.staffinemployees.Response.AllPayslips;
import com.example.staffinemployees.Response.PayslipDetail;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentPayrollBinding;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayrollFragment extends Fragment {
    FragmentPayrollBinding binding;
    ApiInterface apiInterface;

    String Id;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPayrollBinding.inflate(inflater, container, false);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        Id = sharedPreferences.getAll().get("Id").toString();

        Date date = new Date();
        String year = (String) DateFormat.format("yyyy", date); // 2013

        Call<AllPayslips> getAllPayrollCall = apiInterface.getAllPayroll(Integer.parseInt(Id), Integer.parseInt(year));
        getAllPayrollCall.enqueue(new Callback<AllPayslips>() {
            @Override
            public void onResponse(Call<AllPayslips> call, Response<AllPayslips> response) {
                if (response.isSuccessful()) {
                    List<AllPayslips.PayslipDetail> payslipDetailList = response.body().getPayslipDetails();
                    if (payslipDetailList.size() > 0) {
                        binding.payrollRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.payrollRecyclerView.setAdapter(new PaySlipAdapter(payslipDetailList, getContext()));
                        binding.payrollRecyclerView.setVisibility(View.VISIBLE);
                        binding.noDataFound.setVisibility(View.GONE);
                    } else {
                        binding.payrollRecyclerView.setVisibility(View.GONE);
                        binding.noDataFound.setVisibility(View.VISIBLE);
                    }
                } else {
                    binding.payrollRecyclerView.setVisibility(View.GONE);
                    binding.noDataFound.setVisibility(View.VISIBLE);
//                    Toast.makeText(getContext(), "some error occured", Toast.LENGTH_SHORT).show();
                    Log.d("gsdfgsdfg", response.message());
                }
            }

            @Override
            public void onFailure(Call<AllPayslips> call, Throwable t) {
                binding.payrollRecyclerView.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.VISIBLE);
//                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                Log.d("gsdfgsdfg", t.getMessage());
            }
        });


        return binding.getRoot();
    }
}