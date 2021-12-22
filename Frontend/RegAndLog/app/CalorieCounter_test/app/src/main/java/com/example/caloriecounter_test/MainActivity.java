package com.example.caloriecounter_test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.caloriecounter_test.app.httpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    final String TAG = MainActivity.class.getName();
    RequestQueue mRequestQueue;
    String url = "http://coms-309-069.cs.iastate.edu:8080/register";
    String tag_json_obj = "json_obj_req";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);



        Button buttonConnect1 = findViewById(R.id.button_positive_5);
        buttonConnect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button buttonConnect2 = findViewById(R.id.button_negative_5);
        buttonConnect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button buttonConnect3 = findViewById(R.id.button_positive_100);
        buttonConnect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button buttonConnect4 = findViewById(R.id.button_negative_100);
        buttonConnect4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button buttonConnect5 = findViewById(R.id.button_positive_1000);
        buttonConnect5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button buttonConnect6 = findViewById(R.id.button_negative_1000);
        buttonConnect6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button buttonConnect7 = findViewById(R.id.button_reset);
        buttonConnect7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
            }
        });

        Button sendTotal = findViewById(R.id.button_add_total);
        sendTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
                sendRequestQueue();
            }
        });
    }

    public void sendRequestQueue() {
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
