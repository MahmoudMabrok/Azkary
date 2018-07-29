package com.mahmoudmabrok.azakri;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by motamed on 3/26/2018.
 */

public class ZekerAdapter extends ArrayAdapter<Zeker> {
    private final int backgroundID;

    public ZekerAdapter(@NonNull Context context, @NonNull List<Zeker> objects, int resourceId) {
        super(context, 0, objects);
        backgroundID = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Zeker curent = getItem(position);

        TextView tv = (TextView) item.findViewById(R.id.item);
        tv.setText(curent.getName());

        //get equivalent color
        int color = ContextCompat.getColor(getContext(), backgroundID);
        //make background as its category
        tv.setBackgroundColor(color);

        if (backgroundID == R.color.categor_sabah) {
            color = ContextCompat.getColor(getContext(), R.color.black);
            tv.setTextColor(color);
        }

        return item;
    }
}
