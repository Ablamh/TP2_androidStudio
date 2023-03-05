package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ListCapteurActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_capteur);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);  // instancier le manger
        listSensor();
    }

    private void listSensor(){

        List<Sensor> sensorList= sensorManager.getSensorList(Sensor.TYPE_ALL); // liste des capteurs, trouver tt les capteurs de l appareil
        StringBuffer sensorDesc = new StringBuffer();  // le buffer de description
        for (Sensor sensor : sensorList){ // afficher le buffer de descriptionn  de chaque capteur
            sensorDesc.append("New sensor detected: \r\n");
            sensorDesc.append("\tType:" + sensor.getName() +"\r\n"); sensorDesc.append("\tType: " + sensor.getType() + "\r\n");
            sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");
            sensorDesc.append("Resolution (in the sensor unit): " +
                    sensor.getResolution() + "\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use" +
                    sensor.getPower() +"\r\n");
            sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit." +
                    sensor.getMaximumRange() + "\r\n");
            sensorDesc.append("Minimum delay allowed between two events in microsecond"+"or zero if the sensor only returns a value when the data it#s measuring changes "+ sensor.getMinDelay()+ "\r\n");

        }
        Toast.makeText(this,sensorDesc.toString(), Toast.LENGTH_LONG).show();


    }
}