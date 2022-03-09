package com.example.covid.ui.slideshow;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Vaccin {

    JSONObject jsonRootvueensemble;
    JSONObject jsonRootstat;
    public Vaccin(Context context) throws IOException, JSONException, NoSuchAlgorithmException, KeyManagementException {
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
                    BufferedReader br = new BufferedReader(new InputStreamReader(((HttpsURLConnection) new URL("https://www.data.gouv.fr/fr/datasets/r/b8d4eb4c-d0ae-4af6-bb23-0e39f70262bd").openConnection()).getInputStream()));
                    String inputLine ;
                    String total = "";

                    while ((inputLine = br.readLine()) != null) {
                        total = total + inputLine + '\n';
                    }
                    System.out.println(total);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }


}
