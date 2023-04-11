package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyDetails {

    @SerializedName("department")
    @Expose
    private List<Department> department;
    @SerializedName("designation")
    @Expose
    private List<Designation> designation;
    @SerializedName("annual_leave")
    @Expose
    private Integer annualLeave;
    @SerializedName("medical_leave")
    @Expose
    private String medicalLeave;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("employeeID")
    @Expose
    private String employeeID;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("exit_date")
    @Expose
    private String exitDate;
    @SerializedName("basic")
    @Expose
    private List<Basic> basic;
    @SerializedName("hourly_rate")
    @Expose
    private List<HourlyRate> hourlyRate;

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    public List<Designation> getDesignation() {
        return designation;
    }

    public void setDesignation(List<Designation> designation) {
        this.designation = designation;
    }

    public Integer getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Integer annualLeave) {
        this.annualLeave = annualLeave;
    }

    public String getMedicalLeave() {
        return medicalLeave;
    }

    public void setMedicalLeave(String medicalLeave) {
        this.medicalLeave = medicalLeave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public List<Basic> getBasic() {
        return basic;
    }

    public void setBasic(List<Basic> basic) {
        this.basic = basic;
    }

    public List<HourlyRate> getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(List<HourlyRate> hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
