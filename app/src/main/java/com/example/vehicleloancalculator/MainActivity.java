package com.example.vehicleloancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    // Declare all UI elements
    EditText vehiclePrice, downPayment, interestRate;
    RadioGroup radioGroupPeriod;
    Button btnCalculate, btnReset;
    TextView tvLoanAmount, tvTotalInterest, tvTotalPayment, tvMonthlyPayment;
    CardView cardResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Enable edge-to-edge display (status & nav bar can overlap content)

        setContentView(R.layout.activity_main);
        // Link this activity to XML layout

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vehicle Loan Calculator");
        // Set toolbar title

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorOnPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        // Customize toolbar colors

        // Link XML elements to Java variables
        vehiclePrice = findViewById(R.id.vehiclePrice);
        downPayment = findViewById(R.id.downPayment);
        interestRate = findViewById(R.id.interestRate);
        radioGroupPeriod = findViewById(R.id.radioGroupPeriod);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        cardResult = findViewById(R.id.cardResult);

        tvLoanAmount = findViewById(R.id.tvLoanAmount);
        tvTotalInterest = findViewById(R.id.tvTotalInterest);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);
        tvMonthlyPayment = findViewById(R.id.tvMonthlyPayment);

        // Set action for Calculate button
        btnCalculate.setOnClickListener(v -> calculateLoan());

        // Set action for Reset button
        btnReset.setOnClickListener(v -> resetAll());

        // Handle padding for system bars (status & nav bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // -------------------------------------------------------------------------------------------------------------
    //                                              CALCULATE LOAN FUNCTION
    // -------------------------------------------------------------------------------------------------------------
    private void calculateLoan() {

        // Validate input fields
        if (vehiclePrice.getText().toString().isEmpty() ||
                downPayment.getText().toString().isEmpty() ||
                interestRate.getText().toString().isEmpty() ||
                radioGroupPeriod.getCheckedRadioButtonId() == -1) {

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return; // Stop function if any field is empty
        }
        // Convert input text to numbers
        double price = Double.parseDouble(vehiclePrice.getText().toString());
        double downPay = Double.parseDouble(downPayment.getText().toString());
        double interest = Double.parseDouble(interestRate.getText().toString());

        // Get selected loan period
        int selectedId = radioGroupPeriod.getCheckedRadioButtonId();
        RadioButton selectedRadio = findViewById(selectedId);

        int loanYears = Integer.parseInt(selectedRadio.getText().toString().split(" ")[0]);
        // Get the number of years from RadioButton text (e.g., "5 Years" -> 5)

        // Loan calculation formulas
        double loanAmount = price - downPay;
        double totalInterest = loanAmount * (interest / 100) * loanYears;
        double totalPayment = loanAmount + totalInterest;
        double monthlyPayment = totalPayment / (loanYears * 12);

        // Show results card
        cardResult.setVisibility(View.VISIBLE);

        // Display results
        tvLoanAmount.setText("Loan Amount: RM" + String.format("%.2f", loanAmount));
        tvTotalInterest.setText("Total Interest: RM" + String.format("%.2f", totalInterest));
        tvTotalPayment.setText("Total Payment: RM" + String.format("%.2f", totalPayment));
        tvMonthlyPayment.setText("Monthly Payment: RM" + String.format("%.2f", monthlyPayment));
    }
    // -------------------------------------------------------------------------------------------------------------
    //                                               RESET FUNCTION
    // -------------------------------------------------------------------------------------------------------------
    private void resetAll() {
        // Clear all input fields
        vehiclePrice.setText("");
        downPayment.setText("");
        interestRate.setText("");
        radioGroupPeriod.clearCheck();

        // Hide result card
        cardResult.setVisibility(View.GONE);

        // Reset result text
        tvLoanAmount.setText("Loan Amount: -");
        tvTotalInterest.setText("Total Interest: -");
        tvTotalPayment.setText("Total Payment: -");
        tvMonthlyPayment.setText("Monthly Payment: -");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Inflate menu (three dots) on toolbar
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();  // Get clicked menu item ID

        if (itemId == R.id.action_home) {
            // Go back to Welcome screen
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity so back button won't return here
            return true;
        }
        else if (itemId == R.id.action_about) {
            // Open About screen
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.action_share) {
            // Open share intent
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}

