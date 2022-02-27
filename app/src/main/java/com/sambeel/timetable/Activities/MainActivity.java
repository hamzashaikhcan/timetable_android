package com.sambeel.timetable.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sambeel.timetable.Classes.base_url;
import com.sambeel.timetable.Classes.helper;
import com.sambeel.timetable.R;
import com.sambeel.timetable.Services.ForegroundService;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    TextView forgetPassword, error;
    Button login;
    EditText email, password;

    String URL = new base_url().getBaseUrl();

    SpotsDialog dialog;
    helper hp;

    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FIREBASE TOKEN", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                       Log.w("FIREBASE TOKEN", "FIREBASE TOKEN => "+ token);
                    }
                });

        SharedPreferences prefs = getSharedPreferences("USER_PREFS", 0);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if(isLoggedIn){
            Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            init();
        }


    }


    public void init(){
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);

        login = findViewById(R.id.loginButton);

//        forgetPassword = findViewById(R.id.txt_forgot_password);
        error = findViewById(R.id.txt_error);

        dialog = new SpotsDialog(MainActivity.this);
        hp = new helper(MainActivity.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
    }


    void performLogin(){
        if(isValid()){
            dialog.show();
            String em = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String finalURL = URL+"&email="+em+"&password="+pass;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);


                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");


                            if(code.equals("success")){

                                dialog.cancel();

                                String id          = jsonObject.getString("id");
                                String name        = jsonObject.getString("name");;
                                String email       = jsonObject.getString("email");
                                String password    = jsonObject.getString("password");
                                String created_at  = jsonObject.getString("created_at");

                                SharedPreferences setting = getSharedPreferences("USER_PREFS", 0);
                                SharedPreferences.Editor editor = setting.edit();

                                editor.putString("id", id);
                                editor.putString("name", name);
                                editor.putString("email", email);
                                editor.putString("password", password);
                                editor.putString("created_at", created_at);

                                editor.putBoolean("isLoggedIn", true);

                                editor.commit();

                                Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
                                startActivity(intent);
                                finish();
                        }
                        else{
                            error.setText(message);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }


                    }
                    catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Error: "+e, Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            }){

                @Override
                protected Map<String, String> getParams()  {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("token", token);
                    params.put("email", em);
                    params.put("password", pass);
                    params.put("action", "login");



                    return params;
                }
            };

            RequestQueue requestque = Volley.newRequestQueue(MainActivity.this);
            requestque.add(stringRequest);
            requestque.getCache().clear();
        }
    }

    boolean isValid(){
        if( email.getText().toString().trim().isEmpty() ){
            email.setError("This field is required");
            return false;
        }
        else if( password.getText().toString().trim().isEmpty() ){
            password.setError("This field is required");
            return false;
        }
        else {
            return true;
        }
    }


}