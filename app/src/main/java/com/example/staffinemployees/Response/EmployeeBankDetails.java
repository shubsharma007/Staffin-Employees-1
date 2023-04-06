package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeBankDetails {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bank_details")
    @Expose
    private List<BankDetail> bankDetails;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BankDetail> getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(List<BankDetail> bankDetails) {
        this.bankDetails = bankDetails;
    }

}
