package com.example.staffinemployees.Interface;

import com.example.staffinemployees.Fragment.ClaimExpences;
import com.example.staffinemployees.Response.CompanyResponseById;
import com.example.staffinemployees.Response.CreatedHolidayResp;
import com.example.staffinemployees.Response.EmployeeBankDetails;
import com.example.staffinemployees.Response.EmployeeProfileResponse;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.GetMonthlyAttendance;
import com.example.staffinemployees.Response.HolidayResponse;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Response.NationalHolidayResp;
import com.example.staffinemployees.Response.OverTimeResponse;
import com.example.staffinemployees.Response.PaySlipResponse;
import com.example.staffinemployees.Response.Punch;
import com.example.staffinemployees.Response.TotalEmployeeResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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


    @Multipart
    @POST("employee-update/{id}")
    Call<EmployeeProfileResponse> postUpdateEmployee(
            @Path("id") int id,
            @Part MultipartBody.Part profile_image,
            @Part("name") RequestBody name,
            @Part("father_name") RequestBody father_name,
            @Part("date_of_birth") RequestBody date_of_birth,
            @Part("mobile") RequestBody mobile,
            @Part("gender") RequestBody gender,
            @Part("email") RequestBody email,
            @Part("local_address") RequestBody local_address,
            @Part("parmanent_address") RequestBody parmanent_address
    );

    @FormUrlEncoded
    @POST("employee-update/{id}")
    Call<EmployeeProfileResponse> postUpdateEmployeeWithoutImage(
            @Path("id") int id,
            @Field("name") String name,
            @Field("father_name") String father_name,
            @Field("date_of_birth") String date_of_birth,
            @Field("mobile") String mobile,
            @Field("gender") String gender,
            @Field("email") String email,
            @Field("local_address") String local_address,
            @Field("parmanent_address") String parmanent_address
    );

    @Multipart
    @POST("add-event")
    Call<LoginResponse> addEventFunc(
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part image1,
            @Part MultipartBody.Part image2,
            @Part MultipartBody.Part image3,
            @Part("title_name") RequestBody title_name,
            @Part("location") RequestBody location,
            @Part("description") RequestBody description,
            @Part("date") RequestBody date,
            @Part("add_member") RequestBody add_member
    );

    @FormUrlEncoded
    @POST("get-attendance")
    Call<GetMonthlyAttendance> getMonthlyAttendanceByEid(@Field("month") int month,
                                                         @Field("year") int year,
                                                         @Field("employeeID") int employeeID);

    //getPaySLip
    @GET("get-payslip/{id}")
    Call<PaySlipResponse> getPaySlip(@Path("id") int id);


    //get all holidays
    @GET("get-holiday")
    Call<CreatedHolidayResp> getCreatedHolidays();

    @FormUrlEncoded
    @POST("get-national-holiday")
    Call<NationalHolidayResp> getNationalHolidayMonthly(@Field("country") String country,
                                                        @Field("month") int month,
                                                        @Field("year") int year);

    @FormUrlEncoded
    @POST("get-national-holiday")
    Call<NationalHolidayResp> getNationalHoliday(@Field("country") String country,
                                                 @Field("year") int year);

    // claim expenses api , array of images post
    @Multipart
    @POST("add-expense/{id}")
    Call<LoginResponse> postExpenses(@Path("id") int id,
                                     @Part MultipartBody.Part[] bill_image,
                                     @Part("name") RequestBody name,
                                     @Part("price") RequestBody price);


    @FormUrlEncoded
    @POST("update-event/{id}")
    Call<LoginResponse> postUpdateInterested(@Path("id") int id,
                                             @Field("name") String name);
}
