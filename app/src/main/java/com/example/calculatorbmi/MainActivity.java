package com.example.calculatorbmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText etWeightView;
    private EditText etHeightView;
    private TextView tvResultNumberView;
    private TextView tvResultTextView;
    private TextView tvResultRangeView;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiating the created objects
        etWeightView = findViewById(R.id.etWeight);
        etHeightView = findViewById(R.id.etHeight);
        tvResultNumberView = findViewById(R.id.tvResultNumber);
        tvResultTextView = findViewById(R.id.tvResultText);
        tvResultRangeView = findViewById(R.id.tvResultRange);
        Button btnCalculateView = findViewById(R.id.btnCalculate);
        decimalFormat = new DecimalFormat("###.##");

        // When the user clicks the button
        btnCalculateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the hasValue function returns true
                if (hasValue(etWeightView.getText().toString(), etHeightView.getText().toString())) {
                    btnCalculate();
                }
            }
        });
    }

    private boolean hasValue(String weight, String height) {
        if (weight == null || weight.isEmpty()) {
            Toast.makeText(this, "Please enter your weight", Toast.LENGTH_LONG).show();
            return false;
        } else if (height == null || height.isEmpty()) {
            Toast.makeText(this, "Please enter your height", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void btnCalculate() {
        double etWeightValue = Double.parseDouble(etWeightView.getText().toString());
        double etHeightValue = Double.parseDouble(etHeightView.getText().toString());

        // Getting the height^2
        double convertedHeightValue = Math.pow((etHeightValue * 0.01), 2.0);

        // BMI formula
        double bmiValue = etWeightValue / convertedHeightValue;

        // Making the answer in two decimal places
        tvResultNumberView.setText(decimalFormat.format(bmiValue));

        if (bmiValue < 18.5) {
            tvResultTextView.setText("Underweight");
            tvResultTextView.setTextColor(ContextCompat.getColor(this, R.color.underweight));
            tvResultRangeView.setText("(underweight range is less than or equal to 18.5)");
        } else if (bmiValue >= 18.5 && bmiValue <= 24.99) {
            tvResultTextView.setText("Healthy");
            tvResultTextView.setTextColor(ContextCompat.getColor(this, R.color.healthy));
            tvResultRangeView.setText("(normal range is between 18.5 to 25)");
        } else if (bmiValue >= 25.0 && bmiValue <= 29.99) {
            tvResultTextView.setText("Overweight");
            tvResultTextView.setTextColor(ContextCompat.getColor(this, R.color.overweight));
            tvResultRangeView.setText("(overweight range is between 25 to 30)");
        } else {
            tvResultTextView.setText("Obese");
            tvResultTextView.setTextColor(ContextCompat.getColor(this, R.color.obese));
            tvResultRangeView.setText("(obese range is greater than 30)");
        }
    }
}