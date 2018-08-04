package com.mahmoudmabrok.azakri;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.mahmoudmabrok.azakri.adapters.CardAdapter;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * Created by motamed on 3/26/2018.
 */

public class Sabah extends Activity {

    int latestItem = -1;
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

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {
               }


            @Override
            public void onStackEmpty() {
                Toast.makeText(Sabah.this, getString(R.string.finish), Toast.LENGTH_SHORT).show();
            }

        });

    }


    public void refreshData(View view) {

        swipeStack.resetStack();
       /* latestItem = -1;*/
    }

    public void goBack(View view) {
/*
        int local = latestItem ;
        if (local >= 0) {

            String z = mDate.get(local) ;
       //     Toast.makeText(this, "" + z, Toast.LENGTH_SHORT).show();
            mDate.add(local+1 , z);
         //   Toast.makeText(this, "pos" + swipeStack.getCurrentPosition(), Toast.LENGTH_SHORT).show();

            mDate.clear();
            mCardAdapter.notifyDataSetChanged();

            }
*/
/*
        mDate.clear();
        mCardAdapter.notifyDataSetChanged();

        mDate.add("Ahh");
        mCardAdapter.notifyDataSetChanged();
      */
    }

    public void goNext(View view) {
        swipeStack.swipeTopViewToLeft();
    }
}


