package com.ycl.tabview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycl.tabview.R;
import com.ycl.tabview.httpBean.OkExamsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class OkRecordListViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<OkExamsBean.AllRecordBean> mDatas;

    public OkRecordListViewAdapter(Context context, List<OkExamsBean.AllRecordBean> mDatas)
    {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OkRecordListViewAdapter.ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.record_item, parent,
                    false);
            viewHolder = new OkRecordListViewAdapter.ViewHolder();
            /*得到各个控件的对象*/
            viewHolder.record_name = (TextView)convertView.findViewById(R.id.record_name);
            viewHolder.record_time = (TextView) convertView.findViewById(R.id.record_time);
            viewHolder.tv_new_price = (TextView) convertView.findViewById(R.id.tv_new_price);


            convertView.setTag(viewHolder);//绑定ViewHolder对象
        } else
        {
            viewHolder = (OkRecordListViewAdapter.ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        viewHolder.record_name.setText(String.valueOf(this.mDatas.get(position).getUser_name()));
        viewHolder.record_time.setText(String.valueOf(this.mDatas.get(position).getRecord_time()));
        viewHolder.tv_new_price.setText(this.mDatas.get(position).getRecord_price());
        return convertView;
    }
    /*存放控件 的ViewHolder*/
    private  final class ViewHolder
    {
        public TextView record_name;
        public TextView record_time;
        public TextView tv_new_price;

    }
}

