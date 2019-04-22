package com.example.team_sallab.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.team_sallab.R;

public class prefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;
    public prefConfig(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();

    }
    public boolean readLoginstatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);

    }
    public void writeName(String name){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name),name);
        editor.commit();
    }
    public String readName(){
        return sharedPreferences.getString(context.getString(R.string.pref_name),"name");
    }
    public void writepassword(String password){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_password),password);
        editor.commit();
    }
    public String readpassword(){
        return sharedPreferences.getString(context.getString(R.string.pref_password),"password");
    }
    public void displayToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
