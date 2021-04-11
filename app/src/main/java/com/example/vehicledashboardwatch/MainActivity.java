package com.example.vehicledashboardwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends WearableActivity implements View.OnClickListener {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ImageView carImage;
    Integer value = 95;
    Button btnStart, btnStop;
    boolean isCheckDone = false;
    int finishTime = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        setAmbientEnabled();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        carImage = findViewById(R.id.carImage);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        setImage();
    }

    public void setImage() {
        if (value == 0 && value < 10) {
            carImage.setImageResource(R.drawable.carbattery0);
        } else if (value >= 10 && value < 20) {
            carImage.setImageResource(R.drawable.carbattery10);
        } else if (value >= 20 && value < 30) {
            carImage.setImageResource(R.drawable.carbattery20);
        } else if (value >= 30 && value < 40) {
            carImage.setImageResource(R.drawable.carbattery30);
        } else if (value >= 40 && value < 50) {
            carImage.setImageResource(R.drawable.carbattery40);
        } else if (value >= 50 && value < 60) {
            carImage.setImageResource(R.drawable.carbattery50);
        } else if (value >= 60 && value < 70) {
            carImage.setImageResource(R.drawable.carbattery60);
        } else if (value >= 70 && value < 80) {
            carImage.setImageResource(R.drawable.carbattery70);
        } else if (value >= 80 && value < 90) {
            carImage.setImageResource(R.drawable.carbattery80);
        } else if (value >= 90 && value < 100) {
            carImage.setImageResource(R.drawable.carbattery90);
        } else if (value == 100) {
            carImage.setImageResource(R.drawable.carbattery90);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                Intent intent = new Intent(this, StartAnimation.class);
                startActivity(intent);
                CustomIntent.customType(this, "fadein-to-fadeout");

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnStart.setBackground(getResources().getDrawable(R.drawable.round_green_button));
                        btnStart.setTextColor(getResources().getColor(R.color.green));
                        btnStop.setBackground(getResources().getDrawable(R.drawable.round_button));
                        btnStop.setTextColor(getResources().getColor(R.color.white));
                        btnStart.setEnabled(false);
                        btnStop.setEnabled(true);
                        sendMessage("Start Car");
                    }
                }, finishTime * 1000);
                break;
            case R.id.btnStop:
                Intent it = new Intent(this, StopAnimation.class);
                startActivity(it);
                CustomIntent.customType(this, "fadein-to-fadeout");

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnStop.setBackground(getResources().getDrawable(R.drawable.round_red_button));
                        btnStop.setTextColor(getResources().getColor(R.color.dark_red));
                        btnStart.setBackground(getResources().getDrawable(R.drawable.round_button));
                        btnStart.setTextColor(getResources().getColor(R.color.white));
                        btnStart.setEnabled(true);
                        btnStop.setEnabled(false);
                        sendMessage("Stop Car");
                    }
                }, finishTime * 1000);
        }
    }
    public void sendMessage(String value){
        myRef.setValue(value);
    }
}