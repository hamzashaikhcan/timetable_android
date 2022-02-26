package com.sambeel.timetable.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sambeel.timetable.Classes.base_url;
import com.sambeel.timetable.Classes.helper;
import com.sambeel.timetable.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;

public class ProfileSettings extends AppCompatActivity {

    EditText name, email, password;
    Button update;

    String URL = new base_url().getUrl()+"update_teacher&name=";
    SpotsDialog dialog;
    helper hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        init();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails();
            }
        });

        SharedPreferences prefs = getSharedPreferences("USER_PREFS", 0);
        name.setText( prefs.getString("name","") );
        email.setText( prefs.getString("email","") );
        password.setText( prefs.getString("password","") );
    }

    public void init(){
        name = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);

        update = findViewById(R.id.updateButton);
        dialog = new SpotsDialog(ProfileSettings.this);
        hp = new helper(ProfileSettings.this);
    }

    public boolean isValid(){
        if( name.getText().toString().trim().isEmpty() ){
            name.setError("This field is required");
            return false;
        }
        if( email.getText().toString().trim().isEmpty() ){
            email.setError("This field is required");
            return false;
        }
        if( password.getText().toString().trim().isEmpty() ){
            password.setError("This field is required");
            return false;
        }
        return true;
    }

    public void updateDetails(){
        if(isValid()){
            dialog.show();
            SharedPreferences f_prefs = getSharedPreferences("USER_PREFS", 0);

            String id = f_prefs.getString("id", "");

            String f_name = name.getText().toString().trim();
            String f_email = email.getText().toString().trim();
            String f_password = password.getText().toString().trim();

            String finalURL = URL+f_name+"&email="+f_email+"&password="+f_password+"&id="+id;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, finalURL, new Response.Listener<String>() {
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

                            SharedPreferences setting = getSharedPreferences("USER_PREFS", 0);
                            SharedPreferences.Editor editor = setting.edit();

                            editor.putString("id", id);
                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.putString("password", password);

                            editor.putBoolean("isLoggedIn", true);

                            editor.commit();

                            Intent intent = new Intent(ProfileSettings.this, TimeTableActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(ProfileSettings.this, message, Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }


                    } catch (JSONException e) {
                        Toast.makeText(ProfileSettings.this, "Error: "+e, Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ProfileSettings.this, "Connection error at URL: "+finalURL, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            RequestQueue requestque = Volley.newRequestQueue(ProfileSettings.this);
            requestque.add(stringRequest);
            requestque.getCache().clear();
        }
    }

}