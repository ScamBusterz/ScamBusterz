package com.example.andrewjohnson.scambusterz;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

public class Notifications extends AppCompatActivity {
    private final String CHANNEL_ID = "Spam Caller";
    private final int NOTIFICATION_ID = 001;

    public void displayNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_priority_high_notification)
                .setContentTitle("SPAM CALLER")
                .setContentText("This phone number has a high likelihood of being spam")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}
