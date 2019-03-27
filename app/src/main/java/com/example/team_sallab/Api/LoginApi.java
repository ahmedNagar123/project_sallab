package com.example.team_sallab.Api;

import com.example.team_sallab.Model.loginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface LoginApi {
    @FormUrlEncoded
    @GET("login.php")
    Call<loginModel> getData(
            @Field("userName")String userName,
            @Field("password") String password
    );
}
