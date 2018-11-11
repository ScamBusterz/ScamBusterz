package com.example.andrewjohnson.scambusterz;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallReceiver extends PhoneStateListener {
    @Override
    public void onCallStateChanged(int state, String phoneNumber) {

        System.out.println("Heibqwbeqweqweq");
        super.onCallStateChanged(state, phoneNumber);
    }
}
