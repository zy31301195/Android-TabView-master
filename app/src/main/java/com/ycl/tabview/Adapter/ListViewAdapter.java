package com.ycl.tabview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycl.tabview.Bean.MyItemBean;
import com.ycl.tabview.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
//实现视图与数据的绑定
public class ListViewAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<MyItemBean> mDatas;

    public ListViewAdapter(Context context, List<MyItemBean> mDatas)
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
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item, parent,
                    false);
            viewHolder = new ViewHolder();
            /*得到各个控件的对象*/
            viewHolder.iv = (ImageView)convertView.findViewById(R.id.image_left);
            viewHolder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_dates = (TextView) convertView.findViewById(R.id.tv_dates);
            viewHolder.hour = (TextView) convertView.findViewById(R.id.hour);
            viewHolder.minutes= (TextView) convertView.findViewById(R.id.mimute);
            viewHolder.second = (TextView) convertView.findViewById(R.id.second);
            viewHolder.prices = (TextView) convertView.findViewById(R.id.tv_new_price);

            convertView.setTag(viewHolder);//绑定ViewHolder对象
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        viewHolder.tv_dates.setText(this.mDatas.get(position).exam_date);
        viewHolder.tv_name.setText(this.mDatas.get(position).exam_name);
        viewHolder.prices.setText(String.valueOf(this.mDatas.get(position).prices));
        viewHolder.hour.setText(this.mDatas.get(position).hour);
        viewHolder.minutes.setText(this.mDatas.get(position).minutes);
        viewHolder.second.setText(this.mDatas.get(position).second);

        return convertView;
    }
    /*存放控件 的ViewHolder*/
    private  final class ViewHolder
    {
        public ImageView iv;
        public TextView tv_name;
        public TextView tv_dates;
        public TextView hour;
        public TextView minutes;
        public TextView second;
        public TextView prices;

    }
}
