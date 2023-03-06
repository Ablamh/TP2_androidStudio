package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.RelativeLayout;


public class AccelerometreActivity extends AppCompatActivity implements  SensorEventListener{
private SensorManager sensorMgr;
private Sensor accelerometer;
private Boolean supported;
private float[] gravity = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre);
        sensorMgr =(SensorManager)getSystemService(Context.SENSOR_SERVICE) ;

        accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
          supported=sensorMgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        if (!supported){
            sensorMgr.unregisterListener(this,accelerometer);

        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
 //recuper les donneesde l acc a partir de l objet sensorEvent et mettre a jour les 3 objets textView
        final float alpha = 0.8f;
        gravity[0] = alpha * gravity[0] + (1 - alpha) * sensorEvent.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * sensorEvent.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * sensorEvent.values[2];

        final float maxValue = Math.max(Math.max(gravity[0], gravity[1]), gravity[2]);

        RelativeLayout background = findViewById(R.id.background);
        TextView values = findViewById(R.id.values);

        if (maxValue < 5.0) {
            background.setBackgroundColor(Color.GREEN);
            values.setTextColor(Color.BLACK);
        } else if (maxValue < 10.0) {
            background.setBackgroundColor(Color.BLACK);
            values.setTextColor(Color.WHITE);
        } else {
            background.setBackgroundColor(Color.RED);
            values.setTextColor(Color.WHITE);
        }

        values.setText(String.format("X: %.2f Y: %.2f Z: %.2f", sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]));
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {//Appelée quand la précision du capteur change
    // rien a faire la plupart du temps

    }
}