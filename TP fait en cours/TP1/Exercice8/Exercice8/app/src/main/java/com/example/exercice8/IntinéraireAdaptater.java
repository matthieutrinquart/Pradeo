package com.example.exercice8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
/*
Cette classe est un adaptateur qui permet d'afficher de façon jolie dans la ListView la liste des itinéraires.
Cette classe va utiliser la layout intineraire_adaptateur_layout.xml pour affficher la liste des itinèraires.
 */
public class IntinéraireAdaptater extends BaseAdapter {

    private Context context;
    private List<Itinéraire> list;
    private LayoutInflater inflater;

    public IntinéraireAdaptater(Context context, List<Itinéraire> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    /*
    Getter
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Itinéraire getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.itineraire_adaptateur_layout,null);

        Itinéraire e = getItem(i);
        TextView villed = view.findViewById(R.id.Villedepart);
        TextView villea = view.findViewById(R.id.villearriver);
        TextView date = view.findViewById(R.id.date);
        TextView heured = view.findViewById(R.id.heuredepart);

        TextView heurea = view.findViewById(R.id.heurearriver);
        villed.setText(e.getVilledepart());
        villea.setText(e.getVillearriver());
        date.setText(e.getDatedepart());
        heured.setText(e.getHeuredepart());
        heurea.setText(e.getHeurearriver());
        return view;
    }
}
