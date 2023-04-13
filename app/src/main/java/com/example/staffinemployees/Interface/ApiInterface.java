package com.example.staffinemployees.Interface;

import com.example.staffinemployees.Response.CompanyResponseById;
import com.example.staffinemployees.Response.EmployeeBankDetails;
import com.example.staffinemployees.Response.EmployeeProfileResponse;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.HolidayResponse;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Response.OverTimeResponse;
import com.example.staffinemployees.Response.Punch;
import com.example.staffinemployees.Response.TotalEmployeeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("get-event-details/{year}")
    Call<EventsByYearResponse> getEventsByYear(@Path("year") int year);

    @FormUrlEncoded
    @POST("emp-login")
    Call<LoginResponse> postEmpLogin(@Field("mobile") String mobile,
                                     @Field("password") String password);

    @GET("get-all-employee-details")
    Call<TotalEmployeeResponse> getTotalEmployee();

    //get all holidays
    @GET("get-holiday")
    Call<HolidayResponse> getAllHolidays();

    //get employee details
    @GET("get-employee-details/{id}")
    Call<EmployeeProfileResponse> getEmployeeProfile(@Path("id") int id);

    //get employee bank details
    @GET("get-bank-details/{id}")
    Call<EmployeeBankDetails> getEmployeeBankDetails(@Path("id") int id);

    @FormUrlEncoded
    @POST("employee-leave-apply/{id}")
    Call<LoginResponse> postLeaveRequest(@Field("date") String date,
                                         @Field("leave_type") String leave_type,
                                         @Field("reason") String reason,
                                         @Field("end_date") String end_date,
                                         @Field("half_day") String half_day,
                                         @Path("id") int id);

    @FormUrlEncoded
    @POST("clock-in-attendance/{id}")
    Call<Punch> punchIn(@Path("id") int id,
                        @Field("date") String date,
                        @Field("status") String status,
                        @Field("clock_in") String clock_in,
                        @Field("clock_in_location") String clock_in_location
    );

    @FormUrlEncoded
    @POST("clock-out-attendance/{id}")
    Call<Punch> punchOut(@Path("id") int id,
                         @Field("clock_out") String clock_out,
                         @Field("clock_out_location") String clock_out_location
    );

    @GET("get-over-time/{id}")
    Call<OverTimeResponse> getOverTime(@Path("id") int id);

    @GET("get-company/{id}")
    Call<CompanyResponseById> getCompanyDetailsById(@Path("id") int id);

}
