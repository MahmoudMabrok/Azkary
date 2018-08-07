package com.mahmoudmabrok.azakri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.mahmoudmabrok.azakri.DataSet.Data;
import com.mahmoudmabrok.azakri.adapters.CardAdapter;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * Created by motamed on 3/26/2018.
 */

public class Sabah extends Activity implements CardAdapter.ZekerItemClicker {

    int latestItem = -1;
    int count;
    private ArrayList<Zeker> mDate;
    private CardAdapter mCardAdapter;
    private SwipeStack swipeStack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabah);

        //region DataSet
        final Data data = new Data();
        mDate = data.getSabah();
        //endregion

        //region swip Maker
        mCardAdapter = new CardAdapter(this, mDate, this);
        swipeStack = (SwipeStack) findViewById(R.id.swipeStackSabah);

        swipeStack.setAdapter(mCardAdapter);
        //endregion

        //region swip Listner
        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                count = mDate.get(position).getCount();
            }

            @Override
            public void onViewSwipedToRight(int position) {
                count = mDate.get(position).getCount();
            }

            @Override
            public void onStackEmpty() {
                Toast.makeText(Sabah.this, getString(R.string.finish), Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Sabah.this, MainActivity.class);
                startActivity(intent);
            }

        });

        //endregion

    }

    public void refreshData(View view) {

        swipeStack.resetStack();
    }

    public void goNext(View view) {
        swipeStack.swipeTopViewToLeft();
    }

    @Override
    public void onClick(int index) {
      /*  int value = Integer.parseInt()*/ // TODO: 8/7/2018 implement in adapter
    }

}


