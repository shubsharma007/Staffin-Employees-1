package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMonthlyAttendance {
    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getPresentByWorking() {
        return presentByWorking;
    }

    public void setPresentByWorking(String presentByWorking) {
        this.presentByWorking = presentByWorking;
    }

    public String getAttendancePerReport() {
        return attendancePerReport;
    }

    public void setAttendancePerReport(String attendancePerReport) {
        this.attendancePerReport = attendancePerReport;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Integer getPresentDay() {
        return presentDay;
    }

    public void setPresentDay(Integer presentDay) {
        this.presentDay = presentDay;
    }

    public Integer getHalfday() {
        return halfday;
    }

    public void setHalfday(Integer halfday) {
        this.halfday = halfday;
    }

    public Integer getLateComing() {
        return lateComing;
    }

    public void setLateComing(Integer lateComing) {
        this.lateComing = lateComing;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getPaidLeaveCount() {
        return paidLeaveCount;
    }

    public void setPaidLeaveCount(Integer paidLeaveCount) {
        this.paidLeaveCount = paidLeaveCount;
    }

    public Integer getHolidaycount() {
        return holidaycount;
    }

    public void setHolidaycount(Integer holidaycount) {
        this.holidaycount = holidaycount;
    }

    public List<PaidLeaveDate> getPaidLeaveDate() {
        return paidLeaveDate;
    }

    public void setPaidLeaveDate(List<PaidLeaveDate> paidLeaveDate) {
        this.paidLeaveDate = paidLeaveDate;
    }

    public List<HolidayDate> getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(List<HolidayDate> holidayDate) {
        this.holidayDate = holidayDate;
    }

    public List<AbsentDate> getAbsentDate() {
        return absentDate;
    }

    public void setAbsentDate(List<AbsentDate> absentDate) {
        this.absentDate = absentDate;
    }

    public List<PresentDate> getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(List<PresentDate> presentDate) {
        this.presentDate = presentDate;
    }

    public List<LateComingDate> getLateComingDate() {
        return lateComingDate;
    }

    public void setLateComingDate(List<LateComingDate> lateComingDate) {
        this.lateComingDate = lateComingDate;
    }

    public List<HalfDayDate> getHalfdayDate() {
        return halfdayDate;
    }

    public void setHalfdayDate(List<HalfDayDate> halfdayDate) {
        this.halfdayDate = halfdayDate;
    }

    @SerializedName("presentByWorking")
    @Expose
    private String presentByWorking;
    @SerializedName("attendancePerReport")
    @Expose
    private String attendancePerReport;
    @SerializedName("Total_Working_Days")
    @Expose
    private Integer totalWorkingDays;
    @SerializedName("PresentDay")
    @Expose
    private Integer presentDay;
    @SerializedName("halfday")
    @Expose
    private Integer halfday;
    @SerializedName("late_coming")
    @Expose
    private Integer lateComing;
    @SerializedName("absent")
    @Expose
    private Integer absent;
    @SerializedName("paid_leave_count")
    @Expose
    private Integer paidLeaveCount;
    @SerializedName("holidaycount")
    @Expose
    private Integer holidaycount;
    @SerializedName("paid_leave_date")
    @Expose
    private List<PaidLeaveDate> paidLeaveDate;
    @SerializedName("holiday_date")
    @Expose
    private List<HolidayDate> holidayDate;
    @SerializedName("absent_date")
    @Expose
    private List<AbsentDate> absentDate;
    @SerializedName("present_date")
    @Expose
    private List<PresentDate> presentDate;
    @SerializedName("late_coming_date")
    @Expose
    private List<LateComingDate> lateComingDate;
    @SerializedName("halfday_date")
    @Expose
    private List<HalfDayDate> halfdayDate;

    public class PresentDate {
        @SerializedName("date")
        @Expose
        private String date;
        @Expose
        private String clock_in;
        @Expose
        private String clock_out;

        public String getClock_in() {
            return clock_in;
        }

        public void setClock_in(String clock_in) {
            this.clock_in = clock_in;
        }

        public String getClock_out() {
            return clock_out;
        }

        public void setClock_out(String clock_out) {
            this.clock_out = clock_out;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }


    }

    public class AbsentDate {
        @SerializedName("date")
        @Expose
        private String date;

        public String getDate() {
            return date;
        }


        public void setDate(String date) {
            this.date = date;
        }
    }

    public class PaidLeaveDate {
        @SerializedName("date")
        @Expose
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public class HolidayDate {
        @SerializedName("date")
        @Expose
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public class LateComingDate {
        @SerializedName("date")
        @Expose
        private String date;
        private String clock_in;
        private String clock_out;

        public String getClock_in() {
            return clock_in;
        }

        public void setClock_in(String clock_in) {
            this.clock_in = clock_in;
        }

        public String getClock_out() {
            return clock_out;
        }

        public void setClock_out(String clock_out) {
            this.clock_out = clock_out;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public class HalfDayDate {
        @SerializedName("date")
        @Expose
        private String date;
        private String clock_in;
        private String clock_out;

        public String getClock_in() {
            return clock_in;
        }

        public void setClock_in(String clock_in) {
            this.clock_in = clock_in;
        }

        public String getClock_out() {
            return clock_out;
        }

        public void setClock_out(String clock_out) {
            this.clock_out = clock_out;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
