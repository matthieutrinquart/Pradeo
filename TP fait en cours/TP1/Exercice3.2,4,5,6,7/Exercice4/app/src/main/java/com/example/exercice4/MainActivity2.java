package com.example.exercice4;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity2 extends Activity {
    private static final int REQUEST_CODE = 10;
    TextView Nom;
    TextView Prenom;
    TextView age;
    TextView Numero;
    TextView domaine;
    Bundle extras ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Nom = (TextView) this.findViewById(R.id.Nom);
        Prenom = (TextView) this.findViewById(R.id.Prenom);
        age = (TextView) this.findViewById(R.id.Age);
        Numero = (TextView) this.findViewById(R.id.numero);
        domaine = (TextView) this.findViewById(R.id.Domaine);

        extras = getIntent().getExtras();


        Nom.setText( extras.getString("Nom"));
        Prenom.setText( extras.getString("Prenom"));
        age.setText( extras.getString("Age"));
        Numero.setText( extras.getString("Numero"));
        domaine.setText( extras.getString("Domaine"));





    }


    public void Retour(View view) {

        Intent  intention= new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intention);


    }

    public void Continuer(View view) {

        Intent  intention= new Intent(MainActivity2.this, MainActivity3.class);
        intention.putExtra("numero", extras.getString("Numero"));
        startActivityForResult(intention, REQUEST_CODE);


    }
    }

