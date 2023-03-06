package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
Button bexo1,bexo2,bexo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bexo1=(Button)findViewById(R.id.exo1);
        bexo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(MainActivity.this,ListCapteurActivity.class);
                startActivity(it);
            }
        });

        bexo2=(Button)findViewById(R.id.exo2);
        bexo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(MainActivity.this,DetectionCapteurActivity.class);
                startActivity(it);
            }
        });

      bexo3=(Button)findViewById(R.id.exo3);
        bexo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(MainActivity.this,AccelerometreActivity.class);
                startActivity(it);


            }
        });

    }
}