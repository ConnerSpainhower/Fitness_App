package com.example.testingapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingapps.app.httpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class main_screen_activity extends AppCompatActivity {
    final String TAG = MainActivity.class.getName();
    RequestQueue mRequestQueue;
    TextView text;
    String url = "http://coms-309-069.cs.iastate.edu:8080/login";
    String userId = "http://coms-309-069.cs.iastate.edu:8080/users/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        String userName = " ";
        TextView text = (TextView) findViewById(R.id.text);


        Button button_1 = findViewById(R.id.workouts_goto);
        Button button_2 = findViewById(R.id.food_goto);
        Button button_3 = findViewById(R.id.calories_goto);
        Button button_4 = findViewById(R.id.calculator_goto);
        Button button_5 = findViewById(R.id.logout_goto);
        Button button_6 = findViewById(R.id.button_gochat);
        TextView output = findViewById(R.id.welcome);

            String name = getIntent().getStringExtra("USERNAME");
            String password = getIntent().getStringExtra("PASSWORD");
            String id = getIntent().getStringExtra("ID");
            System.out.println(id);
            text.setText(name);


        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_screen_activity.this,workoutPage.class);
                Bundle extras = new Bundle();
                extras.putString("USERNAME",name);
                extras.putString("PASSWORD",password);
                extras.putString("UserID", id);
                intent.putExtras(extras);
                startActivity(intent);
                setContentView(R.layout.workoutpage);

            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(main_screen_activity.this,foodEntryPage.class);
                    Bundle extras = new Bundle();
                    extras.putString("USERNAME",name);
                    extras.putString("PASSWORD",password);
                    extras.putString("UserID", id);
                    intent.putExtras(extras);
                    startActivity(intent);
                    setContentView(R.layout.foodentrypage);

                }

        });

        button_3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(main_screen_activity.this,CalorieCounter_activity.class);
                    Bundle extras = new Bundle();
                    extras.putString("USERNAME",name);
                    extras.putString("PASSWORD",password);
                    extras.putString("UserID", id);
                    intent.putExtras(extras);
                    startActivity(intent);
                    setContentView(R.layout.activity_calorie_counter);

                }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_screen_activity.this,calculator.class);
                Bundle extras = new Bundle();
                extras.putString("USERNAME",name);
                extras.putString("PASSWORD",password);
                extras.putString("UserID", id);
                intent.putExtras(extras);
                startActivity(intent);
                setContentView(R.layout.calculator_layout);

            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to logout screen
                startActivity(new Intent(view.getContext(), MainActivity.class));
                setContentView(R.layout.activity_login);
            }
        });

        button_6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(main_screen_activity.this,Chat.class);
                Bundle extras = new Bundle();
                extras.putString("USERNAME",name);
                extras.putString("PASSWORD",password);
                extras.putString("UserID", id);
                intent.putExtras(extras);
                startActivity(intent);
                setContentView(R.layout.message_trainer);
            }
        });


    }
    public void sendRequestQueue(){
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        //request
        mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("id", "4");
            temp.put("name", "Conner Spainhower");
            temp.put("password", "password123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, null,
                (Response.Listener<JSONObject>) response -> {
                    Log.d(TAG, response.toString());
                    pDialog.hide();

                },error -> {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            // hide the progress dialog
            pDialog.hide();

        });
        httpRequest.getInstance().addToRequestQueue(jsonObjReq);
    }


}
