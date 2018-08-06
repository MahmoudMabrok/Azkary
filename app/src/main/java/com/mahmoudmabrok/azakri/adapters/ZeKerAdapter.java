package com.mahmoudmabrok.azakri.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mahmoudmabrok.azakri.R;

import java.util.Locale;

/**
 * Created by Mahmoud on 8/5/2018.
 */

public class ZeKerAdapter extends ArrayAdapter<String> {

    public ZeKerAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;  //holder to facilitate bind Data

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.card, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String zeker = getItem(position);

        holder.textViewZeker.setText(zeker);
        holder.textViewCount.setText(String.format(Locale.getDefault(), "%d", position));

        return convertView;
    }

    public static class ViewHolder {

        private TextView textViewZeker;
        private TextView textViewCount;

        public ViewHolder(View view) {
            textViewZeker = (TextView) view.findViewById(R.id.textViewCard);
        }
    }
}
