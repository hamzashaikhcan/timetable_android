package com.sambeel.timetable.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.sambeel.timetable.Adapter.TimeTableAdapter;
import com.sambeel.timetable.Classes.Timetable;
import com.sambeel.timetable.Classes.base_url;
import com.sambeel.timetable.Classes.helper;
import com.sambeel.timetable.R;

import dmax.dialog.SpotsDialog;

public class TimeTableActivity extends AppCompatActivity {

    RecyclerView monday_list, tuesday_list, wednesday_list, thursday_list, friday_list, saturday_list;
    RecyclerView.Adapter monday_adapter, tuesday_adapter, wednesday_adapter, thursday_adapter, friday_adapter, saturday_adapter;
    List<Timetable> monday, tuesday, wednesday, thursday, friday, saturday;

    String URL = new base_url().getUrl()+"get_timetable&teacher=";
    SpotsDialog dialog;
    helper hp;

    ImageView logout, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        init();
        getData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeTableActivity.this, ProfileSettings.class);
                startActivity(intent);
            }
        });

    }

    public void init(){

        monday_list = findViewById(R.id.monday_list);
        monday_list.setHasFixedSize(true);
        monday_list.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, LinearLayoutManager.HORIZONTAL, false));

        tuesday_list = findViewById(R.id.tuesday_list);
        tuesday_list.setHasFixedSize(true);
        tuesday_list.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, LinearLayoutManager.HORIZONTAL, false));

        wednesday_list = findViewById(R.id.wednesday_list);
        wednesday_list.setHasFixedSize(true);
        wednesday_list.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, LinearLayoutManager.HORIZONTAL, false));

        thursday_list = findViewById(R.id.thursday_list);
        thursday_list.setHasFixedSize(true);
        thursday_list.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, LinearLayoutManager.HORIZONTAL, false));

        friday_list = findViewById(R.id.friday_list);
        friday_list.setHasFixedSize(true);
        friday_list.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, LinearLayoutManager.HORIZONTAL, false));

        saturday_list = findViewById(R.id.saturday_list);
        saturday_list.setHasFixedSize(true);
        saturday_list.setLayoutManager(new LinearLayoutManager(TimeTableActivity.this, LinearLayoutManager.HORIZONTAL, false));

        monday = new ArrayList<Timetable>();
        tuesday = new ArrayList<Timetable>();
        wednesday = new ArrayList<Timetable>();
        thursday = new ArrayList<Timetable>();
        friday = new ArrayList<Timetable>();
        saturday = new ArrayList<Timetable>();

        logout = findViewById(R.id.logout);
        settings = findViewById(R.id.settings);

        dialog = new SpotsDialog(TimeTableActivity.this);
        hp = new helper(TimeTableActivity.this);
    }

    public void getData(){
        dialog.show();
        SharedPreferences prefs = getSharedPreferences("USER_PREFS", 0);
        String name = prefs.getString("name", "");

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, URL+name, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.cancel();
                try {
                    JSONObject jsonObject = response.getJSONObject(0);


                    JSONArray res_monday = jsonObject.getJSONArray("monday");
                    JSONArray res_tuesday = jsonObject.getJSONArray("tuesday");
                    JSONArray res_wednesday = jsonObject.getJSONArray("wednesday");
                    JSONArray res_thursday = jsonObject.getJSONArray("thursday");
                    JSONArray res_friday = jsonObject.getJSONArray("friday");
                    JSONArray res_saturday = jsonObject.getJSONArray("saturday");

                    List<Timetable> mon, tues, wed, thurs, fri, sat;

                    mon = addData(res_monday);
                    monday_adapter = new TimeTableAdapter(mon, TimeTableActivity.this);
                    monday_list.setAdapter(monday_adapter);

                    tues = addData(res_tuesday);
                    tuesday_adapter = new TimeTableAdapter(tues, TimeTableActivity.this);
                    tuesday_list.setAdapter(tuesday_adapter);

                    wed = addData(res_wednesday);
                    wednesday_adapter = new TimeTableAdapter(wed, TimeTableActivity.this);
                    wednesday_list.setAdapter(wednesday_adapter);

                    thurs = addData(res_thursday);
                    thursday_adapter = new TimeTableAdapter(thurs, TimeTableActivity.this);
                    thursday_list.setAdapter(thursday_adapter);

                    fri = addData(res_friday);
                    friday_adapter = new TimeTableAdapter(fri, TimeTableActivity.this);
                    friday_list.setAdapter(friday_adapter);

                    sat = addData(res_saturday);
                    saturday_adapter = new TimeTableAdapter(sat, TimeTableActivity.this);
                    saturday_list.setAdapter(saturday_adapter);



                } catch (JSONException e) {
                    Toast.makeText(TimeTableActivity.this, "Error: "+e, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    dialog.cancel();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TimeTableActivity.this, "Connection error at URL: "+URL, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        RequestQueue requestque = Volley.newRequestQueue(TimeTableActivity.this);
        requestque.add(stringRequest);
        requestque.getCache().clear();

    }

    public List<Timetable> addData(JSONArray data){
        List<Timetable> local_data = new ArrayList<>();
        Timetable tt;
        for (int i=0; i<data.length(); i++){
            try {
                tt = new Timetable(
                        String.valueOf(i),
                        data.getJSONObject(i).getString("class"),
                        data.getJSONObject(i).getString("day")
                );
                local_data.add(tt);
            } catch (JSONException e) {
                Toast.makeText(TimeTableActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();;
            }

        }
        return local_data;
    }

    public void performLogout(){
        SharedPreferences setting = getSharedPreferences("USER_PREFS", 0);
        SharedPreferences.Editor editor = setting.edit();

        editor.putString("id", null);
        editor.putString("name", null);
        editor.putString("email", null);
        editor.putString("password", null);
        editor.putString("created_at", null);

        editor.putBoolean("isLoggedIn", false);

        editor.commit();

        Intent intent = new Intent(TimeTableActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}