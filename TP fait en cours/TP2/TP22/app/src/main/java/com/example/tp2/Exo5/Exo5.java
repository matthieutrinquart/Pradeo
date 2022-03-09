package com.example.tp2.Exo5;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tp2.Menu.MainActivity;
import com.example.tp2.R;

public class Exo5 extends AppCompatActivity implements SensorEventListener{
    private int m ;
    private boolean tampon;
    private float tab[];

    private Sensor accelerometre;
    private Sensor linearAcc;

    private SensorManager mSensorManager;
    private ImageView image;
    private CameraManager camManager;
    private String cameraId;
    boolean lampe;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo5);
        m = 0;
        tampon = true;
        tab = new float[10];
        for(int i = 0 ; i < 10 ; ++i)
            tab[i] = 10000;
        image = (ImageView)findViewById(R.id.imageView2) ;
        image.setImageResource(R.drawable.eteindre);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        tab[m%10] = Math.abs(x)+Math.abs(y) +Math.abs(z);
        m++;
        float somme = 0;
        for(int i = 0 ; i < 10 ; ++i)
            if(tab[i] != 10000)
                somme = tab[i] + somme;
        float moyenne  = somme/10;
        if(moyenne > 20){
            if(tampon) {
                lampe = !lampe;
                tampon = false;
                if(lampe)
                    image.setImageResource(R.drawable.allumer);
                else
                    image.setImageResource(R.drawable.eteindre);
            }
        }else
            tampon = true;
        try {
            camManager.setTorchMode(cameraId, lampe);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this, accelerometre);
        mSensorManager.unregisterListener(this, linearAcc);
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) !=null){
            linearAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            mSensorManager.registerListener(this, linearAcc, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this,"Il vous manque l'acceleromètre", Toast.LENGTH_LONG).show();
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) !=null){
                accelerometre = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mSensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_UI);
            }
            else {
                Toast.makeText(this,"Il vous manque l'acceleromètre", Toast.LENGTH_LONG).show();
            }
        }

        camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = camManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            Toast.makeText(this,"Problème d'accès au flash", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void RetourExo5(View view){
        Intent intention= new Intent(Exo5.this, MainActivity.class);
        startActivity(intention);
    }
}