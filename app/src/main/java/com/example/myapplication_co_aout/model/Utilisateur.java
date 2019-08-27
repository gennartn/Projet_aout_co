package com.example.myapplication_co_aout.model;

import android.content.Context;

import java.util.ArrayList;

public class Utilisateur {

    private String username;
    private String password;

    public Utilisateur(String username, String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String pseudo) {
        this.username= username;
    }
    public String getNom_Password() {
        return password;
    }
    public void setNom_Password(String nom_personne) {
        this.password = nom_personne;
    }


}
