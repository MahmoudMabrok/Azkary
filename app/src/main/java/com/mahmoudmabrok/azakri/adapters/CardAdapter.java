package com.mahmoudmabrok.azakri.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mahmoudmabrok.azakri.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      /*  WebView view = (WebView) convertView.findViewById(R.id.webView);
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        view.loadData(String.format(htmlText, zeker), "text/html", "utf-8");*/


        TextView textView = (TextView) convertView.findViewById(R.id.textViewCard);
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("<html><body style=\"text-align:justify\"> " + zeker + " </body></Html>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(zeker);
        }
*/
        String text2 = zeker;

        Spannable spannable = new SpannableString(text2);


        String REGEX = "لل";
        Pattern p = Pattern.compile(REGEX);
        //  get a matcher object
        Matcher m = p.matcher(zeker);

        int start, end;

        while (m.find()) {
            start = m.start();
            while (zeker.charAt(start) != ' ' && start != 0) {
                start--;
            }
            end = m.end();
            while (zeker.charAt(end) != ' ') {
                end++;
            }

            spannable.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannable, TextView.BufferType.SPANNABLE);
        return convertView;

    }

}
