package com.example.andrewjohnson.scambusterz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if (spamcall(incomingNumber))
                sendPushNotification(incomingNumber);
        }
    }

    private Boolean spamcall(String phoneNum){
        return true;
    }
    private void sendPushNotification(String incnu){
        System.out.println(incnu);
    }
}
