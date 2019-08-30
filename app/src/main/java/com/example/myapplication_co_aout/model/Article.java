package com.example.myapplication_co_aout.model;

public class Article {


    private String nom_article;
    private String prix;
    private String magasin;
    private String cathegorie;

    public Article(String nom_article,String prix, String magasin, String cathegorie) {
        this.nom_article=nom_article;
        this.prix=prix;
        this.magasin=magasin;
        this.cathegorie=cathegorie;
    }


    public String getNom_article() {
        return nom_article;
    }
    public void setNom_Article(String nom_article) {
        this.nom_article = nom_article;
    }
    public String getPrix_article() {
        return prix;
    }
    public void setPrix_article(String prix) {
        this.prix = prix;
    }
    public String getNom_magasin() {
        return magasin;
    }
    public void setNom_magasin(String nom_magasin) {
        this.magasin = nom_magasin;
    }
    public String getNom_cathegorie() {
        return cathegorie;
    }
    public void setNom_cathegorie(String cathegorie) {
        this.cathegorie = cathegorie;
    }

}
