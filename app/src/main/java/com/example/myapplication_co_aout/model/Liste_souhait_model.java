package com.example.myapplication_co_aout.model;

public class Liste_souhait_model {

    private String nom_liste;
    private String nom_personne;


    public Liste_souhait_model(String nom_liste,String nom_personne) {
        this.nom_liste=nom_liste;
        this.nom_personne=nom_personne;
    }


    public String getNom_liste() {
        return nom_liste;
    }
    public void setNom_list(String nom_liste) {
        this.nom_liste= nom_liste;
    }
    public String getNom_personne() {
        return nom_personne;
    }
    public void setNom_personne(String nom_personne) {
        this.nom_personne = nom_personne;
    }
}
