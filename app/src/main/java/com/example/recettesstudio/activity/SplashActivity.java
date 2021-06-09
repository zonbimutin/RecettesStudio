package com.example.recettesstudio.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recettesstudio.R;
//import com.example.recettesstudio.dao.AppDatabase;
import com.example.recettesstudio.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                AppDatabase.getDatabase(SplashActivity.this);
                ActivityUtils.launchActivity(SplashActivity.this, MainActivity.class);
            }
        }, 2000);
    }
}