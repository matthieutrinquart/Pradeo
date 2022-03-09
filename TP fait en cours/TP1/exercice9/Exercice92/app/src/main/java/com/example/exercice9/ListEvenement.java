package com.example.exercice9;

import java.util.ArrayList;

public class ListEvenement {

    private ArrayList<Evenement> listEvenement;

    public ListEvenement(){

        listEvenement = new ArrayList<>();
    }


    public void add(Evenement e){
        listEvenement.add(e);
    }

    public ArrayList<Evenement> dateEvenement(int year ,int month , int day){
        ArrayList<Evenement> ret = new ArrayList<>();
        for(Evenement e : listEvenement){
            if(year==e.getYear() && month == e.getMonth() && day == e.getDay())
                ret.add(e);
        }
        return ret;
    }



}
