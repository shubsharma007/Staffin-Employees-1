package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.Payslip;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AllPayslips;

import java.util.List;

public class PaySlipAdapter extends RecyclerView.Adapter<PaySlipAdapter.PaySlipViewHolder> {
    List<AllPayslips.PayslipDetail> payslipDetailList;
    Context context;

    public PaySlipAdapter(List<AllPayslips.PayslipDetail> payslipDetailList, Context context) {
        this.payslipDetailList = payslipDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public PaySlipAdapter.PaySlipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_payroll_layout, parent, false);
        return new PaySlipViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PaySlipAdapter.PaySlipViewHolder holder, int position) {
        AllPayslips.PayslipDetail singleUnit = payslipDetailList.get(position);
        holder.payslipsName.setText(singleUnit.getEmployeeId().get(0).getFullName());
        holder.payslipsAmount.setText(singleUnit.getNetSalary() + " RM");
        holder.payslipsDate.setText(singleUnit.getMonth() + " Month , " + singleUnit.getYear() + " Year");

        holder.payslipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Payslip.class);

                Bundle bundle = new Bundle();
                bundle.putString("dp", singleUnit.getEmployeeId().get(0).getProfileImageUrl());
                bundle.putString("name", "Employee - " + singleUnit.getEmployeeId().get(0).getFullName());
                bundle.putString("employeeId", "Employee Id -  " + singleUnit.getEmployeeId().get(0).getEmployeeID());
                bundle.putString("status", singleUnit.getStatus());
                bundle.putString("monthYear", singleUnit.getMonth() + " , " + singleUnit.getYear());
                bundle.putString("basic", singleUnit.getBasic());
                bundle.putString("expenses", singleUnit.getExpense());
                bundle.putString("overtime", singleUnit.getOvertimePay());
                bundle.putString("bonus", (String) singleUnit.getBonus());
                bundle.putString("deductions", singleUnit.getDeductions());
                bundle.putString("net", singleUnit.getNetSalary());
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return payslipDetailList.size();
    }

    public static class PaySlipViewHolder extends RecyclerView.ViewHolder {
        TextView payslipsName, payslipsAmount, payslipsDate;
        ConstraintLayout payslipCard;

        public PaySlipViewHolder(@NonNull View itemView) {
            super(itemView);
            payslipCard = itemView.findViewById(R.id.payslipCard);
            payslipsAmount = itemView.findViewById(R.id.payslipsAmount);
            payslipsDate = itemView.findViewById(R.id.payslipsDate);
            payslipsName = itemView.findViewById(R.id.payslipsName);

        }
    }
}
