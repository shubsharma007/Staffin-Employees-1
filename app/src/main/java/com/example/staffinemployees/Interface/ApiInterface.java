package com.example.staffinemployees.Interface;

import com.example.staffinemployees.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("emp-login")
    Call<LoginResponse> postEmpLogin(@Field("email") String email,
                                     @Field("password") String password);
}
