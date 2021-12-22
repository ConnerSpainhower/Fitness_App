package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exp1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        Button buttonConnect0 = findViewById(R.id.num_0);
        buttonConnect0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect1 = findViewById(R.id.num_1);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect2 = findViewById(R.id.num_2);
        buttonConnect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect3 = findViewById(R.id.num_3);
        buttonConnect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect4 = findViewById(R.id.num_4);
        buttonConnect4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect5 = findViewById(R.id.num_5);
        buttonConnect5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect6 = findViewById(R.id.num_6);
        buttonConnect6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect7 = findViewById(R.id.num_7);
        buttonConnect7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect8 = findViewById(R.id.num_8);
        buttonConnect8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect9 = findViewById(R.id.num_9);
        buttonConnect9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect10 = findViewById(R.id.button_add);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect11 = findViewById(R.id.button_subtract);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect12 = findViewById(R.id.button_multiply);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect13 = findViewById(R.id.button_divide);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });

        Button buttonConnect14 = findViewById(R.id.button_equals);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), calculator.class));
            }
        });
    }
}