package com.example.myapplication_co_aout.model;

import java.util.ArrayList;

public class Liste_article {

    private String nom_article;


    // Constructeur
    public Liste_article(String nom_article) {
        this.nom_article=nom_article;
    }


    public String getNom_liste() {
        return nom_article;
    }
    public void setNom_list(String nom_liste) {
        this.nom_article= nom_liste;
    }


}
