package com.example.testingapps;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class adminHome extends AppCompatActivity {

    Button enterId,exit;
    public EditText Idnum;
    final String TAG = main_screen_activity.class.getName();
    String url = "http://coms-309-069.cs.iastate.edu:8080/users/";
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);

        enterId = (Button) findViewById(R.id.send_button);
        Idnum = (EditText) findViewById(R.id.user_id_num);
        exit = (Button) findViewById(R.id.a_logout);


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
                // createAccount();
               // getId();
                deleteAccount();
                Toast errorToast = Toast.makeText(adminHome.this, "User Deleted", Toast.LENGTH_SHORT);
                errorToast.show();

            }
        });
    }
    public void deleteAccount() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.DELETE, url+Idnum.getText(), null, new Response.Listener<JSONObject>() {
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


