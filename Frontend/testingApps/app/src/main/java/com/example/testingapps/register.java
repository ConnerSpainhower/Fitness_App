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

public class register extends AppCompatActivity {

    Button btn_register1;
    public EditText et_username, et_password, et_cpassword;
    final String TAG = main_screen_activity.class.getName();
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
                if(et_password.getText().toString().equals(et_cpassword.getText().toString())) {
                    createAccount();
                    startActivity(new Intent(v.getContext(), MainActivity.class));
                    setContentView(R.layout.activity_input);
                }
                else {
                    Toast errorToast = Toast.makeText(register.this, "Error , Make sure your passwords match", Toast.LENGTH_SHORT);
                    errorToast.show();
                }

            }
        });
    }
    public void createAccount() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("name",et_username.getText());
            temp.put("password",et_password.getText());
            temp.put("calories",0);

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
