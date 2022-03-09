package com.example.exercice8;

public class Itinéraire {


    private String villedepart;
    private String villearriver;

    private String Datedepart;
    private String Datearriver;


    private String Heuredepart;
    private String Heurearriver;


    private int Prix;

    public Itinéraire(String villedepart, String villearriver, String datedepart, String datearriver, String heuredepart, String heurearriver, int prix) {
        this.villedepart = villedepart;
        this.villearriver = villearriver;
        Datedepart = datedepart;
        Datearriver = datearriver;
        Heuredepart = heuredepart;
        Heurearriver = heurearriver;
        Prix = prix;
    }
    /*
    Retourne simplement un boolean à true si les premières lettres correspondes des villes correspondes
     au String passé en paramètres et False sinon.
     */
    public boolean equalfirstcharactere(String villedepart, String villearriver) {
        return this.villedepart.matches(villedepart +"(.*)") && this.villearriver.matches(villearriver +"(.*)");
    }


    public String getVilledepart() {
        return villedepart;
    }

    public String getVillearriver() {
        return villearriver;
    }

    public String getDatedepart() {
        return Datedepart;
    }

    public String getDatearriver() {
        return Datearriver;
    }

    public String getHeuredepart() {
        return Heuredepart;
    }

    public String getHeurearriver() {
        return Heurearriver;
    }


    public int getPrix() {
        return Prix;
    }
}
