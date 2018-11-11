package com.example.andrewjohnson.scambusterz;

import android.telephony.PhoneStateListener;

public class CallReceiver extends PhoneStateListener {
    @Override
    public void onCallStateChanged(int state, String phoneNumber) {
        System.out.println("Phone: " + phoneNumber);
        super.onCallStateChanged(state, phoneNumber);
    }
}
