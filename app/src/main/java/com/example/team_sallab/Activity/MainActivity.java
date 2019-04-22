package com.example.team_sallab.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.example.team_sallab.R;
import com.example.team_sallab.pref.loginFormActivity;
import com.example.team_sallab.pref.prefConfig;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements loginFormActivity {
    public static prefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final boolean b = getSharedPreferences("user", MODE_PRIVATE).getBoolean("login", false);
        prefConfig=new prefConfig(this);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (prefConfig.readLoginstatus()) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));

                }
                else
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

                finish();



            }
        }, 3000);

    }

    @Override
    public void preformLogin(String name, String password) {

    }
}