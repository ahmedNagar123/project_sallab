package com.example.team_sallab.Model;

public class loginModel
{
    String name , password;
    int success;

//    public loginModel(String name , String password)
//    {
//        this.name = name;
//        this.password = password;
//    }

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
