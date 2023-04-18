package com.example.staffinemployees.Response;

public class PresentAbsentMix {

    private String date;
    private String status;
    private String time_in;
    private String time_out;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime_in() {
        return time_in;
    }

    public void setTime_in(String time_in) {
        this.time_in = time_in;
    }

    public String getTime_out() {
        return time_out;
    }

    public void setTime_out(String time_out) {
        this.time_out = time_out;
    }

    public PresentAbsentMix(String date, String status, String time_in, String time_out) {
        this.date = date;
        this.status = status;
        this.time_in = time_in;
        this.time_out = time_out;
    }
}
