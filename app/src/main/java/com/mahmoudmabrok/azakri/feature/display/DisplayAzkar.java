package com.mahmoudmabrok.azakri.feature.display;

import android.app.Activity;
import android.os.Bundle;

import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.Util.Constants;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayAzkar extends Activity {

    @BindView(R.id.rvDisplay)
    RecyclerView mRvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_azkar);
        ButterKnife.bind(this);

        String type = getIntent().getStringExtra(Constants.ZEKER_TYPE);
    }
}
