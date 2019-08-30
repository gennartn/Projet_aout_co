package com.example.myapplication_co_aout.model;

public class Personne {


    private String nom;
    private String prenom;
    private String date;
    private String cathegorie;


    public Personne(String nom,String prenom, String date, String cathegorie) {
        this.nom=nom;
        this.prenom=prenom;
        this.date=date;
        this.cathegorie=cathegorie;
    }



    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom= prenom;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCathegorie() {
        return cathegorie;
    }
    public void setCathegorie(String cathegorie) {
        this.cathegorie = cathegorie;
    }


}
