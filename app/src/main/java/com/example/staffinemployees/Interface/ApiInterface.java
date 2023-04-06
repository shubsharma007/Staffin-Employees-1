package com.example.staffinemployees.Interface;

import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Response.TotalEmployeeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("emp-login")
    Call<LoginResponse> postEmpLogin(@Field("mobile") String mobile,
                                     @Field("password") String password);


    @GET("get-all-employee-details")
    Call<TotalEmployeeResponse> getTotalEmployee();
}
