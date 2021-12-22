package com.example.testingapps;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingapps.app.httpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class trainerHome extends AppCompatActivity {

    Button enterId, delete,exit;
    public EditText workoutDis, workoutTittle, workoutId;
    final String TAG = main_screen_activity.class.getName();
    String url = "http://coms-309-069.cs.iastate.edu:8080/workouts";
    String urlDel = "http://coms-309-069.cs.iastate.edu:8080/workouts/";
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_home);

        //  databaseHelper = new DatabaseHelper(this);

        enterId = (Button) findViewById(R.id.button_addWorkout);
        workoutId = (EditText) findViewById(R.id.workoutId);
        workoutTittle = (EditText) findViewById(R.id.add_workout);
        delete = (Button) findViewById(R.id.delete_workout);
        exit = (Button) findViewById(R.id.t_logout);



        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
                setContentView(R.layout.activity_login);

            }
        });

        enterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkout();
                Toast errorToast = Toast.makeText(trainerHome.this, "Workout Created", Toast.LENGTH_SHORT);
                errorToast.show();

            }
        });

    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deleteWorkout();
            Toast errorToast = Toast.makeText(trainerHome.this, "Workout Deleted", Toast.LENGTH_SHORT);
            errorToast.show();

        }
    });
}
    public void addWorkout() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("workoutName",workoutTittle.getText());
            temp.put("workoutDescription",workoutDis.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
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
    public void deleteWorkout() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.DELETE, urlDel+workoutId.getText(), null, new Response.Listener<JSONObject>() {
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

}



