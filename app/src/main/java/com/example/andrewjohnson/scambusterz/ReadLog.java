package com.example.andrewjohnson.scambusterz;
import android.database.Cursor;
import android.content.Context;
import android.provider.CallLog;

public class ReadLog {

    private Context mContext;
    public ReadLog(Context aContext){
        mContext = aContext;
    }

    public void accessLogs(){
        String[] projection = new String[] {
                CallLog.Calls.NUMBER
        };


        Cursor c = mContext.getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, null, null, CallLog.Calls.DATE + " DESC");

        if(c.getCount()>0){

            c.moveToFirst();
//            do{
//                String callerNumber = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
//                int callType = c.getInt(c.getColumnIndex(CallLog.Calls.TYPE));
//
//                if(callType == CallLog.Calls.MISSED_TYPE){
//
//                }
//            }while(c.moveToNext());

        }
    }



}
