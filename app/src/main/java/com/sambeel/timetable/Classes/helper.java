package com.sambeel.timetable.Classes;

import android.content.Context;
import android.content.DialogInterface;
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


public class helper {

    private static final int PERMISSION_REQUEST_CODE = 201;
    private static final int PERMISSION_REQUEST_IMAGE_CODE = 200;
    private static int IMG_REQUEST = 100;

    Context mCtx;
    FragmentManager fragmentManager;
    FragmentTransaction ft;

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


