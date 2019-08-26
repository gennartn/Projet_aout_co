package com.example.myapplication_co_aout.model;

import java.util.ArrayList;

public class Liste_article {

    private ArrayList<String> nom_list_article;


    // Constructeur
    public Liste_article(ArrayList<String> nom_article) {
        this.nom_list_article=nom_article;

    }


    public ArrayList<String> getNom_liste_article() {
        return nom_list_article;
    }
    public void setNom_Liste_Article(ArrayList<String> nom_list_article) {
        this.nom_list_article = nom_list_article;
    }

    public String getArticle(ArrayList<String> nom_list_article, int i){
        return nom_list_article.get(i);
    }
    public void setArticle(int i, String article){
        this.nom_list_article.set(i, article);
    }

}
