package com.example.exercice4;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {
    TextView Nom;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Nom = (TextView) this.findViewById(R.id.numero);

        extras = getIntent().getExtras();

        Nom.setText(extras.getString("numero"));
    }

    public void appeler(View view){
        String phone = extras.getString("numero");
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);

    }
}