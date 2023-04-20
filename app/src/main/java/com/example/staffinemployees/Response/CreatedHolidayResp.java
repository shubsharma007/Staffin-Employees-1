package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreatedHolidayResp {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("holiday_list")
    @Expose
    private List<Holiday> holidayList;

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

    public List<Holiday> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    public class Holiday {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("occassion")
        @Expose
        private String occassion;
        @SerializedName("holiday_description")
        @Expose
        private String holidayDescription;
        @SerializedName("holiday_day")
        @Expose
        private Object holidayDay;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
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

        public String getHolidayDescription() {
            return holidayDescription;
        }

        public void setHolidayDescription(String holidayDescription) {
            this.holidayDescription = holidayDescription;
        }

        public Object getHolidayDay() {
            return holidayDay;
        }

        public void setHolidayDay(Object holidayDay) {
            this.holidayDay = holidayDay;
        }

    }
}
