package com.example.caloriecounter_test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    public EditText et_username, et_password;
    Button btn_register, btn_login,btn_food;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setContentView(R.layout.activity_calorie_counter);

        //databaseHelper = new DatabaseHelper(this);
       et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        //reg_password = (EditText) findViewById(R.id.et_password1);
        //reg_username = (EditText) findViewById(R.id.et_username1);
        //et_cpassword = (EditText) findViewById(R.id.et_cpassword1);
        btn_register = (Button) findViewById(R.id.btn_register);
       //btn_register1 = (Button) findViewById(R.id.btn_register1);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_food = (Button) findViewById(R.id.btn_food);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),register.class));
                getRequestQueue();
                setContentView(R.layout.activity_input);

                Button reg = findViewById(R.id.btn_register1);
                 reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(view.getContext(), register.class));
                    }
                });
            }
        });
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),foodEntryPage.class));
                //getRequestQueue();
                setContentView(R.layout.foodentrypage);

                Button reg = findViewById(R.id.btngocalendar);
                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(view.getContext(), foodEntryPage.class));
                    }
                });
            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CalorieCounter_activity.class));
                getRequestQueue();
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



    public void getRequestQueue() {
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        //request
        mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("name","Bob");
            temp.put("password","Bush");
            temp.put("calories",0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, temp,
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