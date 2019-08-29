package com.example.myapplication_co_aout.model;

public class SpinnerDataArticle {

    private String name;
    private String email;
    private String age;

    public SpinnerDataArticle(String name, String email, String age) {
        this.name = name;
        this.email=email;
        this.age = age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }



}
