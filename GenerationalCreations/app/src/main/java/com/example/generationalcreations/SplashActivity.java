package com.example.generationalcreations;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SplashActivity presents the initial app brand logo loading screen.
 * Automatically transitions to LoginActivity after a 2-second loading timer.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge presentation
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Post delayed handler to navigate to LoginActivity after 2 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close SplashActivity so back button exits app
        }, 2000);
    }
}