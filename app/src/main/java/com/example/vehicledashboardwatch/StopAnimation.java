package com.example.vehicledashboardwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

import maes.tech.intentanim.CustomIntent;

public class StopAnimation extends AppCompatActivity {
    int finishTime = 3; //5 secs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_animation);
        final LottieAnimationView lottieAnimationView = findViewById(R.id.stopanimation);
        lottieAnimationView.setMinAndMaxProgress(0.3f, 1f);
        lottieAnimationView.setSpeed(0.5f);
        lottieAnimationView.playAnimation();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StopAnimation.this.finish();
                CustomIntent.customType(StopAnimation.this, "fadein-to-fadeout");

            }
        }, finishTime * 1000);
    }
}