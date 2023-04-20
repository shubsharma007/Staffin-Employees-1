package com.example.staffinemployees.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.PaySlipResponse;
import com.example.staffinemployees.Response.PayslipDetail;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentPayrollBinding;
import com.example.staffinemployees.databinding.FragmentPayslipBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Payslip extends Fragment {
    FragmentPayslipBinding binding;
    ApiInterface apiInterface;
    String Id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPayslipBinding.inflate(inflater, container, false);
        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Id = sharedPreferences.getAll().get("Id").toString();
        Log.i("Id AArahi AHI", Id);

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        getApi();

        return binding.getRoot();
    }

    private void getApi() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Call<PaySlipResponse> paySlipResponseCall = apiInterface.getPaySlip(Integer.parseInt(Id));
        paySlipResponseCall.enqueue(new Callback<PaySlipResponse>() {
            @Override
            public void onResponse(Call<PaySlipResponse> call, Response<PaySlipResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getPayslipDetails().size() == 0) {

                        binding.notFoundLayout.setVisibility(View.VISIBLE);
                        binding.nestedScrollFirst.setVisibility(View.GONE);

                    } else {
                        binding.nestedScrollFirst.setVisibility(View.VISIBLE);
                        binding.notFoundLayout.setVisibility(View.GONE);
                        PayslipDetail singleUnit = response.body().getPayslipDetails().get(0);
                        binding.indicator.setText(singleUnit.getStatus());
                        binding.basicAmount.setText(singleUnit.getBasic());
                        binding.hourlyAmount.setText(singleUnit.getOvertimeHours());
                        binding.expenseAmount.setText(singleUnit.getExpense());
                        binding.bounceAmount.setText(singleUnit.getTotalAllowance());
                        binding.deductionAmount.setText(singleUnit.getDeductions());
                        binding.netAmount.setText(singleUnit.getNetSalary());
                        binding.empId.setText("Emp. ID - " + singleUnit.getEmployeeId());
                    }


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                    Log.e("Try Again Karo", response.message());
                }
            }

            @Override
            public void onFailure(Call<PaySlipResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                Log.e("Api not Working", t.getMessage());


            }
        });

    }
}