package com.mahmoudmabrok.azakri;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mahmoudmabrok.azakri.DataLayer.DataRepository;
import com.tjeannin.apprate.AppRate;

import java.util.Calendar;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindString(R.string.exit_ok)
    String exitPostivitive;
    @BindString(R.string.exit_no)
    String exitNegative;
    @BindString(R.string.exit_title)
    String exitTitle;
    @BindString(R.string.exit_message)
    String exitMessage;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    private DataRepository dataRepository;


    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        dataRepository = ((App) getApplication()).getDataRepository();
        setSupportActionBar(toolbar);


        //AppRate in OnCreate of Activity u want to show in
        new AppRate(this)
                .setMinDaysUntilPrompt(5)
                .setMinLaunchesUntilPrompt(10)
                .setShowIfAppHasCrashed(false)
                .init();

    }

    private void makeAlarm() {

        //   Intent intent = new Intent(this , AzjarService.class);

     /*   pendingIntent = PendingIntent.getService(this , 0 ,
                                        intent ,PendingIntent.FLAG_UPDATE_CURRENT);
*/

        Intent intent = new Intent(this, ZekerReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, 4);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);


        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, pendingIntent);


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
                alarmManager.cancel(pendingIntent);
                break;
            case R.id.action_setting:
                makeAlarm();
                showToast(this, "" + dataRepository.getMasaCount(), 1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSabah(View view) {
        Intent i = new Intent(this, Sabah.class);
        startActivity(i);

    }

    public void showMasa(View view) {
        Intent i = new Intent(this, Masa.class);
        startActivity(i);
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
        DialogeMaker.makeDialog(this, exitMessage, exitPostivitive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "stay", Toast.LENGTH_SHORT).show();
            }
        }, exitNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();
            }
        }).show();
*/
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
}
