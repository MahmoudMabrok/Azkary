package com.mahmoudmabrok.azakri.DataLayer.LocalData;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper {

    private static final String SHARED_NAME = "zeker";

    private static SharedPreferences sharedPreferences;

    public PrefHelper(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        }
    }


    public void addSabah() {
        int prev = sharedPreferences.getInt("sabah", 0);
        prev++;
        setSabahCount(prev);

    }

    public void setSabahCount(int count) {
        sharedPreferences.edit().putInt("sabah", count).apply();
    }

    public void addMasa() {
        int prev = sharedPreferences.getInt("masa", 0);
        prev++;
        setMasaCount(prev);

    }

    public void setMasaCount(int count) {
        sharedPreferences.edit().putInt("masa", count).apply();
    }


}
