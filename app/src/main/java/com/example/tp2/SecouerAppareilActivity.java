package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
;
import android.hardware.Camera;
import android.os.Bundle;

public class SecouerAppareilActivity extends AppCompatActivity implements SensorEventListener {
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    private OnShakeListener mShakeListener;
    private long mShakeTimestamp;
    private int mShakeCount;

    SensorManager sensorMng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secouer_appareil);


    }
    //interface pour ecouter les secousses
   public interface OnShakeListener{
        void onShake();
   }
    public void setOnShakeListener(OnShakeListener listener) {
       mShakeListener = listener;
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


            if (mShakeListener != null) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                // Calcul de la force de la secousse
                float gX = x / SensorManager.GRAVITY_EARTH;
                float gY = y / SensorManager.GRAVITY_EARTH;
                float gZ = z / SensorManager.GRAVITY_EARTH;
                float gForce = (float)Math.sqrt(gX * gX + gY * gY + gZ * gZ);

                // Vérification du seuil de secousse
                if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                    final long now = System.currentTimeMillis();
                    // Ignorer les secousses trop rapprochées dans le temps
                    if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                        return;
                    }
                    // Réinitialiser le compteur de secousses si c'est la première secousse ou si le temps écoulé est supérieur au seuil de réinitialisation
                    if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                        mShakeCount = 0;
                    }
                    mShakeTimestamp = now;
                    mShakeCount++;
                    // Appeler la méthode onShake de l'interface OnShakeListener
                    mShakeListener.onShake();
                }
            }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}