package com.example.team_sallab.Api;

public class ApiUtils {
    public static final String  BASE_URL="http://127.0.0.1/testApi/";
    public static LoginApi getLogin(){
        return RetrofitClient.getInstance(BASE_URL).create(LoginApi.class);
    }
}
