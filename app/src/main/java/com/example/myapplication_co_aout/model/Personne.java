package com.example.myapplication_co_aout.model;

public class Personne {

    private String pseudo;
    private String nom_personne;

    // Constructeur
    public Personne(String pseudo,String nom_personne) {
        this.pseudo=pseudo;
        this.nom_personne=nom_personne;
    }


    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo= pseudo;
    }
    public String getNom_Personne() {
        return nom_personne;
    }
    public void setNom_personne(String nom_personne) {
        this.nom_personne = nom_personne;
    }
}
