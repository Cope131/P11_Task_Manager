package com.myapplicationdev.android.p11_task_manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    int requestCode = 123;
    int notificationID = 888;

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getExtras().getString("name");
        String description = intent.getExtras().getString("description");
        Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.sds);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            channel.setDescription("This is for default notification");
            channel.enableLights(true);
            channel.setSound(sound, attributes);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, i, PendingIntent.FLAG_CANCEL_CURRENT);

        Bitmap pic = BitmapFactory.decodeResource(context.getResources(), R.drawable.sentosa);


        //build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(name + "\n" + description);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setLargeIcon(pic);
        builder.setContentIntent(pendingIntent);
        builder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(pic)
                .bigLargeIcon(null));
        builder.setAutoCancel(true);
        builder.setSound(sound);
        builder.setVibrate(new long[] { 1000, 1000});
        builder.setLights(Color.RED, 3000, 3000);

        Notification n = builder.build();
        notificationManager.notify(123, n);
    }
}