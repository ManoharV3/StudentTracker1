package com.example.manoh.studenttrack;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by manoh on 4/9/2018.
 */

public class Notification_Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }
    public void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this).setSmallIcon(R.drawable.download).setContentTitle("Notifications Example").setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, Notification_Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}

