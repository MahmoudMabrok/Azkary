package com.mahmoudmabrok.azakri;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import static android.os.Build.VERSION_CODES.O;

public class ZekerReceiver extends BroadcastReceiver {

    private static final String TAG = "ZekerReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: ");

        /*int type = intent.getIntExtra("type" , -1 ) ;
        if (type == 0 ){

        }else  if (type == 1 ){

        }*/


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int id = (int) (Math.random() * 2222);

        if (Build.VERSION.SDK_INT >= O) {
            NotificationChannel notificationChannel = new NotificationChannel("nn", "azkar", NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(notificationChannel);


            Notification notification = new Notification.Builder(context, "nn")
                    .setAutoCancel(true)
                    .setContentTitle("Hint")
                    .setContentText(" لا تنسي الاذكار")
                    .setSmallIcon(R.drawable.ic_menu_next)
                    .build();

            notificationManager.notify(id, notification);

        } else {

            Notification notification = new Notification.Builder(context)
                    .setAutoCancel(true)
                    .setContentTitle("Hint")
                    .setContentText(" لا تنسي الاذكار")
                    .setSmallIcon(R.drawable.ic_menu_next)
                    .build();

            notificationManager.notify(id, notification);

        }
    }
}
