package com.mahmoudmabrok.azakri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mahmoudmabrok.azakri.DataLayer.DataRepository;
import com.mahmoudmabrok.azakri.DataSet.Data;
import com.mahmoudmabrok.azakri.adapters.CardAdapter;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import link.fls.swipestack.SwipeStack;

/**
 * Created by motamed on 3/26/2018.
 */

public class Sabah extends Activity implements CardAdapter.ZekerItemClicker {

    int count;

    @BindView(R.id.swipeStackSabah)
    SwipeStack swipeStackSabah;
    @BindView(R.id.button)
    ImageButton button;
    @BindView(R.id.btnBack)
    ImageButton btnBack;

    @BindView(R.id.tvCount)
    TextView tvCount;

    @BindString(R.string.finish)
    String finishText;

    private ArrayList<Zeker> mDate;
    private CardAdapter mCardAdapter;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabah);
        ButterKnife.bind(this);

        dataRepository = ((App) getApplication()).getDataRepository();

        //region DataSet
        final Data data = new Data();
        mDate = data.getSabah();
        //endregion

        //region swip Maker
        mCardAdapter = new CardAdapter(this, mDate, this);
        swipeStackSabah.setAdapter(mCardAdapter);
        //endregion

        //region swip Listner
        swipeStackSabah.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                if (position <= mDate.size() - 2) {
                    count = mDate.get(position + 1).getCount();
                    setTvCountText(count);
                }
            }

            @Override
            public void onViewSwipedToRight(int position) {
                if (position <= mDate.size() - 2) {
                    count = mDate.get(position + 1).getCount();
                    setTvCountText(count);
                }
            }

            @Override
            public void onStackEmpty() {
                Toast.makeText(Sabah.this, finishText, Toast.LENGTH_SHORT).show();
                //region go to Main
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                dataRepository.addSabah();
                Intent intent = new Intent(Sabah.this, MainActivity.class);
                startActivity(intent);
                finish();
                //endregion
            }

        });

        //endregion

    }

/*
    public void refreshData(View view) {

        swipeStackSabah.resetStack();
    }
*/

    public void setTvCountText(String text) {
        tvCount.setText(text);
    }

    public void setTvCountText(int number) {
        tvCount.setText(String.valueOf(number));
    }

    public void goNext(View view) {
        swipeStackSabah.swipeTopViewToLeft();
    }

    @Override
    public void onClick(int index) {
      /*  int value = Integer.parseInt()*/ // TODO: 8/7/2018 implement in adapter
    }

    @OnClick(R.id.button)
    public void onButtonClicked() {
        swipeStackSabah.resetStack();
        count = 1;
        setTvCountText(count);
    }

    @OnClick(R.id.tvCount)
    public void onTvCountClicked() {
        count--;
        if (count <= 0) {
            swipeStackSabah.swipeTopViewToLeft();
        } else {
            setTvCountText(count);
        }
    }
}


