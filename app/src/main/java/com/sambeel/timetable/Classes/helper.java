package com.sambeel.timetable.Classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.sambeel.timetable.R;
import com.sambeel.timetable.Services.MyBroadcastReceiver;


public class helper {

    private static final int PERMISSION_REQUEST_CODE = 201;
    private static final int PERMISSION_REQUEST_IMAGE_CODE = 200;
    private static int IMG_REQUEST = 100;

    Context mCtx;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    int requestCode = 280192;

    public helper(Context mCtx) {

        this.mCtx = mCtx;

    }


    public Boolean checkOnline(){
        ConnectivityManager conMgr =  (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){

            Toast.makeText(mCtx, "Not internet connection", Toast.LENGTH_SHORT).show();

            return false;

        }else{
            return true;
        }
    }

    public void cancelAlarm() {

        AlarmManager alarmManager2 = (AlarmManager) mCtx.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(mCtx.getApplicationContext(),requestCode,new Intent(mCtx, MyBroadcastReceiver.class),0);
        alarmManager2.cancel(pendingIntent2);

//        Toast.makeText(mCtx.getApplicationContext(), "Alarm Cancelled - "+ requestCode, Toast.LENGTH_LONG).show();
    }

    public void replaceFragment(Fragment fragment){
        fragmentManager = ((AppCompatActivity)mCtx).getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void replaceFragmentWithArgs(Fragment fragment, Bundle args){

        fragment.setArguments(args);
        fragmentManager = ((AppCompatActivity)mCtx).getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}


