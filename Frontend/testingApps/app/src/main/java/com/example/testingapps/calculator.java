package com.example.testingapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class calculator extends AppCompatActivity {

    boolean add, subtract, multiply, divide = false;
    int out, equals; //temp
    String name, pass, UserName;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        Button button_0 = findViewById(R.id.num_0);
        Button button_1 = findViewById(R.id.num_1); //sets button_1 as ID of button 1 on calculator
        Button button_2 = findViewById(R.id.num_2);
        Button button_3 = findViewById(R.id.num_3);
        Button button_4 = findViewById(R.id.num_4);
        Button button_5 = findViewById(R.id.num_5);
        Button button_6 = findViewById(R.id.num_6);
        Button button_7 = findViewById(R.id.num_7);
        Button button_8 = findViewById(R.id.num_8);
        Button button_9 = findViewById(R.id.num_9);
        Button button_add = findViewById(R.id.button_add);
        Button button_subtract = findViewById(R.id.button_subtract);
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_divide = findViewById(R.id.button_divide);
        Button button_equals = findViewById(R.id.button_equals);
        Button button_clear = findViewById(R.id.button_clear);
        TextView output = findViewById(R.id.output);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        name = getIntent().getStringExtra("UserID");
        UserName = getIntent().getStringExtra("USERNAME");
        pass = getIntent().getStringExtra("PASSWORD");

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "0");
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() { //when button 1 is clicked on calculator, it will print out 1
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "1");
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "2");
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "3");
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "4");
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "5");
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "6");
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "7");
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "8");
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "9");
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add = true;
                out = Integer.parseInt(output.getText() + "");
                output.setText(null);
            }
        });

        button_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtract = true;
                out = Integer.parseInt(output.getText() + "");
                output.setText(null);
            }
        });

        button_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiply = true;
                out = Integer.parseInt(output.getText() + "");
                output.setText(null);
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                divide = true;
                out = Integer.parseInt(output.getText() + "");
                output.setText(null);
            }
        });

        button_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalsTotal();
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("");
            }
        });
    }
    public int equalsTotal(){
        int out2 = Integer.parseInt(output.getText() + "");

        if(add == true){
            output.setText(out + out2 + "");
            add = false;
            equals = out + out2;
        }

        if(subtract == true){
            output.setText(out - out2 + "");
            subtract = false;
            equals = out - out2;
        }

        if(multiply == true){
            output.setText(out * out2 + "");
            multiply = false;
            equals = out * out2;
        }

        if(divide == true){
            output.setText(out / out2 + "");
            divide = false;
            equals = out / out2;
        }
        return equals;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(calculator.this,main_screen_activity.class);
        Bundle extras = new Bundle();
        extras.putString("USERNAME",UserName);
        extras.putString("PASSWORD",pass);
        extras.putString("UserID", name);
        intent.putExtras(extras);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }
}
