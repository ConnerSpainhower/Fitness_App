package com.example.caloriecounter_test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.caloriecounter_test.app.httpRequest;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity {

    Button btn_register1;
    public EditText et_username, et_password, et_cpassword;
    final String TAG = MainActivity.class.getName();
    RequestQueue mRequestQueue;
    String url = "http://coms-309-069.cs.iastate.edu:8080/register";
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //  databaseHelper = new DatabaseHelper(this);

        et_username = (EditText) findViewById(R.id.et_username1);
        et_password = (EditText) findViewById(R.id.et_password1);
        et_cpassword = (EditText) findViewById(R.id.et_cpassword1);
        btn_register1 = (Button) findViewById(R.id.btn_register1);


        btn_register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));

                setContentView(R.layout.activity_input);
            }
        });
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
            temp.put("name",et_username);
            temp.put("password",et_password);
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
