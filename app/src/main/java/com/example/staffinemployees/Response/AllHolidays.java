package com.example.staffinemployees.Response;

public class AllHolidays {
    private int id;
    private int company_id;
    private String date;
    private String occassion;
    private String holiday_description;
    private String holiday_day;

    public String getHoliday_description() {
        return holiday_description;
    }

    public void setHoliday_description(String holiday_description) {
        this.holiday_description = holiday_description;
    }

    public String getHoliday_day() {
        return holiday_day;
    }

    public void setHoliday_day(String holiday_day) {
        this.holiday_day = holiday_day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOccassion() {
        return occassion;
    }

    public void setOccassion(String occassion) {
        this.occassion = occassion;
    }

}
