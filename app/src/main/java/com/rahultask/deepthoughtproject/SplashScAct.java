package com.rahultask.deepthoughtproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sc);

        new Animations().scaleAnimView(findViewById(R.id.splLogImv));
        new Animations().scaleAnimView(findViewById(R.id.splScName0));
        new Animations().scaleAnimView(findViewById(R.id.splScName1));

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScAct.this, MainActivity.class));
            finish();
        }, 2500);
    }
}