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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class upperbody extends AppCompatActivity {
    final String TAG = main_screen_activity.class.getName();
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    String url = "http://coms-309-069.cs.iastate.edu:8080/users/2";
    String tag_json_obj = "json_obj_req";
    Integer[] out = {0};
    String out2 = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upperbody);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(upperbody.this,workoutPage.class));
        return super.onOptionsItemSelected(item);
    }
}
