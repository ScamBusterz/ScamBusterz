package com.example.andrewjohnson.scambusterz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.os.Build;
import android.app.NotificationChannel;

import android.provider.CallLog;
import android.database.Cursor;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
    private final String CHANNEL_ID = "Spam Caller";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallReceiver mCallReceiver = new CallReceiver();
        TelephonyManager mTelephonyManager = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(mCallReceiver, PhoneStateListener.LISTEN_CALL_STATE);

//        ReadLog read = new ReadLog(getApplicationContext());
        accessLogs();


        createNotificationChannel();
        Button btn = (Button) findViewById(R.id.notifyButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationCall();
            }
        });
    }

    public void accessLogs(){
        String[] projection = {
                android.provider.CallLog.Calls.NUMBER,
                android.provider.CallLog.Calls.TYPE,
                android.provider.CallLog.Calls.CACHED_NAME,
                android.provider.CallLog.Calls.CACHED_NUMBER_TYPE
        };

        String strSelection = CallLog.Calls.TYPE + "=" + CallLog.Calls.INCOMING_TYPE;

        String strOrder = CallLog.Calls.DATE + "DESC";

        Cursor mCallCursor = getContentResolver().query(
            CallLog.Calls.CONTENT_URI, projection, strSelection, null, strOrder
        );

        mCallCursor.moveToFirst();

//        Uri allCalls = Uri.parse("content://call_log/calls");
//        Cursor c = getContentResolver().query(allCalls, projection, null, null, null);
//        System.out.println(c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)));
//        c.close();

//        String[] projection = {
//                CallLog.Calls._ID,
//                CallLog.Calls.NUMBER
//        };
//
//        Cursor c = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, projection,c., null, CallLog.Calls.DATE + "DESC");
//
//        if(c.getCount()>0){
//
//            c.moveToFirst();
//            System.out.println("int" + Integer.toString(c.getInt(0)));
//            do{
//                String callerNumber = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
//                int callType = c.getInt(c.getColumnIndex(CallLog.Calls.TYPE));
//
//                if(callType == CallLog.Calls.MISSED_TYPE){
//
//                }
//            }while(c.moveToNext());

//        }
    }

    public void notificationCall(){
        NotificationCompat.Builder notificationBuilder =  new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_priority_high_notification)
                .setContentTitle("SPAM CALLER")
                .setContentText("This phone number has a high likelihood of being spam")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "foo";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

}
