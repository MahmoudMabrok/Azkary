package com.mahmoudmabrok.azakri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showSabah(View view) {
        Intent i = new Intent(this, Sabah.class);
        startActivity(i);
    }

    public void showMasa(View view) {
        Intent i = new Intent(this, Masa.class);
        startActivity(i);
    }
}
