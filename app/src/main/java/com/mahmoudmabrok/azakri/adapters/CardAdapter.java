package com.mahmoudmabrok.azakri.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.Zeker;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mahmoud on 7/25/2018.
 */

public class CardAdapter extends BaseAdapter {

    ArrayList<Zeker> mData;
    Context context;
    LayoutInflater mInflater;

    private ZekerItemClicker mClickListener;

    //region constructor
    public CardAdapter(Context context, ArrayList<Zeker> mData, ZekerItemClicker mClickListener) {
        this.mData = mData;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mClickListener = mClickListener;
    }
    //endregion

    //region default settings
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
    //endregion


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.card, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Zeker zeker = mData.get(position);
        viewHolder.bindData(zeker, position);
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

        //  boolean isNeed = true ;
        //region allah match
        while (m.find()) {
            start = m.start();
            while (zeker.charAt(start) != ' ' && start != 0) {
                start--;
            }
            end = m.end();
            if (zeker.charAt(end) == 'ي') {
                //case not word we need
            } else {
                while (zeker.charAt(end) != ' ') {
                    end++;
                }
                spannable.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }
        //endregion
        //another case Span
        //region salah wa salm match
        m = p2.matcher(zeker);
        if (m.find()) {
            spannable.setSpan(new ForegroundColorSpan(Color.RED), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        start = zeker.indexOf('(');
        end = zeker.indexOf(')');
        if (start >= 0 && end >= 0) {
            spannable.setSpan(new ForegroundColorSpan(Color.BLUE), start + 1, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //endregion

        return spannable;

    }

    public interface ZekerItemClicker {
        void onClick(int index);
    }

    class ViewHolder implements View.OnClickListener {
        private static final String TAG = "ViewHolder";
        int position;

        TextView tvZeker;
        TextView tvCount;

        public ViewHolder(View view) {
            tvZeker = (TextView) view.findViewById(R.id.textViewCard);
            tvCount = (TextView) view.findViewById(R.id.tvZekerCount);
            tvZeker.setOnClickListener(this);  // is sufficient now
        }

        @Override
        public void onClick(View v) {
            mClickListener.onClick(position);
        }

        //region bind Data
        public void bindData(Zeker zeker, int position) {
            this.position = position;
            Log.v(TAG, position + "");
            String zekerText = String.valueOf(position + 1) + "-" + zeker.getName();
            //// TODO: 8/7/2018 solve problem
            Spannable spannable = getSpannable(zekerText);
            //tvZeker.setText(spannable, TextView.BufferType.SPANNABLE);
            tvZeker.setText(zekerText);
            tvCount.setText("" + zeker.getCount());
        }
        //endregion
    }
}
