package com.example.covid.ui.slideshow;

import android.content.Context;
import android.graphics.Color;
import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.covid.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

public class SlideshowFragment extends Fragment {
    private TextView dose1;
    private TextView vacciné;
    private SlideshowViewModel slideshowViewModel;
    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                        X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                        X509Certificate[] certs, String authType) {
                }
            }
    };
    String total = "";
    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        Handler mHandler = new Handler();
        Handler mHandler2 = new Handler();
        try {
            dose1 = root.findViewById(R.id.textView8);
            vacciné = root.findViewById(R.id.textView10);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            new Thread(
                    new Runnable() {
                        public void run() {
                            try {
                                BufferedReader br = new BufferedReader(new InputStreamReader(((HttpsURLConnection) new URL("https://www.data.gouv.fr/fr/datasets/r/b8d4eb4c-d0ae-4af6-bb23-0e39f70262bd").openConnection()).getInputStream()));
                                String inputLine ;
                                while ((inputLine = br.readLine()) != null) {
                                    total = total + inputLine + '\n';
                                }

                            } catch (IOException  e) {
                                e.printStackTrace();
                            }

                            mHandler.post(new Runnable() {
                                @Override
                                public void run () {
                                    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                                    symbols.setGroupingSeparator(' ');
                                    DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                                    String[] val = total.split("\n")[5].split(";");
                                    dose1.setText(formatter.format(new BigDecimal(val[3])));
                                    vacciné.setText(formatter.format(new BigDecimal(val[4])));
                                    try {
                                        WriteSettings(root.getContext(), val[2]);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
            ).start();

            new Thread(
                    new Runnable() {
                        public void run() {
                            try {
                                String tot = "";
                                BufferedReader br = new BufferedReader(new InputStreamReader(((HttpsURLConnection) new URL("https://www.data.gouv.fr/fr/datasets/r/dc103057-d933-4e4b-bdbf-36d312af9ca9").openConnection()).getInputStream()));
                                String inputLine;
                                BarGraphSeries<DataPoint> point2 = null;
                                br.readLine();
                                DataPoint[] points = new DataPoint[14];
                                int i = 0;
                                while ((inputLine = br.readLine()) != null) {
                                    String[] val = inputLine.split(";");
                                    // tot = tot + inputLine + '\n';
                                    float t1 = Float.parseFloat(val[1]);
                                    float t2 = Float.parseFloat(val[6]);
                                    if (!val[1].equals("0")) {
                                        points[i] = new DataPoint(i, t2);
                                        ++i;

                                    }


                                }
                                BarGraphSeries<DataPoint> point = new BarGraphSeries<DataPoint>(points);

                                mHandler2.post(new Runnable() {
                                                   @Override
                                                   public void run() {
                                                       final GraphView graph = root.findViewById(R.id.graph3);
                                                       point.setDrawValuesOnTop(false);
                                                       point.setValuesOnTopColor(Color.RED);
                                                       point.setTitle("personne");
                                                       point.setSpacing(30);
                                                       graph.addSeries(point);
                                                       graph.getViewport().setYAxisBoundsManual(true);
                                                       graph.getViewport().setXAxisBoundsManual(true);
                                                       point.isAnimated();
                                                       graph.getViewport().setMinX(0);
                                                       graph.getViewport().setMinY(0);
                                                   }
                                               }
                                );

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();





            slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {

                }
            });

    } catch (NoSuchAlgorithmException | KeyManagementException e) {
        e.printStackTrace();
    };
        return root;
    }

    public void WriteSettings(Context context , String test) throws IOException {

        try {
            FileOutputStream fOut = context.openFileOutput("test.txt",0);
            fOut.write(test.getBytes());
            fOut.close();
        }
        catch (Exception e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}