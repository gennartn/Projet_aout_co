package com.example.myapplication_co_aout.model;

public class Log {

    private String username;
    private String password;

    // Constructeur
    public Log(String username,String password) {
        this.username=username;
        this.password=password;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username= username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}