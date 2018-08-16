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


public class Masa extends Activity implements CardAdapter.ZekerItemClicker {


    @BindView(R.id.swipeStack)
    SwipeStack swipeStack;
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

    private int count;


    private DataRepository dataRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.masa);
        ButterKnife.bind(this);

        dataRepository = ((App) getApplication()).getDataRepository();

        Data data = new Data();
        mDate = data.getMasa();

        mCardAdapter = new CardAdapter(this, mDate, this);
        swipeStack.setAdapter(mCardAdapter);

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
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
                Toast.makeText(Masa.this, finishText, Toast.LENGTH_SHORT).show();
                //region go to Main
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                dataRepository.addMasa();
                Intent intent = new Intent(Masa.this, MainActivity.class);
                startActivity(intent);
                finish();
                //endregion
            }

        });
    }

    public void setTvCountText(int number) {
        tvCount.setText(String.valueOf(number));
    }

    public void refreshData(View view) {
        swipeStack.resetStack();
        count = 1;
        setTvCountText(count);
    }

    public void goNext(View view) {
        swipeStack.swipeTopViewToLeft();
    }

    @OnClick(R.id.tvCount)
    public void onClick(TextView textView) {
        count--;
        if (count <= 0) {
            swipeStack.swipeTopViewToLeft();
        } else {
            setTvCountText(count);
        }
    }

    //region onClick Swip Card
    @Override
    public void onClick(int index) {
//        Toast.makeText(this, "sasasasa", Toast.LENGTH_SHORT).show();
    }
    //endregion
}
