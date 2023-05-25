package com.example.staffinemployees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.databinding.ActivityPayslipBinding;

public class Payslip extends AppCompatActivity {
    ActivityPayslipBinding binding;
    String dp, name, employeeId, status, monthYear, basic, expenses, overtime, bonus, deductions, net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayslipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dp = getIntent().getExtras().getString("dp");
        name = getIntent().getExtras().getString("name");
        employeeId = getIntent().getExtras().getString("employeeId");
        status = getIntent().getExtras().getString("status");
        monthYear = getIntent().getExtras().getString("monthYear");
        basic = getIntent().getExtras().getString("basic");
        expenses = getIntent().getExtras().getString("expenses");
        overtime = getIntent().getExtras().getString("overtime");
        bonus = getIntent().getExtras().getString("bonus");
        deductions = getIntent().getExtras().getString("deductions");
        net = getIntent().getExtras().getString("net");

        Glide.with(Payslip.this).load(dp).into(binding.dp);
        binding.nameTv.setText(name);
        binding.empId.setText(employeeId);
        binding.indicator.setText(status);
        binding.txt2.setText(monthYear);
        binding.basicAmount.setText(basic+ " Rm");
        binding.hourlyAmount.setText(overtime+ " Rm");
        binding.expenseAmount.setText(expenses+ " Rm");
        binding.bounceAmount.setText(bonus+ " Rm");
        binding.deductionAmount.setText(deductions+ " Rm");
        binding.netAmount.setText(net+ " Rm");

        binding.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Payslip.this, "Downaloading payslip", Toast.LENGTH_SHORT).show();
            }
        });

    }
}