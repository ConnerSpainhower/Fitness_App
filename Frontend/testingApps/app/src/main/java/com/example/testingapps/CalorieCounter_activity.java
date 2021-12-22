package com.example.testingapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingapps.app.httpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class CalorieCounter_activity extends AppCompatActivity {
    final String TAG = main_screen_activity.class.getName();
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    String idName, idUser, idPass;
    String url = "http://coms-309-069.cs.iastate.edu:8080/users/";
    String tag_json_obj = "json_obj_req";
    Integer[] out = {0};
    String out2 = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        Button button_1 = findViewById(R.id.button_positive_5);
        Button button_2 = findViewById(R.id.button_negative_5);
        Button button_3 = findViewById(R.id.button_positive_100);
        Button button_4 = findViewById(R.id.button_negative_100);
        Button button_5 = findViewById(R.id.button_positive_1000);
        Button button_6 = findViewById(R.id.button_negative_1000);
        Button button_7 = findViewById(R.id.button_reset);
        Button button_8 = findViewById(R.id.button_add_total);
        TextView output = findViewById(R.id.CalorieCounter_output);

        idName = getIntent().getStringExtra("UserID");
        idUser = getIntent().getStringExtra("USERNAME");
        idPass = getIntent().getStringExtra("PASSWORD");



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] += 5;
                output.setText(out[0].toString());
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] -= 5;
                output.setText(out[0].toString());
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] += 100;
                output.setText(out[0].toString());
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] -= 100;
                output.setText(out[0].toString());
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] += 1000;
                output.setText(out[0].toString());
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] -= 1000;
                output.setText(out[0].toString());
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] = 0;
                output.setText(out[0].toString());
            }
        });

        //will need to configure so it sends to total profile calorie count for the day
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCalories();
                JSONObject temp = new JSONObject();
                out[0] = 0;
                output.setText(out[0].toString());
            }
        });
    }
    public void updateCalories() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("id",idName);
            temp.put("name",idUser);
            temp.put("password", idPass);
            temp.put("calories", out[0]);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, url + idName, temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjReq);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(CalorieCounter_activity.this,main_screen_activity.class);
        Bundle extras = new Bundle();
        extras.putString("USERNAME",idUser);
        extras.putString("PASSWORD",idPass);
        extras.putString("UserID", idName);
        intent.putExtras(extras);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }
}
