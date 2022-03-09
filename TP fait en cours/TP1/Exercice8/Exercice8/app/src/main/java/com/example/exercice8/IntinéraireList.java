package com.example.exercice8;

import android.util.Pair;

import java.util.ArrayList;

public class IntinéraireList {

    ArrayList<Itinéraire> listItinéraire;

    public IntinéraireList(){

        listItinéraire =  new ArrayList<>();
    }
    public void add(Itinéraire i){
        listItinéraire.add(i);
    }
    /*
    Cette fonction prend en paramètre 2 String qui ont été écrite dans les EditText de l'application et retourne
     une liste de d'Itinéraire dont les premières lettres correspondes au caractères donner en paramètres
     (donc écrites dans les EditText).
     */
    public ArrayList<Itinéraire> firtcharactereequal(String villedepart , String villearriver){
        ArrayList<Itinéraire> retour  = new ArrayList<>();
        for(Itinéraire i : listItinéraire ){
            if(i.equalfirstcharactere(villedepart , villearriver)){
                retour.add(i);
            }

        }
        return retour;


    }



}
