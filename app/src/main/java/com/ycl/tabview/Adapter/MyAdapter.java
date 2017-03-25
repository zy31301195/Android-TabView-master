package com.ycl.tabview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.tabview.Bean.MyItemBean;
import com.ycl.tabview.R;
import com.ycl.tabview.Util.MyViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private List<MyItemBean> mData;

    private OnRecycleItemClick onRecycleitemClick = null;

    public MyAdapter(List<MyItemBean> data){
        this.mData = data;
    }

    public void setOnItemClickListener(OnRecycleItemClick onItemClickListener) {
        onRecycleitemClick = onItemClickListener;
    }

    public interface OnRecycleItemClick{
        void onItemClick(View view, Object object);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        itemView.setOnClickListener(this);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        myholder.tv_dates.setText(this.mData.get(position).exam_date);
        myholder.tv_name.setText(this.mData.get(position).exam_name);
        myholder.prices.setText(String.valueOf(this.mData.get(position).prices));
        myholder.hour.setText(this.mData.get(position).hour);
        myholder.minutes.setText(this.mData.get(position).minutes);
        myholder.second.setText(this.mData.get(position).second);
        myholder.itemView.setTag(this.mData.get(position));

    }

    @Override
    public void onClick(View v) {
        if(onRecycleitemClick !=null){
            onRecycleitemClick.onItemClick(v,v.getTag());
        }
    }
}
