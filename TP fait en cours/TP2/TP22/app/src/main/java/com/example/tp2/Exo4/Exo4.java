package com.example.tp2.Exo4;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tp2.Menu.MainActivity;
import com.example.tp2.R;


public class Exo4 extends AppCompatActivity implements SensorEventListener {
    private Sensor accelerometre;
    private Sensor linearAcc;
    private SensorManager mSensorManager;

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo4);
        image  = findViewById(R.id.imageView);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        

    if(Math.abs(x) + Math.abs(y) > 10) {
        if (y > 0 && x >= 0)
            image.setRotation((float) Math.toDegrees(Math.atan(x / y) - Math.PI));
        else if (y > 0 && x < 0)
            image.setRotation((float) Math.toDegrees(Math.atan(x / y) + Math.PI));
        else if (y < 0)
            image.setRotation((float) Math.toDegrees(Math.atan(x / y)));
        else if (y == 0 && x > 0)
            image.setRotation((float) Math.toDegrees(Math.PI / 2));
        else if (y == 0 && x < 0)
            image.setRotation((float) Math.toDegrees((3 * Math.PI) / 2));
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
/*
*/
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) !=null){
            linearAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            mSensorManager.registerListener(this, linearAcc, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
        }
        else {
            Toast.makeText(this,"Il vous manque le linéaire acceleromètre", Toast.LENGTH_LONG).show();

            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) !=null){
                accelerometre = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mSensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
            }
            else {
                Toast.makeText(this,"Il vous manque l'acceleromètre", Toast.LENGTH_LONG).show();
            }
        }

    }
    public void RetourExo4(View view){
        Intent intention= new Intent(Exo4.this, MainActivity.class);
        startActivity(intention);
    }
}