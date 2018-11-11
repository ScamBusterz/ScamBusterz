package com.example.andrewjohnson.scambusterz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Build;
import android.app.NotificationChannel;

import android.provider.CallLog;
import android.database.Cursor;
import android.net.Uri;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.app.Activity;


public class MainActivity extends AppCompatActivity {
    private final String CHANNEL_ID = "Spam Caller";

    private static final int REQUEST_RUNTIME_PERMISSION = 123;
    String[] permissons = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallReceiver mCallReceiver = new CallReceiver();
        TelephonyManager mTelephonyManager = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(mCallReceiver, PhoneStateListener.LISTEN_CALL_STATE);

        //Check permissions

//        final String[] NECESSARY_PERMISSIONS = new String[] {Manifest.permission.GET_ACCOUNTS };
//
//        if (ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
//
//            //Permission is granted
//
//        } else {
//
//            //ask for permission
//
//            ActivityCompat.requestPermissions(
//                    MainActivity.this,
//                    NECESSARY_PERMISSIONS, 123);
//        }
//        ReadLog read = new ReadLog(getApplicationContext());
        if (CheckPermission(MainActivity.this, permissons[0])) {
            // you have permission go ahead
            accessLogs();
        } else {
            // you do not have permission go request runtime permissions
            RequestPermission(MainActivity.this, permissons, REQUEST_RUNTIME_PERMISSION);
        }



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
        };
//
//        String strSelection = CallLog.Calls.TYPE + "=" + CallLog.Calls.INCOMING_TYPE;
//
//        String strOrder = CallLog.Calls.DATE + "DESC";
//
//        Cursor mCallCursor = getContentResolver().query(
//            CallLog.Calls.CONTENT_URI, projection, strSelection, null, strOrder
//        );
//
//        mCallCursor.moveToFirst();

        Uri allCalls = Uri.parse("content://call_log/calls");
        String strOrder = CallLog.Calls.DATE + "DESC";

        Cursor c = getContentResolver().query(allCalls,projection, null, null, CallLog.Calls.DATE + "DESC");
        while(c.moveToNext()){
            String callNumber = c.getString(c
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER));
            Log.d("NUMBER", callNumber);
        }


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

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {

            case REQUEST_RUNTIME_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // you have permission go ahead
                    accessLogs();
                } else {
                    // you do not have permission show toast.
                }
                return;
            }
        }
    }

    public void RequestPermission(Activity thisActivity, String[] Permission, int Code) {
        if (ContextCompat.checkSelfPermission(thisActivity,
                Permission[0])
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Permission[0])) {
            } else {
                ActivityCompat.requestPermissions(thisActivity, Permission,
                        Code);
            }
        }
    }

    public boolean CheckPermission(Context context, String Permission) {
        if (ContextCompat.checkSelfPermission(context,
                Permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
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
