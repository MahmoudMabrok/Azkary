package com.mahmoudmabrok.azakri.feature.display;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.mahmoudmabrok.azakri.App;
import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.Zeker;
import com.mahmoudmabrok.azakri.dataLayer.DataRepository;
import com.mahmoudmabrok.azakri.dataSet.Data;
import com.mahmoudmabrok.azakri.util.Constants;
import com.mahmoudmabrok.azakri.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayAzkar extends Activity implements ZekerAdapter.ZekerStateListner {

    @BindView(R.id.rvDisplay)
    RecyclerView mRvDisplay;

    private DataRepository dataRepository;
    private List<Zeker> zekerList;
    private int lastClicked;
    private boolean isSabah;


    private static final String TAG = "DisplayAzkar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_azkar);
        ButterKnife.bind(this);
        dataRepository = ((App) getApplication()).getDataRepository();

        String type = getIntent().getStringExtra(Constants.ZEKER_TYPE);
        if (type.equals(Constants.SABAH)) {
            zekerList = new Data().getSabah();
            isSabah = true;
        } else {
            zekerList = new Data().getMasa();

        }
        initRv();
        scrollRvToLastPos();
    }

    public void scrollRvToLastPos() {
        int pos;
        if (isSabah) {
            pos = dataRepository.getLastPosSabah();
        } else {
            pos = dataRepository.getLastPosMasa();
        }
        Log.d(TAG, "scrollRvToLastPos: " + pos);
        mRvDisplay.scrollToPosition(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    private void initRv() {
        ZekerAdapter adapter = new ZekerAdapter(this);
        adapter.setZekerList(zekerList);
        mRvDisplay.setAdapter(adapter);
    }

    @Override
    public void onFinish() {
        lastClicked = 0;
        if (isSabah) {
            dataRepository.addSabah();
        } else {
            dataRepository.addMasa();
        }
        new CountDownTimer(500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Toast.makeText(DisplayAzkar.this, "انتهيت من الأذكار", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();

    }
    @Override
    public void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
    @Override
    public void onDisplayed(int pos) {
        lastClicked = pos;
        Log.d(TAG, "onDisplayed: " + pos);
    }

    @Override
    public void onShareClick(String item) {
        Util.share(this, item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isSabah) {
            dataRepository.addLastPosSabah(lastClicked);
        } else
            dataRepository.addLastPosMasa(lastClicked);
        Log.d(TAG, "onStop: " + lastClicked);
    }
}
