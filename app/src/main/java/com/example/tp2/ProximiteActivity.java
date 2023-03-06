package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProximiteActivity extends AppCompatActivity implements SensorEventListener {
Sensor proximite;
SensorManager sensorMng;
boolean supported;
boolean estProche=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximite);

        sensorMng=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        proximite=sensorMng.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        supported=sensorMng.registerListener(this, proximite, sensorMng.SENSOR_DELAY_UI);
        if (!supported){
            sensorMng.unregisterListener(this,proximite);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float distance = sensorEvent.values[0];
            if (distance < proximite.getMaximumRange()) {

                estProche = true;
            } else {
                estProche = false;
            }
            updateUI();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private void updateUI() {
        ImageView objectImage = findViewById(R.id.objet_image);
        TextView proximityText = findViewById(R.id.proximite_text);

        if (estProche) {
            objectImage.setImageResource(R.drawable.iconeproche);
            proximityText.setText(R.string.objet_proche);
        } else {
            objectImage.setImageResource(R.drawable.iconeloin);
            proximityText.setText(R.string.objet_loin);
        }
    }

}