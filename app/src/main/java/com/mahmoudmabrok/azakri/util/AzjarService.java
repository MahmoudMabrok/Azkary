package com.mahmoudmabrok.azakri.util;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.mahmoudmabrok.azakri.BuildConfig;
import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.feature.display.DisplayAzkar;

import static android.os.Build.VERSION_CODES.O;

public class AzjarService extends IntentService {

    private static final String TAG = "AzjarService";

    public AzjarService() {
        super("Sercoooo");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (BuildConfig.DEBUG)
            Log.d(TAG, "onHandleIntent: ");

        String type = intent.getStringExtra(Constants.ZEKER_TYPE);
        String message, tittle = "اذكار الصباح والمساء";
        // used to open display with its type directly
        Intent zeker = new Intent(AzjarService.this, DisplayAzkar.class);
        int drawableId;

        if (type.equals(Constants.SABAH)) {
            message = "اذكار الصباح";
            zeker.putExtra(Constants.ZEKER_TYPE, Constants.SABAH);
            drawableId = R.drawable.ic_isun;
        } else {
            message = "اذكار المساء";
            zeker.putExtra(Constants.ZEKER_TYPE, Constants.MASA);
            drawableId = R.drawable.ic_night;
        }
        Log.d(TAG, "onHandleIntent: ");

        PendingIntent pendingIntent = PendingIntent.getActivities(AzjarService.this,
                1000, new Intent[]{zeker}, PendingIntent.FLAG_UPDATE_CURRENT);
        
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int id = 11;

        if (Build.VERSION.SDK_INT >= O) {
            NotificationChannel notificationChannel = new NotificationChannel("nn", "azkar", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification notification = new Notification.Builder(getApplicationContext(), "nn")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(tittle)
                    .setContentText(message)
                    .setSmallIcon(drawableId)
                    .build();

            notificationManager.notify(11, notification);

        } else {

            Notification notification = new Notification.Builder(getApplicationContext())
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(tittle)
                    .setContentText(message)
                    .setSmallIcon(drawableId)
                    .build();

            notificationManager.notify(11, notification);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
