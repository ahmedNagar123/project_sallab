package com.example.team_sallab.Api;

import android.widget.EditText;

import com.example.team_sallab.Model.loginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApi {
    @FormUrlEncoded
    @POST("login.php/{userName}/{password}")
    Call<loginModel> getData(
            @Path("userName") String userName,
            @Path("password") String password
    );
}
