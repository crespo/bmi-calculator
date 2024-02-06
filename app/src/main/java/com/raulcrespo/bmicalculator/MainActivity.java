package com.raulcrespo.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }


    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        heightEditText = findViewById(R.id.edit_text_height);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();
                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBmi() {
        String heightText = heightEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        double height = Double.parseDouble(heightText);
        double weight = Double.parseDouble(weightText);

        return (weight / height / height) * 10000;
    }

    private void displayResult(double bmiResult) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String formattedBmi = numberFormat.format(bmiResult);
        String fullResult;

        if (bmiResult < 18.5) {
            fullResult = formattedBmi + " - you're underweight.";
        } else if (bmiResult > 25) {
            fullResult = formattedBmi + " - you're overweight.";
        } else {
            fullResult = formattedBmi + " - you're at a healthy weight.";
        }

        resultText.setText(fullResult);
    }

    private void displayGuidance(double bmiResult) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String formattedBmi = numberFormat.format(bmiResult);
        String fullResult;

        if (maleButton.isChecked()) {
            // Display boy guidance
            fullResult = formattedBmi + " - As you're under 18, please consult with your doctor for the healthy range for boys.";
        } else if (femaleButton.isChecked()) {
            // Display girl guidance
            fullResult = formattedBmi + " - As you're under 18, please consult with your doctor for the healthy range for girls.";
        } else {
            // Display general guidance
            fullResult = formattedBmi + " - As you're under 18, please consult with your doctor for the healthy range.";
        }

        resultText.setText(fullResult);
    }

}
