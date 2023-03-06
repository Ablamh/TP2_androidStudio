package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class DirectionActivity extends AppCompatActivity implements SensorEventListener {
 Sensor accelerometer;
 SensorManager sensorMng;
 boolean supported;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        sensorMng=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorMng.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        supported=sensorMng.registerListener(this,accelerometer,sensorMng.SENSOR_DELAY_UI);
        if (!supported){
            sensorMng.unregisterListener(this,accelerometer);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Mesurez le changement d'accélération par rapport à la dernière mesure
        float deltaX = sensorEvent.values[0];
        float deltaY = sensorEvent.values[1];

        // Déterminez la direction du mouvement en fonction du changement d'accélération
        String directionX = deltaX > 2 ? " la direction de telephone est Droite" : deltaX < -2 ? "la direction de telephone est Gauche" : "";
        String directionY = deltaY > 2 ? "la direction de telephone est En haut" : deltaY < -2 ? "la direction de telephone estEn bas " : "";

        // Mettez à jour les TextViews pour afficher la direction du mouvement
        TextView directionXTextView = findViewById(R.id.direction_x);
        directionXTextView.setText(directionX);

        TextView directionYTextView = findViewById(R.id.direction_y);
        directionYTextView.setText(directionY);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}