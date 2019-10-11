package com.mahmoudmabrok.azakri.feature.display;

import android.app.Activity;
import android.os.Bundle;

import com.mahmoudmabrok.azakri.App;
import com.mahmoudmabrok.azakri.DataLayer.DataRepository;
import com.mahmoudmabrok.azakri.DataSet.Data;
import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.Util.Constants;
import com.mahmoudmabrok.azakri.Zeker;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayAzkar extends Activity {

    @BindView(R.id.rvDisplay)
    RecyclerView mRvDisplay;

    private DataRepository dataRepository;
    private List<Zeker> zekerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_azkar);
        ButterKnife.bind(this);
        dataRepository = ((App) getApplication()).getDataRepository();

        String type = getIntent().getStringExtra(Constants.ZEKER_TYPE);
        if (type.equals(Constants.SABAH)) {
            zekerList = new Data().getSabah();
        } else {
            zekerList = new Data().getMasa();
        }
        initRv();
    }

    private void initRv() {
        ZekerAdapter adapter = new ZekerAdapter();
        adapter.setZekerList(zekerList);
        mRvDisplay.setAdapter(adapter);
        mRvDisplay.setLayoutManager(new LinearLayoutManager(this));
    }
}
