package com.example.tp2.Menu;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tp2.Exo1.Exo1;
import com.example.tp2.Exo2.Exo2;
import com.example.tp2.Exo3.Exo3;
import com.example.tp2.Exo4.Exo4;
import com.example.tp2.Exo5.Exo5;
import com.example.tp2.Exo6.ExoProximiter;
import com.example.tp2.Exo7.Exo7;
import com.example.tp2.R;
import pub.devrel.easypermissions.EasyPermissions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int READ_STORAGE_PERMISSION_REQUEST = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView simpleGrid;
        ArrayList birdList=new ArrayList<>();
        simpleGrid = (GridView) findViewById(R.id.grid);
        birdList.add(new Item("Exercice 1",R.drawable.icon));
        birdList.add(new Item("Exercice 2",R.drawable.icon2));
        birdList.add(new Item("Exercice 3",R.drawable.icon3));
        birdList.add(new Item("Exercice 4",R.drawable.icon4));
        birdList.add(new Item("Exercice 5",R.drawable.icon5));
        birdList.add(new Item("Exercice 6",R.drawable.icon6));
        birdList.add(new Item("Exercice 7",R.drawable.icon7));

        GridAdaptateur myAdapter=new GridAdaptateur(this,R.layout.adaptateur_grid_layout,birdList);
        simpleGrid.setAdapter(myAdapter);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Intent intention;
                switch (position){
                    case 0 :
                        intention= new Intent(MainActivity.this, Exo1.class);
                        startActivity(intention);

                        break;
                    case 1 :
                        intention= new Intent(MainActivity.this, Exo2.class);
                        startActivity(intention);

                        break;
                    case 2 :
                        intention= new Intent(MainActivity.this, Exo3.class);
                        startActivity(intention);

                        break;
                    case 3 :
                        intention= new Intent(MainActivity.this, Exo4.class);
                        startActivity(intention);

                        break;
                    case 4 :
                        intention= new Intent(MainActivity.this, Exo5.class);
                        startActivity(intention);

                        break;
                    case 5 :
                        intention= new Intent(MainActivity.this, ExoProximiter.class);
                        startActivity(intention);

                        break;
                    case 6 :
                        intention= new Intent(MainActivity.this, Exo7.class);
                        startActivity(intention);

                        break;

                }
            }
        });


        String permission1 = Manifest.permission.ACCESS_FINE_LOCATION;
        String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;
        if (!EasyPermissions.hasPermissions(this, permission1)) {
            EasyPermissions.requestPermissions(this, "Our App Requires a permission to access your storage", READ_STORAGE_PERMISSION_REQUEST
                    , permission1);
        }

        if (!EasyPermissions.hasPermissions(this, permission2)) {
            EasyPermissions.requestPermissions(this, "Our App Requires a permission to access your storage", READ_STORAGE_PERMISSION_REQUEST
                    , permission2);
        }


    }

    public void Exo1(View view){
        Intent intention= new Intent(MainActivity.this, Exo1.class);
        startActivity(intention);

    }
    public void Exo2(View view){
        Intent intention= new Intent(MainActivity.this, Exo2.class);
        startActivity(intention);

    }
    public void Exo3(View view){
        Intent intention= new Intent(MainActivity.this, Exo3.class);
        startActivity(intention);

    }
    public void Exo4(View view){
        Intent intention= new Intent(MainActivity.this, Exo4.class);
        startActivity(intention);

    }
    public void Exo5(View view){
        Intent intention= new Intent(MainActivity.this, Exo5.class);
        startActivity(intention);

    }
    public void Exo6(View view){
        Intent intention= new Intent(MainActivity.this, ExoProximiter.class);
        startActivity(intention);

    }
    public void Exo7(View view){
        Intent intention= new Intent(MainActivity.this, Exo7.class);
        startActivity(intention);

    }
}