package com.example.covid.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.covid.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class GalleryFragment extends Fragment {

    private TextView Tauxinc;
    private TextView occhop;
    private TextView R0;
    private TextView pos;
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Handler mHandler = new Handler();
        try {
        if(!new File("/data/user/0/com.example.covid/files/data.csv").exists()){
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

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        String httpsURL = "https://www.data.gouv.fr/fr/datasets/r/381a9472-ce83-407d-9a64-1b8c23af83df";
                        URL myUrl = null;
                        myUrl = new URL(httpsURL);
                        HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
                        InputStream is = conn.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String inputLine ;
                        String total = "";

                        while ((inputLine = br.readLine()) != null) {
                            total = total + inputLine + '\n';
                        }
                        total = total + inputLine + '\n';
                        WriteSettings(root.getContext(), total);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }


        new Thread(new Runnable(){
            @Override
            public void run () {


                mHandler.post(new Runnable() {
                    @Override
                    public void run () {
                        try{
                            FileInputStream fin = root.getContext().openFileInput("data.csv");
                            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                            LineGraphSeries<DataPoint> point = new LineGraphSeries<>();
                            LineGraphSeries<DataPoint> point2 = new LineGraphSeries<>();
                            LineGraphSeries<DataPoint> point3 = new LineGraphSeries<>();
                            LineGraphSeries<DataPoint> point4 = new LineGraphSeries<>();
                            String dern2 = null;
                            String dern3 = null;
                            String dern4 = null;
                            String dern5 = null;
                            String csvLine;
                            String[] val;
                            String date;
                            reader.readLine();
                            while (!(csvLine = reader.readLine()).equals("null")) {
                                val = csvLine.split(",");
                                date = val[0].replace("\"", "");
                                if (!val[1].equals("NA")) {
                                    point.appendData(new DataPoint(new SimpleDateFormat("yyyy-MM-dd").parse(date), Double.parseDouble(val[1])), true, 500);
                                    dern2 = csvLine;
                                }

                                if (!val[2].equals("NA")) {
                                    point3.appendData(new DataPoint(new SimpleDateFormat("yyyy-MM-dd").parse(date), Double.parseDouble(val[2])), true, 500);
                                    dern3 = csvLine;
                                }
                                if (!val[3].equals("NA")) {
                                    point2.appendData(new DataPoint(new SimpleDateFormat("yyyy-MM-dd").parse(date), Double.parseDouble(val[3])), true, 500);
                                    dern4 = csvLine;
                                }
                                if (!val[4].equals("NA")) {
                                    point4.appendData(new DataPoint(new SimpleDateFormat("yyyy-MM-dd").parse(date), Double.parseDouble(val[4])), true, 500);
                                    dern5 = csvLine;
                                }
                            }
                            fin.close();


                        Tauxinc = root.findViewById(R.id.textView7);
                        occhop = root.findViewById(R.id.textView8);
                        R0 = root.findViewById(R.id.textView9);
                        pos = root.findViewById(R.id.textView10);
                        Tauxinc.setText(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(dern2.split(",")[0].replace("\"", "")))+ '\n' + Math.round(Double.parseDouble(dern2.split(",")[1])*10.0)/10.0);
                        occhop.setText(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(dern4.split(",")[0].replace("\"", "")))+ '\n' + Math.round(Double.parseDouble(dern4.split(",")[3])*10.0)/10.0);
                        R0.setText(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(dern3.split(",")[0].replace("\"", "")))+ '\n' + Math.round(Double.parseDouble(dern3.split(",")[2])*10.0)/10.0);
                        pos.setText(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(dern5.split(",")[0].replace("\"", "")))+ '\n' + Math.round(Double.parseDouble(dern5.split(",")[4])*10.0)/10.0);

                        final GraphView graph = root.findViewById(R.id.graph);
                        point.setAnimated(true);
                        point.setThickness(15);
                        graph.addSeries(point);
                        Tauxinc.setTextColor(point.getColor());
                        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
                        graph.getViewport().setYAxisBoundsManual(true);
                        graph.getGridLabelRenderer().setNumHorizontalLabels(10); // only 4 because of the space
                        graph.getViewport().setMinX(point.getLowestValueX());
                        graph.getViewport().setMaxX(point.getHighestValueX());
                        graph.getViewport().setXAxisBoundsManual(true);
                        graph.getViewport().setYAxisBoundsManual(true);
                        graph.getGridLabelRenderer().setHumanRounding(false, true);
                        graph.getGridLabelRenderer().setHorizontalLabelsAngle(90);
                        graph.getViewport().setScalable(true);
                        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
                        graph.getViewport().scrollToEnd();
                        graph.getViewport().setScrollable(true);


                        final GraphView graph2 =root.findViewById(R.id.graph2);

                        point2.setAnimated(true);
                        point2.setThickness(15);
                        point2.setColor(Color.parseColor("#e21313"));
                        graph2.addSeries(point2);
                        occhop.setTextColor(point2.getColor());
                        graph2.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
                        graph2.getViewport().setYAxisBoundsManual(true);
                        graph2.getGridLabelRenderer().setNumHorizontalLabels(10); // only 4 because of the space
                        graph2.getViewport().setMinX(point2.getLowestValueX());
                        graph2.getViewport().setMaxX(point2.getHighestValueX());
                        graph2.getViewport().setXAxisBoundsManual(true);
                        graph2.getViewport().setYAxisBoundsManual(true);
                        graph2.getGridLabelRenderer().setHumanRounding(false, true);
                        graph2.getGridLabelRenderer().setHorizontalLabelsAngle(90);
                        graph2.getViewport().setScalable(true);
                        graph2.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
                        graph2.getViewport().scrollToEnd();
                        graph2.getViewport().setScrollable(true);



                        final GraphView graph3 =root.findViewById(R.id.graph3);

                        point3.setAnimated(true);
                        point3.setThickness(15);
                        point3.setColor(Color.parseColor("#11c12c"));
                        graph3.addSeries(point3);
                        R0.setTextColor(point3.getColor());
                        graph3.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
                        graph3.getViewport().setYAxisBoundsManual(true);
                        graph3.getGridLabelRenderer().setNumHorizontalLabels(10); // only 4 because of the space
                        graph3.getViewport().setMinX(point3.getLowestValueX());
                        graph3.getViewport().setMaxX(point3.getHighestValueX());
                        graph3.getViewport().setXAxisBoundsManual(true);
                        graph3.getViewport().setYAxisBoundsManual(true);
                        graph3.getGridLabelRenderer().setHumanRounding(false, true);
                        graph3.getGridLabelRenderer().setHorizontalLabelsAngle(90);
                        graph3.getViewport().setScalable(true);
                        graph3.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
                        graph3.getViewport().scrollToEnd();
                        graph3.getViewport().setScrollable(true);

                        final GraphView graph4 =  root.findViewById(R.id.graph4);

                        point4.setAnimated(true);
                        point4.setThickness(15);
                        point4.setColor(Color.parseColor("#D4FF33"));
                        graph4.addSeries(point4);
                        pos.setTextColor(point4.getColor());
                        graph4.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
                        graph4.getViewport().setYAxisBoundsManual(true);
                        graph4.getGridLabelRenderer().setNumHorizontalLabels(10); // only 4 because of the space
                        graph4.getViewport().setMinX(point4.getLowestValueX());
                        graph4.getViewport().setMaxX(point4.getHighestValueX());
                        graph4.getViewport().setXAxisBoundsManual(true);
                        graph4.getViewport().setYAxisBoundsManual(true);
                        graph4.getGridLabelRenderer().setHumanRounding(false, true);
                        graph4.getGridLabelRenderer().setHorizontalLabelsAngle(90);
                        graph4.getViewport().setScalable(true);
                        graph4.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
                        graph4.getViewport().scrollToEnd();
                        graph4.getViewport().setScrollable(true);

                            point2.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    occhop.setText(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date((long) dataPoint.getX()).getTime()) + '\n' + Math.round(dataPoint.getY()*10.0)/10.0);

                                }
                            });

                            point4.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    pos.setText(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date((long) dataPoint.getX()).getTime()) + '\n' + Math.round(dataPoint.getY()*10.0)/10.0);

                                }
                            });
                            point3.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    R0.setText(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date((long) dataPoint.getX()).getTime()) + '\n' + Math.round(dataPoint.getY()*10.0)/10.0);

                                }
                            });
                            point.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint){
                                    Tauxinc.setText(new SimpleDateFormat("dd/MM/yyyy").format(new java.sql.Date((long) dataPoint.getX()).getTime()) + '\n' + Math.round(dataPoint.getY()*10.0)/10.0);
                                }
                            });

                            if (!(new SimpleDateFormat("dd/MM").format(Objects.requireNonNull(new SimpleDateFormat("yyyy-MM-dd").parse(
                                    dern4.split(",")[0].replace("\"", "")))).equals(
                                    lectureFichierfichier(root.getContext())))) {


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

    // Activate the new trust manager
                                SSLContext sc = SSLContext.getInstance("SSL");
                                sc.init(null, trustAllCerts, new SecureRandom());
                                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                                Thread thread = new Thread(new Runnable() {

                                    @Override
                                    public void run() {
                                        try {
                                            String httpsURL = "https://www.data.gouv.fr/fr/datasets/r/381a9472-ce83-407d-9a64-1b8c23af83df";
                                            URL myUrl = null;
                                            myUrl = new URL(httpsURL);
                                            HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
                                            InputStream is = conn.getInputStream();
                                            InputStreamReader isr = new InputStreamReader(is);
                                            BufferedReader br = new BufferedReader(isr);
                                            String inputLine ;
                                            String total = "";

                                            while ((inputLine = br.readLine()) != null) {
                                                total = total + inputLine + '\n';
                                            }
                                            total = total + inputLine + '\n';
                                            WriteSettings(root.getContext(), total);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                thread.start();


                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (KeyManagementException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        }).start();
        } catch ( NoSuchAlgorithmException | KeyManagementException | InterruptedException e) {
            e.printStackTrace();
        };

        return root;

    }

    public void WriteSettings(Context context, String data) throws IOException {

        try {
            FileOutputStream fOut = context.openFileOutput("data.csv",0);
            fOut.write(data.getBytes());
            fOut.close();
        }
        catch (Exception e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String lectureFichierfichier(Context context) throws IOException {

        InputStream input;
        String text = "";

        try {

            input=context.openFileInput("test.txt");
            int size=input.available();
            byte[] buffer=new byte[size];
            input.read(buffer);
            input.close();
            text=new String(buffer);
        } catch (Exception e){
        }
        return text;

    }
}