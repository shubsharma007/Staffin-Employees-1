package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaySlipResponse {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("payslip_details")
        @Expose
        private List<PayslipDetail> payslipDetails;

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

        public List<PayslipDetail> getPayslipDetails() {
            return payslipDetails;
        }

        public void setPayslipDetails(List<PayslipDetail> payslipDetails) {
            this.payslipDetails = payslipDetails;
        }


    }
