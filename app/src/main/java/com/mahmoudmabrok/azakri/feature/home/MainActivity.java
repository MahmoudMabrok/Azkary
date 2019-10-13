package com.mahmoudmabrok.azakri.feature.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mahmoudmabrok.azakri.App;
import com.mahmoudmabrok.azakri.DataLayer.DataRepository;
import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.Util.Constants;
import com.mahmoudmabrok.azakri.ZekerReceiver;
import com.mahmoudmabrok.azakri.feature.display.DisplayAzkar;
import com.tjeannin.apprate.AppRate;

import java.util.Calendar;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindString(R.string.exit_ok)
    String exitPostivitive;
    @BindString(R.string.exit_no)
    String exitNegative;
    @BindString(R.string.exit_title)
    String exitTitle;
    @BindString(R.string.exit_message)
    String exitMessage;
    @BindView(R.id.sabah)
    Button mSabah;
    @BindView(R.id.masa)
    Button mMasa;

    private DataRepository dataRepository;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        dataRepository = ((App) getApplication()).getDataRepository();

        //AppRate in OnCreate of Activity u want to show in
        new AppRate(this)
                .setMinDaysUntilPrompt(5)
                .setMinLaunchesUntilPrompt(10)
                .setShowIfAppHasCrashed(true)
                .init();

        makeAlarm();

    }

    private void makeAlarm() {

        Intent intent = new Intent(this, ZekerReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        int nHour = 1;
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 1000 * 60 * 60 * nHour, pendingIntent);


        calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 1000 * 60 * 60 * nHour, pendingIntent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send_feedback:
                Toast.makeText(this, "" + dataRepository.getSabahCount(), Toast.LENGTH_SHORT).show();
                // alarmManager.cancel(pendingIntent);
                break;
            case R.id.action_setting:
                // makeAlarm();
                showToast(this, "" + dataRepository.getMasaCount(), 1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .positiveText(exitPostivitive)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                      /*  MainActivity.this.finishAffinity(); //claose all current stack activity
                        System.exit(0); // free up all resources
                   */
                        finish();

                    }
                })
                /*   .negativeText(exitNegative)
                   .onNegative(new MaterialDialog.SingleButtonCallback() {
                       @Override
                       public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                       }
                   })*/
                .title(exitTitle)
                .content(exitMessage)
                .show();


/*
        DialogeMaker.makeDialog(MainActivity.this , exitTitle , exitMessage).show();
        */
/*
   new  AlertDialog.Builder(MainActivity.this).setTitle(exitTitle).setMessage(exitMessage).create().show();
*/


    }

    public void showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void showToast(Context context, String text, int x) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);

        x = R.dimen._100sdp;

        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, x, 200);
        toast.show();

    }

    @OnClick(R.id.sabah)
    public void onMSabahClicked() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1F, 0.7F);
        alphaAnimation.setDuration(1000);
        mSabah.startAnimation(alphaAnimation);
        mSabah.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                openDisplay(Constants.SABAH);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @OnClick(R.id.masa)
    public void onMMasaClicked() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1F, 0.7F);
        alphaAnimation.setDuration(1000);
        mMasa.startAnimation(alphaAnimation);
        mMasa.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                openDisplay(Constants.MASA);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    private void openDisplay(String zekerType) {
        Intent openAcivity = new Intent(MainActivity.this, DisplayAzkar.class);
        openAcivity.putExtra(Constants.ZEKER_TYPE, zekerType);
        startActivity(openAcivity);
    }


}
