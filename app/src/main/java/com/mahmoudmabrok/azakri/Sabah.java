package com.mahmoudmabrok.azakri;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by motamed on 3/26/2018.
 */

public class Sabah extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabah);

        Data data = new Data();

/*
        ZekerAdapter zekerAdapter = new ZekerAdapter( this ,data.getSabah() , R.color.categor_sabah) ;
        ListView l = (ListView)findViewById(R.id.listS);
        l.setAdapter(zekerAdapter);*/


    }
}
