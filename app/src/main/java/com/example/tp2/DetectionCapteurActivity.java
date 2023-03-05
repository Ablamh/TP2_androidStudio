package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class DetectionCapteurActivity extends AppCompatActivity {
private SensorManager mSensorManager;
private Sensor mSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_capteur);
        mSensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE) ;
        //DETECTION L'ABSENCCE OU LA PRESENCE DE CAPTEUR TYPE GRAVITY
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null){
            List<Sensor> gravSencors = mSensorManager.getSensorList(Sensor.TYPE_GRAVITY);
            for (int i=0; i<gravSencors.size();i++){
                if ((gravSencors.get(i).getVendor().contains("Google Inc."))&&
                        (gravSencors.get(i).getVersion()==3)){
                    mSensor=gravSencors.get(i);


                }else {
                    Toast.makeText(this, " Le capteur de gravité dont le fabricant est Google Inc. et la version est 3 n'est pas disponible", Toast.LENGTH_LONG).show();
                }
            }


        }


    }
}