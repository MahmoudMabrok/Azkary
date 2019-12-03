package com.mahmoudmabrok.azakri;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;

import com.mahmoudmabrok.azakri.dataLayer.DataRepository;

public class App extends Application {


    private DataRepository dataRepository;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();
        dataRepository = new DataRepository(this);
        startAlarmNotification();
    }

    private void startAlarmNotification() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }
}
