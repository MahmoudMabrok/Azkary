package com.mahmoudmabrok.azakri;

import android.app.Application;

import com.mahmoudmabrok.azakri.DataLayer.DataRepository;

public class App extends Application {


    private DataRepository dataRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        dataRepository = new DataRepository(this);

    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }
}
