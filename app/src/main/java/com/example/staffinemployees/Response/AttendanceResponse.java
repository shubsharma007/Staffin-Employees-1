package com.example.staffinemployees.Response;

public class AttendanceResponse {

    private String day;
    private String date;
    private String status;
    private String inTime;
    private String outTime;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

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

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public AttendanceResponse(String day, String date, String status, String inTime, String outTime) {
        this.day = day;
        this.date = date;
        this.status = status;
        this.inTime = inTime;
        this.outTime = outTime;
    }
}
