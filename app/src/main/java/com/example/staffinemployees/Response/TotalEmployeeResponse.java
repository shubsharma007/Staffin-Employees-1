package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TotalEmployeeResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("employee_result")
    @Expose
    private List<EmployeeResult> employeeResult;




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

    public List<EmployeeResult> getEmployeeResult() {
        return employeeResult;
    }

    public void setEmployeeResult(List<EmployeeResult> employeeResult) {
        this.employeeResult = employeeResult;
    }
}
