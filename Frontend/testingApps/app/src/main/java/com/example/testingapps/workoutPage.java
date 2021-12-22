package com.example.testingapps;

import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class workoutPage extends AppCompatActivity {
    final String TAG = main_screen_activity.class.getName();
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    String url = "http://coms-309-069.cs.iastate.edu:8080/workouts";
    String tag_json_obj = "json_obj_req";
    String name, UserName, pass;
    Integer[] out = {0};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutpage);

        Button button_1 = findViewById(R.id.UpperBody);
        Button button_2 = findViewById(R.id.lowerbody);
        Button button_3 = findViewById(R.id.core);
        Button button_4 = findViewById(R.id.cardio);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        name = getIntent().getStringExtra("UserID");
        UserName = getIntent().getStringExtra("USERNAME");
        pass = getIntent().getStringExtra("PASSWORD");

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), upperbody.class));
                setContentView(R.layout.upperbody);
                updateWorkout();
            }
        });


        button_2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    startActivity(new Intent(view.getContext(),legs.class));
                    setContentView(R.layout.legs);
                    updateWorkout();
                }

        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),core.class));
                setContentView(R.layout.core);
                updateWorkout();
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),cardio.class));
                setContentView(R.layout.cardio);
                updateWorkout();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(workoutPage.this,main_screen_activity.class);
        Bundle extras = new Bundle();
        extras.putString("USERNAME",UserName);
        extras.putString("PASSWORD",pass);
        extras.putString("UserID", name);
        intent.putExtras(extras);
        startActivity(intent);
        return super.onOptionsItemSelected(item);


    }
    public void updateWorkout() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("id",1);
            temp.put("workoutName","Core Workout");
            temp.put("workoutDescription","core");
            temp.put("completed","true");


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
}