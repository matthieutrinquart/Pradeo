package com.example.exercice32;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        LinearLayout monLinearLayout = new LinearLayout(this);

        monLinearLayout.setOrientation(LinearLayout.VERTICAL);

        EditText prenom = new EditText(this);
        prenom.setHint("Prenom");


        EditText nom = new EditText(this);
        nom.setHint("Nom");



        EditText age = new EditText(this);
        age.setHint("Age");


        EditText numero = new EditText(this);
        numero.setHint("Numéro");


        EditText domaine = new EditText(this);
        domaine.setHint("Domaine");

        Button bouton = new Button(this);
        bouton.setText("VALIDER");

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Prenom : " + prenom.getText().toString() +"\n"+
                        "Nom : " + nom.getText().toString() + "\n"+
                        "Age : " + age.getText().toString() + "\n"+
                        "Numero : " + numero.getText().toString()+"\n"+
                        "Domaine : " + domaine.getText().toString(), Toast.LENGTH_LONG).show();
            }});


        monLinearLayout.addView(prenom);
        monLinearLayout.addView(nom);
        monLinearLayout.addView(age);
        monLinearLayout.addView(numero);
        monLinearLayout.addView(domaine);
        monLinearLayout.addView(bouton);


        setContentView(monLinearLayout);
    }
}