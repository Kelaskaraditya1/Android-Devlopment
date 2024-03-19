package com.starkindustries.databases_mark2;

public class User
{
    public String sid,name,phone_no,username,password;
    public User(String sid,String name,String phone_no,String username,String password)
    {
        this.sid=sid;
        this.name=name;
        this.phone_no=phone_no;
        this.username=username;
        this.password=password;
    }
    public User(String name,String phone_no,String username,String password)
    {
        this.name=name;
        this.phone_no=phone_no;
        this.username=username;
        this.password=password;
    }
    public User(String name,String phone_no,String username)
    {
        this.name=name;
        this.phone_no=phone_no;
        this.username=username;
    }
    public User()
    {}

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
