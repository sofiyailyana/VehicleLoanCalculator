package com.example.vehicleloancalculator;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;


public class WelcomeActivity extends AppCompatActivity {
// This activity represents the welcome screen of your app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // onCreate() is called when the activity is first created
        super.onCreate(savedInstanceState);
        // Calls the parent class to set up the activity state
        setContentView(R.layout.activity_welcome);
        // Link this activity to its XML layout file

        Button btnCalc = findViewById(R.id.btnCalculator);
        // Find the Calculator button from layout so we can use it
        Button btnAbout = findViewById(R.id.btnAbout);
        // Find the About button from layout

        btnCalc.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class))
        );
        // When Calculator button is clicked -> open MainActivity (calculator screen)

        btnAbout.setOnClickListener(v ->
                startActivity(new Intent(this, AboutActivity.class))
        );
        // When About button is clicked -> open AboutActivity (info screen)
    }
}