package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result_login")
    @Expose
    private ResultLogin resultLogin;

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

    public ResultLogin getResultLogin() {
        return resultLogin;
    }

    public void setResultLogin(ResultLogin resultLogin) {
        this.resultLogin = resultLogin;
    }



}
