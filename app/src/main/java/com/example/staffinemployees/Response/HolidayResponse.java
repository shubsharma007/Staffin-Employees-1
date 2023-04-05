package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolidayResponse {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("message")
    @Expose
    private String message;

    private List<AllHolidays> holiday_list;

    public List<AllHolidays> getHoliday_list() {
        return holiday_list;
    }

    public void setHoliday_list(List<AllHolidays> holiday_list) {
        this.holiday_list = holiday_list;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
