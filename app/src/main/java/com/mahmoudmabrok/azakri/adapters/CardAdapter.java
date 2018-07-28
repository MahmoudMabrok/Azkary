package com.mahmoudmabrok.azakri.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;

import com.mahmoudmabrok.azakri.R;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 7/25/2018.
 */

public class CardAdapter extends BaseAdapter {

    ArrayList<String> mData;
    Context context;
    LayoutInflater mInflater;

    public CardAdapter(Context context, ArrayList<String> mData) {
        this.mData = mData;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.card, parent, false);

        String zeker = mData.get(position);
        WebView view = (WebView) convertView.findViewById(R.id.webView);
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        view.loadData(String.format(htmlText, zeker), "text/html", "utf-8");

        return convertView;

    }

    public void refreshData(ArrayList<String> list) {
        //    mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }


}
