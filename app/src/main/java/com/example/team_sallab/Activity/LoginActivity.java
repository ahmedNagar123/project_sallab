package com.example.team_sallab.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team_sallab.Api.ApiUtils;
import com.example.team_sallab.Api.LoginApi;
import com.example.team_sallab.Network.BackgroundTask;
import com.example.team_sallab.Network.BackgroundTaskInterface;
import com.example.team_sallab.Network.NetworkStatusAndType;
import com.example.team_sallab.R;
import com.example.team_sallab.Model.loginModel;
import com.example.team_sallab.pref.prefConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static com.example.team_sallab.Api.RetrofitClient.getInstance;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {

    EditText etuserName,etpassword;
    Button btnLogin;
     LoginApi login;
     private final static String Login_Script = "https://www.deltamedonline.com/testApi/login.php";
//    private SharedPreferences preferences;
    private prefConfig prefConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etuserName=findViewById(R.id.etID);
        etpassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btSignup3);
        btnLogin.setOnClickListener(this);
        login= ApiUtils.getLogin();
//        preferences=getSharedPreferences("user",MODE_PRIVATE);


    }

    @Override
    public void onClick(View v) {
        final String userName = etuserName.getText().toString();
        final String password = etpassword.getText().toString();


        // validate form
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
//            doLogin(userName,password);

            switch (new NetworkStatusAndType(LoginActivity.this).NetworkStatus())
            {
                case 0:
                    //TODO disconnected

                    Toast.makeText(this, "You Are Discoonected the Internet", Toast.LENGTH_SHORT).show();

                    break;
                case 1:
                    //TODO connecting
                    break;
                case 2:

                    Map<String,Object> values = new LinkedHashMap<>();
                    values.put("userName" , userName);
                    values.put("password", password);

                    new BackgroundTask(new BackgroundTaskInterface() {
                        @Override
                        public void processFinished(String response) {
                            if(response != null)
                            {
                                try {
                                    Log.e("response" , response);
                                    JSONObject mainObject = new JSONObject(response.substring(response.indexOf("{"),
                                            response.lastIndexOf("}")+1));

                                    int success = mainObject.getInt("success");
                                    switch (success)
                                    {
                                        case 0:
                                            //TODO
                                            break;
                                        case 1:
                                            // login start Activity
                                            prefConfig.writeLoginStatus(true);
                                            prefConfig.writeName(userName);
                                            prefConfig.writepassword(password);
                                            prefConfig.displayToast("Login Success");
                                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                            finish();
                                            break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },values).execute(Login_Script);
                    break;
            }
        }else
            Toast.makeText(this, "All field Require", Toast.LENGTH_SHORT).show();


    }

//    private void doLogin(String userName, String password) {
//        Call<loginModel> call = login.getData(userName, password);
//        call.enqueue(this);
//
//
//    }
//
//
//    @Override
//    public void onResponse(Call<loginModel> call, Response<loginModel> response) {
//        if(response.isSuccessful()) {
//            loginModel loginModel = response.body();
//            if (loginModel.getResponse().equals("ok")) {
//                // login start Activity
//                prefConfig.writeLoginStatus(true);
//                String name = loginModel.getName();
//                String password = loginModel.getPassword();
//                prefConfig.writeName(name);
//                prefConfig.writepassword(password);
//                prefConfig.displayToast("Login Success");
//                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                finish();
//
//
////                Log.e("success",response.message());
//
//            } else if(loginModel.getResponse().equals("failed")) {
////                Toast.makeText(this, "userName And Password Incorrect", Toast.LENGTH_SHORT).show();
//                prefConfig.displayToast("userName And Password Incorrect");
//
//            }
//
//            } else {
//            Toast.makeText(this, "Error please try Again !!", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    @Override
//    public void onFailure(Call<loginModel> call, Throwable t) {
//        Toast.makeText(LoginActivity.this,"Failed" ,Toast.LENGTH_LONG).show();
//        Log.e("login Activity" , t.getMessage());
//    }



}
