package com.mahmoudmabrok.azakri;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mahmoudmabrok.azakri.adapters.CardAdapter;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * Created by motamed on 3/26/2018.
 */

public class Sabah extends Activity {

    private ArrayList<String> mDate;
    private CardAdapter mCardAdapter;
    private SwipeStack swipeStack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabah);
        Data data = new Data();
        mDate = data.getSabah();

        mCardAdapter = new CardAdapter(this, mDate);
        swipeStack = (SwipeStack) findViewById(R.id.swipeStackSabah);
        swipeStack.setAdapter(mCardAdapter);
    }


    public void refreshData(View view) {
        swipeStack.resetStack();
    }
}


