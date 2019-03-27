package com.example.team_sallab.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.team_sallab.Api.LoginApi;
import com.example.team_sallab.R;
import com.example.team_sallab.Model.loginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static com.example.team_sallab.Api.RetrofitClient.getInstance;

public class LoginActivity extends AppCompatActivity implements Callback<loginModel> {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit= getInstance();
        LoginApi login = retrofit.create(LoginApi.class);
        Call<loginModel> call = login.getData("name", "123456");
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<loginModel> call, Response<loginModel> response) {
        if(response.isSuccessful())
        {
            if(response.body().getName() == "Entered name" && response.body().getPassword() == "Entered passwored")
            {
                //TODO
                // open second activity
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            }else{
                Log.e("error",response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<loginModel> call, Throwable t) {
        Toast.makeText(LoginActivity.this,"Failed" ,Toast.LENGTH_LONG).show();
        Log.e("login Activity" , t.getMessage());
    }


}
