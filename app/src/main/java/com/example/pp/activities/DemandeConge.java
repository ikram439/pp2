package com.example.pp.activities;

public class DemandeConge {
    int id;
    String date_fin,date_debut,demandeur;



    public DemandeConge(int id, String date_fin, String date_debut, String demandeur) {
        this.id = id;
        this.date_fin = date_fin;
        this.date_debut = date_debut;
        this.demandeur = demandeur;
    }

    public DemandeConge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

}
