package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("employeeID")
    @Expose
    private String employeeID;
    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("department_id")
    @Expose
    private String departmentId;
    @SerializedName("designation")
    @Expose
    private Integer designation;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("local_address")
    @Expose
    private String localAddress;
    @SerializedName("permanent_address")
    @Expose
    private String permanentAddress;
    @SerializedName("annual_leave")
    @Expose
    private Integer annualLeave;
    @SerializedName("medical_leave")
    @Expose
    private String medicalLeave;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("last_login")
    @Expose
    private Object lastLogin;
    @SerializedName("remember_token")
    @Expose
    private Object rememberToken;
    @SerializedName("exit_date")
    @Expose
    private Object exitDate;
    @SerializedName("first_over_time_start")
    @Expose
    private Object firstOverTimeStart;
    @SerializedName("first_over_time_end")
    @Expose
    private Object firstOverTimeEnd;
    @SerializedName("first_over_time_amount")
    @Expose
    private Object firstOverTimeAmount;
    @SerializedName("second_over_time_start")
    @Expose
    private Object secondOverTimeStart;
    @SerializedName("second_over_time_end")
    @Expose
    private Object secondOverTimeEnd;
    @SerializedName("second_over_time_amount")
    @Expose
    private Object secondOverTimeAmount;
    @SerializedName("reset_code")
    @Expose
    private Object resetCode;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("work_duration")
    @Expose
    private String workDuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDesignation() {
        return designation;
    }

    public void setDesignation(Integer designation) {
        this.designation = designation;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
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

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(Object rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Object getExitDate() {
        return exitDate;
    }

    public void setExitDate(Object exitDate) {
        this.exitDate = exitDate;
    }

    public Object getFirstOverTimeStart() {
        return firstOverTimeStart;
    }

    public void setFirstOverTimeStart(Object firstOverTimeStart) {
        this.firstOverTimeStart = firstOverTimeStart;
    }

    public Object getFirstOverTimeEnd() {
        return firstOverTimeEnd;
    }

    public void setFirstOverTimeEnd(Object firstOverTimeEnd) {
        this.firstOverTimeEnd = firstOverTimeEnd;
    }

    public Object getFirstOverTimeAmount() {
        return firstOverTimeAmount;
    }

    public void setFirstOverTimeAmount(Object firstOverTimeAmount) {
        this.firstOverTimeAmount = firstOverTimeAmount;
    }

    public Object getSecondOverTimeStart() {
        return secondOverTimeStart;
    }

    public void setSecondOverTimeStart(Object secondOverTimeStart) {
        this.secondOverTimeStart = secondOverTimeStart;
    }

    public Object getSecondOverTimeEnd() {
        return secondOverTimeEnd;
    }

    public void setSecondOverTimeEnd(Object secondOverTimeEnd) {
        this.secondOverTimeEnd = secondOverTimeEnd;
    }

    public Object getSecondOverTimeAmount() {
        return secondOverTimeAmount;
    }

    public void setSecondOverTimeAmount(Object secondOverTimeAmount) {
        this.secondOverTimeAmount = secondOverTimeAmount;
    }

    public Object getResetCode() {
        return resetCode;
    }

    public void setResetCode(Object resetCode) {
        this.resetCode = resetCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(String workDuration) {
        this.workDuration = workDuration;
    }

}
