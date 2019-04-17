package com.example.team_sallab.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team_sallab.Api.ApiUtils;
import com.example.team_sallab.Api.LoginApi;
import com.example.team_sallab.R;
import com.example.team_sallab.Model.loginModel;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static com.example.team_sallab.Api.RetrofitClient.getInstance;

public class LoginActivity extends AppCompatActivity implements Callback<loginModel>, View.OnClickListener {

    EditText etuserName,etpassword;
    Button btnLogin;
     LoginApi login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etuserName=findViewById(R.id.etID);
        etpassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btSignup3);
        btnLogin.setOnClickListener(this);
        login= ApiUtils.getLogin();



    }

    @Override
    public void onClick(View v) {
        String userName = etuserName.getText().toString();
        String password = etpassword.getText().toString();
        // validate form
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
            doLogin(userName,password);
        }else
            Toast.makeText(this, "All field Require", Toast.LENGTH_SHORT).show();


    }

    private void doLogin(String userName, String password) {
        Call<loginModel> call = login.getData(userName, password);
        call.enqueue(this);


    }


    @Override
    public void onResponse(Call<loginModel> call, Response<loginModel> response) {
        if(response.isSuccessful()) {
            loginModel loginModel = response.body();
            if (loginModel.getMessage().equals("true")) {
                // login start Activity
                Log.e("success",response.message());

            } else {
                Toast.makeText(this, "userName And Password Incorrect", Toast.LENGTH_SHORT).show();

            }

            } else {
            Toast.makeText(this, "Error please try Again !!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<loginModel> call, Throwable t) {
        Toast.makeText(LoginActivity.this,"Failed" ,Toast.LENGTH_LONG).show();
        Log.e("login Activity" , t.getMessage());
    }


}
