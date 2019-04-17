package com.example.team_sallab.Model;

public class loginModel
{
    String name , password;
    String message;
    int success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }


    public String getName()
    {
        return this.name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setName(String name)
    {
        this.name =  name;
    }

    public void setPassword(String password)
    {
        this.password =  password;
    }
}
