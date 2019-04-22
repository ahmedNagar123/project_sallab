package com.example.team_sallab.Api;

import android.widget.EditText;

import com.example.team_sallab.Model.loginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginApi {
    @POST("login.php")
    Call<loginModel> getData(
            @Query("userName") String userName,
            @Query("password") String password
    );
}
