package com.mahmoudmabrok.azakri.feature.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahmoudmabrok.azakri.R;
import com.mahmoudmabrok.azakri.Zeker;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ZekerAdapter extends RecyclerView.Adapter<ZekerAdapter.Holder> {

    private List<Zeker> list;
    private List<Zeker> tempList = new ArrayList<>();
    private ZekerStateListner zekerStateListner;

    public ZekerAdapter(Context context) {
        list = new ArrayList<>();
        zekerStateListner = (ZekerStateListner) context;
    }

    public void addZeker(Zeker item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, list.size());
    }


    public void setZekerList(List<Zeker> data) {
        list = new ArrayList<>(data);
        tempList = new ArrayList<>(data);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<Zeker> getList() {
        return list;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zeker_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Zeker item = list.get(i);
        holder.mTvTimes.setText(String.valueOf(item.getCount()));
        holder.mTvZeker.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    interface ZekerStateListner {
        void onFinish();
        void onDisplayed(int pos);
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvZeker)
        TextView mTvZeker;
        @BindView(R.id.tvTimes)
        TextView mTvTimes;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                int pos = getAdapterPosition();
                Zeker zeker = list.get(pos);
                int count = zeker.getCount();
                int originalPos = tempList.indexOf(zeker);
                if (count == 1) {
                    list.remove(pos);
                    notifyItemRemoved(pos);
                    notifyItemRangeRemoved(pos, list.size());
                    zekerStateListner.onDisplayed(originalPos + 1);
                    // check if it become empty
                    if (list.size() == 0) {
                        zekerStateListner.onFinish();
                        // to scroll to first item
                        zekerStateListner.onDisplayed(0);
                    }
                } else {
                    zeker.setCount(--count);
                    list.set(pos, zeker);
                    notifyItemChanged(pos);
                    zekerStateListner.onDisplayed(originalPos);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}