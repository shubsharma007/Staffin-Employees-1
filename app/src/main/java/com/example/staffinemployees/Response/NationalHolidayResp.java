package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NationalHolidayResp {
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {

        @SerializedName("holidays")
        @Expose
        private List<Holiday> holidays;

        public List<Holiday> getHolidays() {
            return holidays;
        }
        public void setHolidays(List<Holiday> holidays) {
            this.holidays = holidays;
        }

        public class Holiday {

            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("description")
            @Expose
            private String description;

            @SerializedName("date")
            @Expose
            private Date date;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }

            public class Date {

                @SerializedName("iso")
                @Expose
                private String iso;


                public String getIso() {
                    return iso;
                }

                public void setIso(String iso) {
                    this.iso = iso;
                }
            }
        }
    }
}
