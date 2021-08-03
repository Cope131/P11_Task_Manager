package com.myapplicationdev.android.p11_task_manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    int requestCode = 123;
    int notificationID = 888;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, i, PendingIntent.FLAG_CANCEL_CURRENT);

        //build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setContentTitle("Task Manager Reminder!");
        builder.setContentText("Post Letters");
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Notification n = builder.build();
        notificationManager.notify(123, n);
    }
}