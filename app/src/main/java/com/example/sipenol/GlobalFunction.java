package com.example.sipenol;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalFunction {
    private GlobalFunction(){

    }
    private static GlobalFunction instance = null;

    public static synchronized GlobalFunction getInstance(){
        if (instance == null){
            instance = new GlobalFunction();
        }
        return instance;
    }

    public void generateAlertDialog(Context context, String message, String title){
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setCancelable(false);
        ab.setMessage(message);
        ab.setTitle(title);
        ab.setNegativeButton("Cancel", null);
        ab.show();
    }
    public Api initApi(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        return api;
    }
    public ProgressDialog generateProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Sedang mengambil data dari awan...");
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDialog;
    }
    public void setStatusTextColor(TextView tv, int status){
        switch (status){
            case 0:{
                tv.setTextColor(Color.rgb(43,101,209));
                break;
            }
            case 1:{
                tv.setTextColor(Color.rgb(228, 140, 26));
                break;
            }
            case 2:{
                tv.setTextColor(Color.rgb(39,209,45));
                break;
            }
            case 3:{
                tv.setTextColor(Color.rgb(228,43,26));
            }
        }
    }
    public void setStatusBackgroundColor(LinearLayout ly, int status){
        switch (status){
            case 0:{
                ly.setBackgroundColor(Color.rgb(43,101,209));
                break;
            }
            case 1:{
                ly.setBackgroundColor(Color.rgb(228, 140, 26));
                break;
            }
            case 2:{
                ly.setBackgroundColor(Color.rgb(39,209,45));
                break;
            }
            case 3:{
                ly.setBackgroundColor(Color.rgb(228,43,26));
            }
        }
    }
}
