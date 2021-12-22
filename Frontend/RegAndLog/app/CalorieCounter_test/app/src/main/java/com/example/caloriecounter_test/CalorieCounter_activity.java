package com.example.caloriecounter_test;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.caloriecounter_test.app.httpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class CalorieCounter_activity extends AppCompatActivity {
    final String TAG = MainActivity.class.getName();
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    String url = "http://coms-309-069.cs.iastate.edu:8080/users/2";
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





        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] += 5;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] -= 5;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] += 100;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] -= 100;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] += 1000;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] -= 1000;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out[0] = 0;
                out2 = out.toString();
                output.setText(out[0].toString());
            }
        });

        //will need to configure so it sends to total profile calorie count for the day
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestQueue();
                out[0] = 0;
                out2 = out.toString();
                output.setText(out[0].toString());
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
            //String id = ma.m.size()+1+"";
            temp.put("id", "2");
           // temp.get("id");

            temp.put("name", "Conner Spainhower");
            temp.put("password", "password12345");
            temp.put("calories", out[0]);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, url, temp,
                (Response.Listener<JSONObject>) response -> {
                    Log.d(TAG, response.toString());
                    pDialog.hide();

                }, error -> {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            // hide the progress dialog
            pDialog.hide();

        });
        httpRequest.getInstance().addToRequestQueue(jsonObjReq);
    }
}
