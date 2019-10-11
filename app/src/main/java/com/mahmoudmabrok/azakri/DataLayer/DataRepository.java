package com.mahmoudmabrok.azakri.DataLayer;

import android.content.Context;

import com.mahmoudmabrok.azakri.DataLayer.LocalData.PrefHelper;

public class DataRepository {

    private PrefHelper mPref;

    public DataRepository(Context context) {
        mPref = new PrefHelper(context);
    }

    public void addMasa() {
        mPref.addMasa();
    }

    public void addSabah() {
        mPref.addSabah();
    }

    public int getSabahCount() {
        return mPref.getSabahCount();
    }

    public int getMasaCount() {
        return mPref.getMasaCount();
    }


}
