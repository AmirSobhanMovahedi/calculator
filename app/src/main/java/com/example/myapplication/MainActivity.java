package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private String currentResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        setupButtons();
    }

    private void setupButtons() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8,
                R.id.button9, R.id.button_plus, R.id.button_minus,
                R.id.button_multiply, R.id.button_divide, R.id.button_equals,
                R.id.buttonclear // دکمه پاک کردن را اضافه کنید
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "=":
                // محاسبه نتیجه
                calculateResult();
                break;
            case "C": // اگر دکمه پاک کردن کلیک شود
                clearResult();
                break;
            default:
                // افزودن عدد یا عملگر به نمایش
                currentResult += buttonText;
                break;
        }
        result.setText(currentResult);
    }

    private void clearResult() {
        currentResult = ""; // پاک کردن ورودی
        result.setText(""); // پاک کردن نمایش نتیجه
    }

    private void calculateResult() {
        try {
            String[] tokens = currentResult.split("(?<=[-+*/])|(?=[-+*/])");
            double value1 = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double value2 = Double.parseDouble(tokens[2]);

            double calculationResult = 0;

            switch (operator) {
                case "+":
                    calculationResult = value1 + value2;
                    break;
                case "-":
                    calculationResult = value1 - value2;
                    break;
                case "*":
                    calculationResult = value1 * value2;
                    break;
                case "/":
                    calculationResult = value1 / value2;
                    break;
            }

            currentResult = String.valueOf(calculationResult);
        } catch (Exception e) {
            currentResult = "Error";
        }
        result.setText(currentResult);
    }
}
