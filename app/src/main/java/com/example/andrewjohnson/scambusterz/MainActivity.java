package com.example.andrewjohnson.scambusterz;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        try{
            String imei = tm.getDeviceId();
            System.out.println(imei);
        }
        catch(Exception e){
            System.out.println("error");
        }



//        CallReceiver cRec = new CallReceiver();
//        String ACTION = "com.example.andrewjohnson.scambusterz.CallReceiver";
//
//        IntentFilter intentFilter = new IntentFilter(ACTION);
//        this.registerReceiver(cRec, intentFilter);
    }
}
