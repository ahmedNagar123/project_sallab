package com.example.team_sallab.Model;

import com.google.gson.annotations.SerializedName;

public class loginModel {
//    String name , password;
//    String message;
//    Boolean success;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//
//
//    public Boolean getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(Boolean success) {
//        this.success = success;
//    }
//
//
//    public String getName()
//    {
//        return this.name;
//    }
//
//    public String getPassword()
//    {
//        return this.password;
//    }
//
//    public void setName(String name)
//    {
//        this.name =  name;
//    }
//
//    public void setPassword(String password)
//    {
//        this.password =  password;
//    }
//}


    @SerializedName("response")
    private String response;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}