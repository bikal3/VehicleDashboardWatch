package com.example.vehicledashboardwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

import maes.tech.intentanim.CustomIntent;

public class StartAnimation extends AppCompatActivity {
    int finishTime = 3; //5 secs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        final LottieAnimationView lottieAnimationView = findViewById(R.id.animation);
        lottieAnimationView.setMinAndMaxProgress(0.5f, 0.8f);
        lottieAnimationView.setSpeed(0.5f);
        lottieAnimationView.playAnimation();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StartAnimation.this.finish();
                CustomIntent.customType(StartAnimation.this, "fadein-to-fadeout");

            }
        }, finishTime * 1000);
    }
}