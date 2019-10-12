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


    public void addLastPosSabah(int pos) {
        mPref.addLastPosSabah(pos);
    }

    public int getLastPosSabah() {
        return mPref.getLastPosSabah();
    }

    public void addLastPosMasa(int pos) {
        mPref.addLastPosMasa(pos);
    }

    public int getLastPosMasa() {
        return mPref.getLastPosMasa();
    }


}
