package com.example.andrewjohnson.scambusterz;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


public class CallReceiver extends PhoneStateListener {
    @Override
    public void onCallStateChanged(int state, String phoneNumber) {
        if(state == TelephonyManager.CALL_STATE_IDLE) {
            System.out.println("Phone: " + phoneNumber);
            super.onCallStateChanged(state, phoneNumber);
        }
    }

}
