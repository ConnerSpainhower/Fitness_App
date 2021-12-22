package com.example.testingapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class foodEntryPage extends AppCompatActivity {
    final String TAG = MainActivity.class.getName();
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    String url = "http://coms-309-069.cs.iastate.edu:8080/foods";
    String userUrl = "http://coms-309-069.cs.iastate.edu:8080/users/";
    String tag_json_obj = "json_obj_req";
    Integer[] out = {0};
    String name, UserName, pass;
    Integer foodIdNum;
    public EditText food, cals;
    public TextView thedate;
    public Button enterFood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodentrypage);
        enterFood = (Button) findViewById(R.id.btn_enter);
        food = (EditText) findViewById(R.id.et_foodAte);
        cals = (EditText) findViewById(R.id.et_caloriesAte);


        name = getIntent().getStringExtra("UserID");
        UserName = getIntent().getStringExtra("USERNAME");
        pass = getIntent().getStringExtra("PASSWORD");
        System.out.println(name);
        foodIdNum =721;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        enterFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();

               // Intent intent = new Intent(foodEntryPage.this,Calender.class);
             //  startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(foodEntryPage.this,main_screen_activity.class);
        Bundle extras = new Bundle();
        extras.putString("USERNAME",UserName);
        extras.putString("PASSWORD",pass);
        extras.putString("UserID", name);
        intent.putExtras(extras);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }
    public void updateFood() {
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("foodName",food.getText());
            temp.put("foodCalories",cals.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                foodIdNum = foodIdNum +1;
                addFoodToUser();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjReq);

    }

    public void addFoodToUser(){
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, userUrl+name+"/foods/"+foodIdNum,null, new Response.Listener<JSONObject>() {
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
