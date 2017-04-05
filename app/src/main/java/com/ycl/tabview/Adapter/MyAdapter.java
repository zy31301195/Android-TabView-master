package com.ycl.tabview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.Util.MyViewHolder;
import com.ycl.tabview.View.EasyCountDownTextureView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycl.tabview.Activity.RegisterActivity.school_name;

/**
 * Created by Administrator on 2017/3/24.
 */

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Exam> mData;
    public int[] imageids = {R.drawable.jisuan, R.drawable.chuanmei, R.drawable.shang, R.drawable.xindian, R.drawable.gongcheng, R.drawable.yixue, R.drawable.faxue, R.drawable.waiguoyu, R.drawable.chuangyi};//图表id

    private OnRecycleItemClick onRecycleitemClick = null;
    private EasyCountDownTextureView countdownText;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MyAdapter(List<Exam> data) {
        this.mData = data;
    }

    public void setOnItemClickListener(OnRecycleItemClick onItemClickListener) {
        onRecycleitemClick = onItemClickListener;
    }


    public interface OnRecycleItemClick {
        void onItemClick(View view, Object object);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        itemView.setOnClickListener(this);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        Map<String, Object> list = new HashMap<String, Object>();
        for (int i = 0; i < school_name.length; i++) {
            list.put(school_name[i], imageids[i]);
        }
        myholder.iv.setImageResource((Integer) list.get(this.mData.get(position).getExam_school()));
        myholder.tv_dates.setText(this.mData.get(position).getExam_date());
        myholder.tv_name.setText(this.mData.get(position).getExam_name());
        myholder.prices.setText(this.mData.get(position).getExam_prices());
        try {
            Date date = df.parse(this.mData.get(position).getExam_date() + " 00:00:00");
            long t = date.getTime() - System.currentTimeMillis();
            myholder.mEasyCountDownTextureView.setTime(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myholder.itemView.setTag(this.mData.get(position));

    }

    @Override
    public void onClick(View v) {
        if (onRecycleitemClick != null) {
            onRecycleitemClick.onItemClick(v, v.getTag());
        }
    }

}
