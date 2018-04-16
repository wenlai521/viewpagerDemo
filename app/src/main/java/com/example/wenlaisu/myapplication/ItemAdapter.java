package com.example.wenlaisu.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wenlaisu on 2018/4/12.
 */

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyHolder> {
    private List<String> data;

    public ItemAdapter(Context mContext, List<String> mDatas) {
        data = mDatas;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.text.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return null == data ? 0 : data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
