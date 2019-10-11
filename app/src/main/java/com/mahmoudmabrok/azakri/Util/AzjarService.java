package com.mahmoudmabrok.azakri.Util;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.mahmoudmabrok.azakri.BuildConfig;
import com.mahmoudmabrok.azakri.MainActivity;
import com.mahmoudmabrok.azakri.R;

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

        Intent mainINtent = new Intent(AzjarService.this, MainActivity.class);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int id = 11;
        if (Build.VERSION.SDK_INT >= O) {
            NotificationChannel notificationChannel = new NotificationChannel("nn", "azkar", NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(notificationChannel);


            Notification notification = new Notification.Builder(getApplicationContext(), "nn")
                    .setAutoCancel(true)
                    .setContentTitle("Hint")
                    .setContentText(" لا تنسي الاذكار")
                    .setSmallIcon(R.drawable.ic_menu_next)
                    .build();

            notificationManager.notify(11, notification);

        } else {

            Notification notification = new Notification.Builder(getApplicationContext())
                    .setAutoCancel(true)
                    .setContentTitle("Hint")
                    .setContentText(" لا تنسي الاذكار")
                    .setSmallIcon(R.drawable.ic_menu_next)
                    .build();

            notificationManager.notify(11, notification);

        }
    }
}
