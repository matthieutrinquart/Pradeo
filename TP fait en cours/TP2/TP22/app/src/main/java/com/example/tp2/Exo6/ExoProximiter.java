package com.example.tp2.Exo6;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tp2.Menu.MainActivity;
import com.example.tp2.R;

public class ExoProximiter extends AppCompatActivity implements SensorEventListener {
    private SoundPool soundPool;
    private int soundIdnotif;
    private boolean musique;

    private ImageView image;

    private AudioManager audioManager;
    private float volume;
    private Sensor proximiter;
    private int MAX_STREAMS = 5;

    private int streamType = AudioManager.STREAM_MUSIC;
    private SensorManager mSensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_proximiter);
        musique = true;
        image = (ImageView) findViewById(R.id.imageView5);
        image.setImageResource(R.drawable.mute);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] ==0) {
            if(musique) {
                image.setImageResource(R.drawable.sond);
                float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);
                float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);
                this.volume = currentVolumeIndex / maxVolumeIndex;
                this.soundPool.play(this.soundIdnotif, this.volume, this.volume, 1, 0, 1f);
                musique = false;
            }
        } else {
            musique = true;
            image.setImageResource(R.drawable.mute);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this, proximiter);
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) !=null){
            proximiter = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            mSensorManager.registerListener(this, proximiter, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
        } else {
            Toast.makeText(this,"Il manque le capteur de proximité", Toast.LENGTH_LONG).show();
        }
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        this.setVolumeControlStream(streamType);
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        else
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        this.soundIdnotif = this.soundPool.load(this, R.raw.sond,1);
    }


    public void RetourExo6(View view){
        Intent intention= new Intent(ExoProximiter.this, MainActivity.class);
        startActivity(intention);
    }

}