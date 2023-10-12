package com.example.pandacare;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pandacare.PandaLove.PandaLove;


public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // on tap will open Panda Love activity
        Intent repeatingIntent = new Intent(context, PandaLove.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // getting the pendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // building the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"Notification")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.red_panda_main)
                .setContentTitle("PandaCare")
                .setContentText("Panda is here to remind you about your bad habits and live a good habit life!")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        // send notification
        notificationManagerCompat.notify(200,builder.build());

    }


}