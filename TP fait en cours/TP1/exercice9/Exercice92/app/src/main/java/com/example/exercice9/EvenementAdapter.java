package com.example.exercice9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EvenementAdapter extends BaseAdapter {

    private Context context;
    private List<Evenement> list;
    private LayoutInflater inflater;

    public EvenementAdapter(Context context, List<Evenement> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Evenement getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.evenemnt_adptateur_layout,null);

        Evenement e = getItem(i);
        TextView date = view.findViewById(R.id.Datea);
        TextView horaire = view.findViewById(R.id.Horrairea);
        TextView titre = view.findViewById(R.id.titrea);
        TextView description = view.findViewById(R.id.descriptiona);

        date.setText(e.getDay()+"/"+e.getMonth()+"/" + e.getYear());
        String heure;
        String minute;
        if(e.getMin() < 10){
            minute = "0"+e.getMin();

        }else{
            minute = ""+e.getMin();
        }

        if(e.getHour() < 10){
            heure = "0"+e.getHour();

        }else{
            heure = ""+e.getHour();
        }
        horaire.setText(heure+":"+minute);
        titre.setText(e.getTitre());
        description.setText(e.getDescription());


        return view;
    }
}
