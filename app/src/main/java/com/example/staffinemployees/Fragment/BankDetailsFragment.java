package com.example.staffinemployees.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.PersonalDetailsActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.BankDetail;
import com.example.staffinemployees.Response.EmployeeBankDetails;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentBankDetailsBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BankDetailsFragment extends Fragment {
    FragmentBankDetailsBinding binding;
    ApiInterface apiInterface;
    String Id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false);


        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Id = sharedPreferences.getAll().get("Id").toString();
        Log.i("Id AArahi AHI", Id);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Call<EmployeeBankDetails> employeeBankDetailsCall = apiInterface.getEmployeeBankDetails(Integer.parseInt(Id));
        employeeBankDetailsCall.enqueue(new Callback<EmployeeBankDetails>() {
            @Override
            public void onResponse(Call<EmployeeBankDetails> call, Response<EmployeeBankDetails> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    try {
                        List<BankDetail> user = response.body().getBankDetails();
                        BankDetail singleUser = user.get(0);

                        binding.holderEt.setText(singleUser.getAccountName());
                        Log.e("Account Name Aya", singleUser.getAccountName());
                        binding.accNoEt.setText(singleUser.getAccountNumber());
                        Log.e("Account Number Aya", singleUser.getAccountNumber());
                        binding.ifscEt.setText(singleUser.getBranch());
                        Log.e("branch Name Aya", singleUser.getBranch());
                        binding.bankEt.setText(singleUser.getBank());
                        Log.e("Bank Name Aya", singleUser.getBank());
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeeBankDetails> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

        return binding.getRoot();
    }
}