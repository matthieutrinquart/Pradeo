package com.example.exercice8;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private IntinéraireList list;
    private EditText Villedepart;
    private IntinéraireAdaptater adapter;
    private ListView listView ;
    private EditText Villearriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Villedepart = (EditText) this.findViewById(R.id.Depart);
        Villearriver = (EditText) this.findViewById(R.id.arriver);
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<Itinéraire> i = new ArrayList<>();
        i.add(0,new Itinéraire(getString(R.string.depart),getString(R.string.arriver),getString(R.string.Date),getString(R.string.Datea),getString(R.string.Heuredepart),getString(R.string.Heurearriver),0));
        listView.setAdapter(new IntinéraireAdaptater(MainActivity.this,i));
        /*
         *Permet de cliquer sur un Item de la ListView et d'afficher le second Intent de l'application
         *
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                final int REQUEST_CODE = 20;

                Itinéraire item = adapter.getItem(position);

                Intent intention= new Intent(MainActivity.this, MainActivity2.class);
                intention.putExtra("Villed", item.getVilledepart());
                intention.putExtra("Villea", item.getVillearriver() );
                intention.putExtra("Dated", item.getDatedepart());
                intention.putExtra("Datea", item.getDatearriver());
                intention.putExtra("Heured",item.getHeuredepart());
                intention.putExtra("Heurea",item.getHeurearriver());
                intention.putExtra("prix",item.getPrix());

                startActivityForResult(intention, REQUEST_CODE);


            }
        });
        /*
            Cette parti de code permet est appelé directement dès qu'il y a une modification detecté dans l'EditText.
            Donc à chaque modification l'application va chercher la liste des itinèraire qui correspond au premier charactère ecrit dans les EditText.
            La liste d'itinèraire sera fournit à l'adaptateur qui permettra ensuite d'afficher cette liste dans la ListView
         */
        Villedepart.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Your query to fetch Data
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!Villedepart.getText().toString().isEmpty()) {

                    ArrayList<Itinéraire> o = list.firtcharactereequal(Villedepart.getText().toString(), Villearriver.getText().toString());
                    o.add(0,new Itinéraire(getString(R.string.depart),getString(R.string.arriver),getString(R.string.Date),getString(R.string.Datea),getString(R.string.Heuredepart),getString(R.string.Heurearriver),0));
                    adapter = new IntinéraireAdaptater(MainActivity.this,o);
                    listView.setAdapter(adapter);
                }
            }

        });
        /*
            Cette parti de code permet est appelé directement dès qu'il y a une modification detecté dans l'EditText.
            Donc à chaque modification l'application va chercher la liste des itinèraire qui correspond au premier charactère ecrit dans les EditText.
            La liste d'itinèraire sera fournit à l'adaptateur qui permettra ensuite d'afficher cette liste dans la ListView
         */
        Villearriver.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Your query to fetch Data
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<Itinéraire> o = list.firtcharactereequal(Villedepart.getText().toString(), Villearriver.getText().toString());
                o.add(0,new Itinéraire(getString(R.string.depart),getString(R.string.arriver),getString(R.string.Date),getString(R.string.Datea),getString(R.string.Heuredepart),getString(R.string.Heurearriver) ,0));
                adapter = new IntinéraireAdaptater(MainActivity.this,o);
                listView.setAdapter(adapter);
                }

        });

    }





    /*
        Fonction qui permet d'initialiser l'application avec quelque exemple d'itinèraire.
     */
    public void init(){

        list = new IntinéraireList();

        list.add(new Itinéraire("Paris","Montpellier","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Paris","Montpellier","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Paris","Montpellier","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Paris","Montpellier","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Paris","Montpellier","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));
        list.add(new Itinéraire("Paris","Montpellier","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Paris","Montpellier","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Paris","Montpellier","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Paris","Montpellier","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Paris","Montpellier","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));
        list.add(new Itinéraire("Paris","Montpellier","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Paris","Montpellier","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Paris","Montpellier","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Paris","Montpellier","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Paris","Montpellier","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));
        list.add(new Itinéraire("Paris","Montpellier","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Paris","Montpellier","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Paris","Montpellier","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Paris","Montpellier","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Paris","Montpellier","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));
        list.add(new Itinéraire("Paris","Montpellier","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Paris","Montpellier","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Paris","Montpellier","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Paris","Montpellier","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Paris","Montpellier","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));



        list.add(new Itinéraire("Montpellier","Paris","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Montpellier","Paris","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Montpellier","Paris","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Montpellier","Paris","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Montpellier","Paris","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));
        list.add(new Itinéraire("Montpellier","Paris","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Montpellier","Paris","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Montpellier","Paris","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Montpellier","Paris","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Montpellier","Paris","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));
        list.add(new Itinéraire("Montpellier","Paris","03/03/2015" , "03/03/2015", "14H30", "15H30"  ,50));
        list.add(new Itinéraire("Montpellier","Paris","23/03/2015" , "23/03/2015", "19H30", "21H30",70));
        list.add(new Itinéraire("Montpellier","Paris","14/03/2015" , "14/03/2015", "11H30", "13H30" ,60));
        list.add(new Itinéraire("Montpellier","Paris","28/11/2015" , "28/11/2015", "14H30", "15H30"  ,55));
        list.add(new Itinéraire("Montpellier","Paris","21/03/2015" , "21/03/2015", "7H30", "9H30"  ,45));



        list.add(new Itinéraire("Renne","Paris","25/9/2015" , "25/9/2015", "10H30", "12H30"  ,60));
        list.add(new Itinéraire("Renne","Paris","04/12/2015" , "04/12/2015", "11H30", "12H30",45));
        list.add(new Itinéraire("Renne","Paris","12/06/2015" , "12/06/2015", "12H30", "12H30" ,50));
        list.add(new Itinéraire("Renne","Paris","23/11/2015" , "23/11/2015", "12H30", "12H30",70));
        list.add(new Itinéraire("Renne","Paris","01/01/2015" , "01/01/2015", "13H30", "12H30",64));
        list.add(new Itinéraire("Renne","Paris","14/03/2015" , "14/03/2015", "14H30", "12H30" ,75));
        list.add(new Itinéraire("Renne","Paris","25/9/2015" , "25/9/2015", "10H30", "12H30"  ,60));
        list.add(new Itinéraire("Renne","Paris","04/12/2015" , "04/12/2015", "11H30", "12H30",45));
        list.add(new Itinéraire("Renne","Paris","12/06/2015" , "12/06/2015", "12H30", "12H30" ,50));
        list.add(new Itinéraire("Renne","Paris","23/11/2015" , "23/11/2015", "12H30", "12H30",70));
        list.add(new Itinéraire("Renne","Paris","01/01/2015" , "01/01/2015", "13H30", "12H30",64));
        list.add(new Itinéraire("Renne","Paris","14/03/2015" , "14/03/2015", "14H30", "12H30" ,75));
        list.add(new Itinéraire("Renne","Paris","25/9/2015" , "25/9/2015", "10H30", "12H30"  ,60));
        list.add(new Itinéraire("Renne","Paris","04/12/2015" , "04/12/2015", "11H30", "12H30",45));
        list.add(new Itinéraire("Renne","Paris","12/06/2015" , "12/06/2015", "12H30", "12H30" ,50));
        list.add(new Itinéraire("Renne","Paris","23/11/2015" , "23/11/2015", "12H30", "12H30",70));
        list.add(new Itinéraire("Renne","Paris","01/01/2015" , "01/01/2015", "13H30", "12H30",64));
        list.add(new Itinéraire("Renne","Paris","14/03/2015" , "14/03/2015", "14H30", "12H30" ,75));







        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));
        list.add(new Itinéraire("Paris","Renne","14/06/2015" , "14/06/2015", "15H30", "17H30" ,55));




        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Miramas","Montpellier","25/08/2015" , "25/08/2015", "18H30", "19H30",30));



        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30" ,30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30",30));
        list.add(new Itinéraire("Montpellier","Miramas","25/08/2015" , "25/08/2015", "18H30", "19H30",30));



        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Montpellier","Marseille","20/09/2015" , "20/09/2015", "13H30", "15H30",40));



        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30",40));

        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Montpellier","20/09/2015" , "20/09/2015", "13H30", "15H30",40));


        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30",40));

        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30",40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30" ,40));
        list.add(new Itinéraire("Marseille","Renne","20/09/2015" , "20/09/2015", "13H30", "15H30",40));



    }




}