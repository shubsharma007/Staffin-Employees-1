package com.example.staffinemployees.Interface;

import com.example.staffinemployees.Response.EmployeeProfileResponse;
import com.example.staffinemployees.Response.HolidayResponse;
import com.example.staffinemployees.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("emp-login")
    Call<LoginResponse> postEmpLogin(@Field("mobile") String mobile,
                                     @Field("password") String password);

    //get all holidays
    @GET("get-holiday")
    Call<HolidayResponse> getAllHolidays();

    @GET("get-employee-details/{id}")
    Call<EmployeeProfileResponse> getEmployeeProfile(@Path("id") int id);

}
