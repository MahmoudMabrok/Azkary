package com.mahmoudmabrok.azakri.DataLayer.LocalData;

import android.content.Context;
import android.content.SharedPreferences;

import com.mahmoudmabrok.azakri.Util.Constants;

public class PrefHelper {

    private static final String SHARED_NAME = "zeker";

    private static SharedPreferences sharedPreferences;

    public PrefHelper(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        }
    }


    public void addSabah() {
        int prev = sharedPreferences.getInt(Constants.sabah_pref_key, 0);
        prev++;
        setSabahCount(prev);

    }

    public void addMasa() {
        int prev = sharedPreferences.getInt(Constants.masa_pref_key, 0);
        prev++;
        setMasaCount(prev);

    }

    public int getSabahCount() {
        return sharedPreferences.getInt(Constants.sabah_pref_key, 0);
    }

    public void setSabahCount(int count) {
        sharedPreferences.edit().putInt(Constants.sabah_pref_key, count).apply();
    }

    public int getMasaCount() {
        return sharedPreferences.getInt(Constants.masa_pref_key, 0);
    }

    public void setMasaCount(int count) {
        sharedPreferences.edit().putInt(Constants.masa_pref_key, count).apply();
    }


}
