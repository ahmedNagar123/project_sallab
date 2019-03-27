package com.example.team_sallab.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getInstance(){
      return new Retrofit.Builder().baseUrl("http://192.168.1.115/testApi/").addConverterFactory(GsonConverterFactory.create()).build();
    }
}
