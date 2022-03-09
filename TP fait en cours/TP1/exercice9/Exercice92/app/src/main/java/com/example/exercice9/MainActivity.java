package com.example.exercice9;


import android.icu.util.Calendar;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private int year= Calendar.getInstance().get(Calendar.YEAR);
    private int month= Calendar.getInstance().get(Calendar.MONTH);
    private int day= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private ListEvenement list;
    private ListView listView ;


    private int oldyear;
    private int oldmonth;
    private int oldday;

   public CustomDialog boitedialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ListEvenement();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = (DatePicker) this.findViewById(R.id.datePicker);
        listView = (ListView) findViewById(R.id.listView);
        boitedialog = new CustomDialog(MainActivity.this);
        this.datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {

                MainActivity.this.year = year;
                MainActivity.this.month = month;
                MainActivity.this.day = dayOfMonth;
                if(year == MainActivity.this.oldyear && month == MainActivity.this.oldmonth && dayOfMonth ==MainActivity.this.oldday){
                    boitedialog.getconfirmerrbouton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Evenement even = new Evenement(year,month,day,boitedialog.getPicker().getHour(),boitedialog.getPicker().getMinute(),boitedialog.getTitre().getText().toString(),boitedialog.getDescription().getText().toString());

                            list.add(even);

                            boitedialog.dismiss();
                            ArrayList<Evenement> E = list.dateEvenement(year,month,day);

                            listView.setAdapter(new EvenementAdapter(MainActivity.this,E));


                        }});

                    boitedialog.getannulerbouton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boitedialog.dismiss();


                        }});
                    boitedialog.show();

                }
                ArrayList<Evenement> E = list.dateEvenement(year,month,day);

                listView.setAdapter(new EvenementAdapter(MainActivity.this,E));


                MainActivity.this.oldyear = year;
                MainActivity.this.oldmonth = month;
                MainActivity.this.oldday = dayOfMonth;
            }
        });
    }





}