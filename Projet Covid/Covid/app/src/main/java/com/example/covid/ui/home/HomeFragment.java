package com.example.covid.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.covid.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URL;


public class HomeFragment extends Fragment {
    private TextView cas1;
    private TextView cas2;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Handler handler = new Handler();
         new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            JSONObject jsonRootstat;
                            jsonRootstat = new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/rozierguillaume/covid-19/master/data/france/stats/stats.json").openStream())).readLine());
                            String general = new JSONObject(jsonRootstat.getString("rea")).getString("date");
                            handler.post(new Runnable() {
                                @Override
                                public void run () {
                                    TextView date2;
                                    date2 = root.findViewById(R.id.textView7);
                                    date2.setText(date2.getText() + general + ": ");


                                    cas2 = root.findViewById(R.id.textView9);
                                    cas2.setText(cas2.getText() + general + ": ");


                                    cas2 = root.findViewById(R.id.textView12);
                                    cas2.setText(cas2.getText() + general + ": ");


                                    cas2 = root.findViewById(R.id.texthop);
                                    cas2.setText(cas2.getText() + general + ": ");


                                    cas2 = root.findViewById(R.id.textdc);
                                    cas2.setText(cas2.getText() + general + ": ");


                                    cas2 = root.findViewById(R.id.textR0);
                                    cas2.setText(cas2.getText() + general + ": ");


                                    cas2 = root.findViewById(R.id.textsthosp);
                                    cas2.setText(cas2.getText() + general + ": ");
                                    cas2 = root.findViewById(R.id.textView8);
                                    try {
                                        cas2.setText(new JSONObject(jsonRootstat.getString("taux_positivite")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.textView10);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("taux_incidence")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.textView11);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("rea")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.textView5);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("rea_new")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.nbhop);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("hosp")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.newnbhop);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("hosp_new")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.nbdc);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("incid_dc")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.R0);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("reffectif")).getString("valeur"));


                                        cas2 = root.findViewById(R.id.sthosp);
                                        cas2.setText(new JSONObject(jsonRootstat.getString("taux_saturation_rea")).getString("valeur") + "%");

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }}
                            });

                                WriteSettings(root.getContext(), general);




                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        Handler handler2 = new Handler();
         new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            JSONObject jsonRootvueensemble;
                            jsonRootvueensemble = new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/rozierguillaume/covid-19/master/data/france/stats/vue-ensemble.json").openStream())).readLine());

                            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                            symbols.setGroupingSeparator(' ');
                            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                            handler2.post(new Runnable() {
                                @Override
                                public void run () {
                                    try {
                                        cas1 = root.findViewById(R.id.textView4);
                                        cas1.setText(formatter.format(new BigDecimal(jsonRootvueensemble.getString("cas"))));
                                        cas1 = root.findViewById(R.id.textView2);
                                        cas1.setText(cas1.getText() + jsonRootvueensemble.getString("update") + ": ");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();




        return root;
    }

    public void WriteSettings(Context context , String val) throws IOException {

        try {
            FileOutputStream fOut = context.openFileOutput("test.txt",0);
            fOut.write(val.getBytes());
            fOut.close();
        }
        catch (Exception e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}