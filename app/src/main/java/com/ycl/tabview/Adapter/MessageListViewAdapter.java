package com.ycl.tabview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycl.tabview.R;
import com.ycl.tabview.httpBean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MessageListViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<RecordBean.AllMessageBean> mDatas;

    public MessageListViewAdapter(Context context, List<RecordBean.AllMessageBean> mDatas)
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
        MessageListViewAdapter.ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.comment_item, parent,
                    false);
            viewHolder = new MessageListViewAdapter.ViewHolder();
            /*得到各个控件的对象*/
            viewHolder.comment_name = (TextView)convertView.findViewById(R.id.comment_name);
            viewHolder.comment_date = (TextView) convertView.findViewById(R.id.comment_date);
            viewHolder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);

            convertView.setTag(viewHolder);//绑定ViewHolder对象
        } else
        {
            viewHolder = (MessageListViewAdapter.ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        viewHolder.comment_name.setText(this.mDatas.get(position).getUser_name());
        viewHolder.comment_date.setText(String.valueOf(this.mDatas.get(position).getMessage_time()));
        viewHolder.comment_content.setText(this.mDatas.get(position).getMessage_content());
        return convertView;
    }
    /*存放控件 的ViewHolder*/
    private  final class ViewHolder
    {
        public TextView comment_name;
        public TextView comment_date;
        public TextView comment_content;

    }
}
