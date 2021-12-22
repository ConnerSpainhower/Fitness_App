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

public class adminLogin extends AppCompatActivity {

    Button enter;
    String idNum = "null";
    public EditText admin_user, admin_pass;
    final String TAG = main_screen_activity.class.getName();
    String url = "http://coms-309-069.cs.iastate.edu:8080/register-admin";
    String adminurl = "http://coms-309-069.cs.iastate.edu:8080/login-admin";
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        //  databaseHelper = new DatabaseHelper(this);

        admin_user = (EditText) findViewById(R.id.admin_user);
        admin_pass = (EditText) findViewById(R.id.admin_pass);
        enter = (Button)findViewById(R.id.Admin_login);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // createAccount();
                getId();

            }
        });
    }
    public void createAccount() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("name",admin_user.getText());
            temp.put("password",admin_pass.getText());

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

    public void getId() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String password = admin_user.getText().toString();
        String username = admin_pass.getText().toString();

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("name",admin_user.getText());

            temp.put("password",admin_pass.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, adminurl, temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject id = response;

                    idNum = id.toString();
                    idNum = idNum.replaceAll("[\\D]", "");
                    if (!idNum.equals("null")) {
                        setContentView(R.layout.admin_home);
                        Intent sendStuff = new Intent(adminLogin.this, adminHome.class);
                        startActivity(sendStuff);
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        httpRequest.getInstance().addToRequestQueue(jsonObjReq);
    }

}


