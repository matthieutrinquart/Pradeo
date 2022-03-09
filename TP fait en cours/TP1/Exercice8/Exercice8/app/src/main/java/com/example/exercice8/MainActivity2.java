package com.example.exercice8;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    private Bundle extras ;
    private ImageView imaged;
    private ImageView imagea;
    private TextView villed;
    private TextView villea;

    private TextView heured;
    private  TextView heurea;
    private TextView date;
    private TextView prix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        extras = getIntent().getExtras();

        imaged = (ImageView)this.findViewById(R.id.imageView);

        imagea = (ImageView)this.findViewById(R.id.imageView5);

        villed = (TextView) this.findViewById(R.id.Villed);
        villea = (TextView) this.findViewById(R.id.Villea);

        heured = (TextView) this.findViewById(R.id.Heured);
        heurea = (TextView) this.findViewById(R.id.Heurea);

        prix = (TextView) this.findViewById(R.id.Prix);

        date = (TextView) this.findViewById(R.id.valeurd);

        villed.setText(extras.getString("Villed"));
        villea.setText(extras.getString("Villea"));

        heured.setText(extras.getString("Heured"));
        heurea.setText(extras.getString("Heurea"));


        prix.setText(extras.getInt("prix") + "€");

        date.setText(extras.getString("Dated"));

        switch (extras.getString("Villea")){

            case "Marseille" :
                imagea.setImageResource(R.drawable.marseille);
                break;
            case "Montpellier" :
                imagea.setImageResource(R.drawable.montpellier);
                break;
            case "Paris" :
                imagea.setImageResource(R.drawable.paris);
                break;
            case "Miramas" :
                imagea.setImageResource(R.drawable.miramas);
                break;
            case "Renne" :
                imagea.setImageResource(R.drawable.renne);
                break;

        }

        switch (extras.getString("Villed")){

            case "Marseille" :
                imaged.setImageResource(R.drawable.marseille);
                break;
            case "Montpellier" :
                imaged.setImageResource(R.drawable.montpellier);
                break;
            case "Paris" :
                imaged.setImageResource(R.drawable.paris);
                break;
            case "Miramas" :
                imaged.setImageResource(R.drawable.miramas);
                break;
            case "Renne" :
                imaged.setImageResource(R.drawable.renne);
                break;

        }




    }

    public void retour(View view){
        Intent intention= new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intention);
    }
}