package com.example.main_menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class main_screen_activity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button button_1 = findViewById(R.id.workouts_goto);
        Button button_2 = findViewById(R.id.food_goto);
        Button button_3 = findViewById(R.id.calories_goto);
        Button button_4 = findViewById(R.id.calculator_goto);
        Button button_5 = findViewById(R.id.logout_goto);
        TextView output = findViewById(R.id.greeting_id);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to workouts to subtract total calories
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to foods to add to total calorie count
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to total calories for the day
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to calculator screen
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to logout screen
            }
        });
    }
}
