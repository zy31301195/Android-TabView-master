package com.ycl.tabview.Util;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycl.tabview.R;
import com.ycl.tabview.View.EasyCountDownTextureView;


public class MyViewHolder extends ViewHolder{

	public ImageView iv;
	public TextView tv_name;
	public TextView tv_dates;
	public TextView prices;
	public EasyCountDownTextureView mEasyCountDownTextureView;

	public MyViewHolder(View arg0) {
		super(arg0);
		iv = (ImageView)arg0.findViewById(R.id.image_left);
		tv_name = (TextView)arg0.findViewById(R.id.tv_name);
		tv_dates = (TextView) arg0.findViewById(R.id.tv_dates);
		prices = (TextView) arg0.findViewById(R.id.tv_new_price);
		mEasyCountDownTextureView = (EasyCountDownTextureView) arg0.findViewById(R.id.setting_countdown_text);
	}
}
