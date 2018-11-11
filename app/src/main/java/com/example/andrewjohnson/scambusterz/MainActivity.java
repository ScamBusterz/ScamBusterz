package com.example.andrewjohnson.scambusterz;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Build;
import android.app.NotificationChannel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;

// Parse JSON
import org.json.JSONObject;
import org.json.JSONArray;
//import org.json.JSONException;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONString;
//import org.json.JSONStringer;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class MainActivity extends AppCompatActivity {
    private final String CHANNEL_ID = "Spam Caller";

    // API call
    private OkHttpClient okHttpClient;
    private Request request;
    private String urlFirst = "https://proapi.whitepages.com/3.0/phone_reputation?phone=";
    private String urlLast = "&api_key=27b32d68aa1142948d336ef2b3cae8cf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiCall("9991110003");
        createNotificationChannel();
        Button btn = (Button) findViewById(R.id.notifyButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationCall();
            }
        });
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

    private void apiCall(String phoneNumber){
        okHttpClient = new OkHttpClient();
        String url = urlFirst + phoneNumber + urlLast;
        request = new Request.Builder().url(url).build();

        final String jsonResponse = "Nothing";
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("\n\n" + jsonResponse +"\n");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("\n\n");

                String bodyString = response.body().string();
                System.out.println(bodyString);
                JSONObject json;

                try {
                    json = new JSONObject(bodyString);
                } catch (JSONException e) {
                    json = null;

                }
                //List<String> list = new ArrayList<String>();
                JSONObject innerObj;
                try {
                    innerObj = json.getJSONObject("reputation_details");
                } catch (JSONException e) {
                    innerObj = null;
                }
                String riskType;
                System.out.println(innerObj);
                if (innerObj != null) {
                    try {
                        riskType = innerObj.getString("type");

                    }
                    catch(JSONException e) {
                        riskType ="It didnt work";

                    }
                    System.out.println(riskType);
                }
                else {
                    System.out.println("innerObj was null\n");
                }
            }





        });
    }

}
