package com.example.vehicleloancalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class AboutActivity extends AppCompatActivity {
    TextView tvGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        // Link this activity to activity_about XML layout

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set toolbar as action bar

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorOnPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        // Set toolbar text color and background color

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Show back button on toolbar
        }
        // GITHUB CLICKABLE LINK
        tvGithub = findViewById(R.id.githubLink);
        tvGithub.setOnClickListener(v -> {
            String url = "https://github.com/sofiyailyana/VehicleLoanCalculator.git";
            // Create intent to open the GitHub link in browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
    // ENABLE BACK BUTTON
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Close this activity when back button clicked
        return true;
    }
}

