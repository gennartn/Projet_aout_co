package com.example.myapplication_co_aout.model;

public class Article {


    private String nom_article;
    private int prix;

        // Constructeur
    public Article(String nom_article,int prix) {
        this.nom_article=nom_article;
        this.prix=prix;
    }


    public String getNom_article() {
        return nom_article;
    }
    public void setNom_Article(String nom_article) {
        this.nom_article = nom_article;
    }
    public int getPrix_article() {
        return prix;
    }
    public void setPrix_article(int prix) {
        this.prix = prix;
    }

}
