package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("tax_payer_id")
    @Expose
    private Object taxPayerId;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("bin")
    @Expose
    private Object bin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Object getTaxPayerId() {
        return taxPayerId;
    }

    public void setTaxPayerId(Object taxPayerId) {
        this.taxPayerId = taxPayerId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Object getBin() {
        return bin;
    }

    public void setBin(Object bin) {
        this.bin = bin;
    }
}
