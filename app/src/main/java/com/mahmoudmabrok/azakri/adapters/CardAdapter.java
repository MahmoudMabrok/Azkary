package com.mahmoudmabrok.azakri.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
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

    ViewGroup mViewGroup;

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

        mViewGroup = parent;
        convertView = mInflater.inflate(R.layout.card, parent, false);

        String zeker = mData.get(position);

        TextView textView = (TextView) convertView.findViewById(R.id.textViewCard);

        Spannable spannable = getSpannable(zeker);

        textView.setText(spannable, TextView.BufferType.SPANNABLE);
        return convertView;

    }

    public Spannable getSpannable(String zeker) {

        Spannable spannable = new SpannableString(zeker);


        String REGEX = "لل";
        String REGEX2 = "ﷺ";
        Pattern p = Pattern.compile(REGEX);
        Pattern p2 = Pattern.compile(REGEX2);
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

        //another case Span
        m = p2.matcher(zeker);
        if (m.find()) {
            spannable.setSpan(new ForegroundColorSpan(Color.RED), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        start = zeker.indexOf('(');
        end = zeker.indexOf(')');
        if (start >= 0 && end >= 0) {
            spannable.setSpan(new ForegroundColorSpan(Color.BLUE), start + 1, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannable;

    }


}
