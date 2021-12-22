package com.example.testingapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingapps.app.httpRequest;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity implements Serializable{
    final String TAG = MainActivity.class.getName();
    RequestQueue mRequestQueue;
    String url = "http://coms-309-069.cs.iastate.edu:8080/login";
    String userUrl = "http://coms-309-069.cs.iastate.edu:8080/users/";
    String tag_json_obj = "json_obj_req";
    TextView text;
    public EditText et_username, et_password;
    public String idValue = "null";
    public String extra = "hello world";
    public boolean ready = false;
    Button btn_register, btn_login, admin, trainer;
    WebSocketClient wsc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        trainer = (Button) findViewById(R.id.trainerlog);
        admin = (Button) findViewById(R.id.login_admin);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        //reg_password = (EditText) findViewById(R.id.et_password1);
        //reg_username = (EditText) findViewById(R.id.et_username1);
        //et_cpassword = (EditText) findViewById(R.id.et_cpassword1);
        btn_register = (Button) findViewById(R.id.btn_register);
        //btn_register1 = (Button) findViewById(R.id.btn_register1);
        btn_login = (Button) findViewById(R.id.btn_login);


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), adminLogin.class));
                setContentView(R.layout.adminlogin);

                Button reg = findViewById(R.id.Admin_login);
                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(view.getContext(), adminLogin.class));
                    }
                });
            }
        });

        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), trainerLogin.class));
                setContentView(R.layout.login_trainer);

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), register.class));

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


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void connectWebSocket(){
        URI uri;
        try{
            uri = new URI("ws://echo.websocket.org");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        wsc = new WebSocketClient(uri){
            @Override
            public void onOpen(ServerHandshake serverHandshake){
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String msg){
                Log.i("Websocket", "Message Received");
                //Add print to screen here with text output
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote){
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception e){
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        wsc.connect();
        /*
        Need to put send code here after we make interface
         */
    }
    private void userLogin(){
        //request
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        String password = et_password.getText().toString();
        String username = et_username.getText().toString();
        System.out.println(username);

        //Json request
        JSONObject temp = new JSONObject();
        System.out.println(idValue);
       try {
            temp.put("name", et_username);
            temp.put("password" , et_password);
         } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObj= new JsonObjectRequest(Request.Method.GET, userUrl + idValue, temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject id= response;
                try {
                    String user = id.getString("name");
                    System.out.println(user);
                    String pass = id.getString("password");
                    if(user.equals(username) && pass.equals(password)){
                        setContentView(R.layout.activity_main);
                        getUserData();
                    }
                    else {
                        Toast errorToast = Toast.makeText(MainActivity.this, "Error , check username and password", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        httpRequest.getInstance().addToRequestQueue(jsonObj);

    }
    public void getId() {
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        final String password = et_password.getText().toString();
        final String username = et_username.getText().toString();
        //request
        mRequestQueue = Volley.newRequestQueue(this);

        //Json request
        JSONObject temp = new JSONObject();
        try {
            temp.put("name", et_username.getText());
            temp.put("password", et_password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject id = response;


                    idValue = id.toString();
                    idValue = idValue.replaceAll("[\\D]", "");
                    if (!idValue.equals("null")) {
                        userLogin();
                    }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast errorToast = Toast.makeText(MainActivity.this, "Error , check username and password", Toast.LENGTH_SHORT);
                errorToast.show();

            }
        });
        httpRequest.getInstance().addToRequestQueue(jsonObjReq);
    }
    public void getUserData(){

       Intent sendStuff = new Intent(this, main_screen_activity.class);
      // sendStuff.putExtra("key",et_username.getText().toString());
        Bundle extras = new Bundle();
        extras.putString("USERNAME",et_username.getText().toString());
        extras.putString("PASSWORD",et_password.getText().toString());
        extras.putString("ID",idValue);
        sendStuff.putExtras(extras);
        startActivity(sendStuff);
       // Intent intent = new Intent(this,foodEntryPage.class);
        //intent.putExtra("UserID", idValue);
       // startActivity(intent);

    }


    }