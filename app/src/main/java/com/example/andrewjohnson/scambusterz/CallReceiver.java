package com.example.andrewjohnson.scambusterz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import android.os.Bundle;


public class CallReceiver extends BroadcastReceiver{



    public void onReceive(Context context, Intent intent){

    }

//    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
//
//
//
//    public CallReceiver(){
//        super();
//    }
//
//    public void onReceive(Context context, Intent intent) {
//        String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
//        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
//
//        try {
//            System.out.println("Receiver Start");
//            Toast.makeText(context," Receiver start ",Toast.LENGTH_SHORT).show();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//            this.incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//            if (spamcall(incomingNumber))
//                sendPushNotification(incomingNumber);
//        }
//}


    private Boolean spamcall(String phoneNum){
        return true;
    }

    private void sendPushNotification(String incnu){
        System.out.println("hello");
    }

}
