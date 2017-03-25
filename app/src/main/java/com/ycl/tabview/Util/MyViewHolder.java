package com.ycl.tabview.Util;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycl.tabview.R;


public class MyViewHolder extends ViewHolder{

	public ImageView iv;
	public TextView tv_name;
	public TextView tv_dates;
	public TextView hour;
	public TextView minutes;
	public TextView second;
	public TextView prices;

	public MyViewHolder(View arg0) {
		super(arg0);
		iv = (ImageView)arg0.findViewById(R.id.image_left);
		tv_name = (TextView)arg0.findViewById(R.id.tv_name);
		tv_dates = (TextView) arg0.findViewById(R.id.tv_dates);
		hour = (TextView) arg0.findViewById(R.id.hour);
		minutes= (TextView) arg0.findViewById(R.id.mimute);
		second = (TextView) arg0.findViewById(R.id.second);
		prices = (TextView) arg0.findViewById(R.id.price);
	}
}
