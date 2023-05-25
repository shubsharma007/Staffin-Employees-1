package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPayroll {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_payslip_details")
    @Expose
    private List<AllPayslipDetail> allPayslipDetails;

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

    public List<AllPayslipDetail> getAllPayslipDetails() {
        return allPayslipDetails;
    }

    public void setAllPayslipDetails(List<AllPayslipDetail> allPayslipDetails) {
        this.allPayslipDetails = allPayslipDetails;
    }

    public class AllPayslipDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("employee_id")
        @Expose
        private List<EmployeeId> employeeId;
        @SerializedName("month")
        @Expose
        private String month;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("payment_mode")
        @Expose
        private String paymentMode;
        @SerializedName("basic")
        @Expose
        private String basic;
        @SerializedName("overtime_hours")
        @Expose
        private String overtimeHours;
        @SerializedName("overtime_pay")
        @Expose
        private String overtimePay;
        @SerializedName("allowances")
        @Expose
        private String allowances;
        @SerializedName("total_allowance")
        @Expose
        private String totalAllowance;
        @SerializedName("deductions")
        @Expose
        private String deductions;
        @SerializedName("total_deduction")
        @Expose
        private String totalDeduction;
        @SerializedName("additionals")
        @Expose
        private String additionals;
        @SerializedName("total_additional")
        @Expose
        private String totalAdditional;
        @SerializedName("net_salary")
        @Expose
        private String netSalary;
        @SerializedName("pay_date")
        @Expose
        private String payDate;
        @SerializedName("expense")
        @Expose
        private String expense;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("status")
        @Expose
        private String status;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<EmployeeId> getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(List<EmployeeId> employeeId) {
            this.employeeId = employeeId;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getBasic() {
            return basic;
        }

        public void setBasic(String basic) {
            this.basic = basic;
        }

        public String getOvertimeHours() {
            return overtimeHours;
        }

        public void setOvertimeHours(String overtimeHours) {
            this.overtimeHours = overtimeHours;
        }

        public String getOvertimePay() {
            return overtimePay;
        }

        public void setOvertimePay(String overtimePay) {
            this.overtimePay = overtimePay;
        }

        public String getAllowances() {
            return allowances;
        }

        public void setAllowances(String allowances) {
            this.allowances = allowances;
        }

        public String getTotalAllowance() {
            return totalAllowance;
        }

        public void setTotalAllowance(String totalAllowance) {
            this.totalAllowance = totalAllowance;
        }

        public String getDeductions() {
            return deductions;
        }

        public void setDeductions(String deductions) {
            this.deductions = deductions;
        }

        public String getTotalDeduction() {
            return totalDeduction;
        }

        public void setTotalDeduction(String totalDeduction) {
            this.totalDeduction = totalDeduction;
        }

        public String getAdditionals() {
            return additionals;
        }

        public void setAdditionals(String additionals) {
            this.additionals = additionals;
        }

        public String getTotalAdditional() {
            return totalAdditional;
        }

        public void setTotalAdditional(String totalAdditional) {
            this.totalAdditional = totalAdditional;
        }

        public String getNetSalary() {
            return netSalary;
        }

        public void setNetSalary(String netSalary) {
            this.netSalary = netSalary;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getExpense() {
            return expense;
        }

        public void setExpense(String expense) {
            this.expense = expense;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public class EmployeeId {

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
            private String exitDate;
            @SerializedName("first_over_time_start")
            @Expose
            private String firstOverTimeStart;
            @SerializedName("first_over_time_end")
            @Expose
            private String firstOverTimeEnd;
            @SerializedName("first_over_time_amount")
            @Expose
            private String firstOverTimeAmount;
            @SerializedName("second_over_time_start")
            @Expose
            private String secondOverTimeStart;
            @SerializedName("second_over_time_end")
            @Expose
            private String secondOverTimeEnd;
            @SerializedName("second_over_time_amount")
            @Expose
            private String secondOverTimeAmount;
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

            public String getExitDate() {
                return exitDate;
            }

            public void setExitDate(String exitDate) {
                this.exitDate = exitDate;
            }

            public String getFirstOverTimeStart() {
                return firstOverTimeStart;
            }

            public void setFirstOverTimeStart(String firstOverTimeStart) {
                this.firstOverTimeStart = firstOverTimeStart;
            }

            public String getFirstOverTimeEnd() {
                return firstOverTimeEnd;
            }

            public void setFirstOverTimeEnd(String firstOverTimeEnd) {
                this.firstOverTimeEnd = firstOverTimeEnd;
            }

            public String getFirstOverTimeAmount() {
                return firstOverTimeAmount;
            }

            public void setFirstOverTimeAmount(String firstOverTimeAmount) {
                this.firstOverTimeAmount = firstOverTimeAmount;
            }

            public String getSecondOverTimeStart() {
                return secondOverTimeStart;
            }

            public void setSecondOverTimeStart(String secondOverTimeStart) {
                this.secondOverTimeStart = secondOverTimeStart;
            }

            public String getSecondOverTimeEnd() {
                return secondOverTimeEnd;
            }

            public void setSecondOverTimeEnd(String secondOverTimeEnd) {
                this.secondOverTimeEnd = secondOverTimeEnd;
            }

            public String getSecondOverTimeAmount() {
                return secondOverTimeAmount;
            }

            public void setSecondOverTimeAmount(String secondOverTimeAmount) {
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

    }
}
