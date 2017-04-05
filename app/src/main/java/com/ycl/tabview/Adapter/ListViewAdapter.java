package com.ycl.tabview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycl.tabview.Activity.RegisterActivity.school_name;

/**
 * Created by Administrator on 2017/3/30.
 */
//实现视图与数据的绑定
public class ListViewAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Exam> mDatas;
    public int[] imageids ={R.drawable.jisuan,R.drawable.chuanmei,R.drawable.shang,R.drawable.xindian,R.drawable.gongcheng,R.drawable.yixue,R.drawable.faxue,R.drawable.waiguoyu,R.drawable.chuangyi};//图表id

    public ListViewAdapter(Context context, List<Exam> mDatas)
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
//            viewHolder.hour = (TextView) convertView.findViewById(R.id.hour);
//            viewHolder.minutes= (TextView) convertView.findViewById(R.id.mimute);
//            viewHolder.second = (TextView) convertView.findViewById(R.id.second);
            viewHolder.prices = (TextView) convertView.findViewById(R.id.tv_new_price);

            convertView.setTag(viewHolder);//绑定ViewHolder对象
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        /*设置TextView显示的内容，即我们存放在动态数组中的数据*/

        Map<String,Object> list = new HashMap<String,Object>();
        for (int i=0;i<school_name.length;i++){
            list.put(school_name[i], imageids[i]);
        }
        viewHolder.iv.setImageResource((Integer) list.get(this.mDatas.get(position).getExam_school()));
        viewHolder.tv_dates.setText(this.mDatas.get(position).getExam_date());
        viewHolder.tv_name.setText(this.mDatas.get(position).getExam_name());
        viewHolder.prices.setText(this.mDatas.get(position).getExam_prices());

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
