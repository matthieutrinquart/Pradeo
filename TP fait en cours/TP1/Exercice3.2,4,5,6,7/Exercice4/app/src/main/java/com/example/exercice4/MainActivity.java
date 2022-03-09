package com.example.exercice4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 10;
    EditText Nom;
    EditText Prenom;
    EditText age;
    EditText Numero;
    EditText domaine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nom = (EditText) this.findViewById(R.id.Nom);
        Prenom = (EditText) this.findViewById(R.id.Prenom);
        age = (EditText) this.findViewById(R.id.Age);
        Numero = (EditText) this.findViewById(R.id.numero);
        domaine = (EditText) this.findViewById(R.id.Domaine);
    }


    public void jeclique(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Titredialogue);
        builder.setMessage(R.string.Textedialogue);
        builder.setPositiveButton(R.string.confirmerdialogue, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Prenom :" + Prenom.getText().toString() +"\n"+
                        "Nom :" + Nom.getText().toString() + "\n"+
                        "Age :" + age.getText().toString() + "\n"+
                        "Numero :" + Numero.getText().toString()+"\n"+
                        "Domaine :" + domaine.getText().toString(), Toast.LENGTH_LONG).show();
                Intent  intention= new Intent(MainActivity.this, MainActivity2.class);
                intention.putExtra("Nom", Nom.getText().toString());
                intention.putExtra("Prenom", Prenom.getText().toString() );
                intention.putExtra("Age", age.getText().toString());
                intention.putExtra("Numero", Numero.getText().toString());
                intention.putExtra("Domaine", domaine.getText().toString());
                startActivityForResult(intention, REQUEST_CODE);

            }

        });

        builder.setNegativeButton(R.string.annulerdialogue, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }
}