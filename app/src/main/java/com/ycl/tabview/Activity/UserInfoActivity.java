package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ycl.tabview.R;

import static com.ycl.tabview.Activity.LoginActivity.user_tel;

/**
 * Created by Administrator on 2017/3/23.
 */

public class UserInfoActivity extends Activity{
    private RelativeLayout re_avatar;
    private RelativeLayout re_name;
    private RelativeLayout re_tel;
    private RelativeLayout re_sex;
    private RelativeLayout re_address;
    private RelativeLayout re_no;
    private RelativeLayout re_sign;

    private ImageView iv_avatar;
    private TextView tv_name;
    private TextView tv_tel;
    private TextView tv_sex;
    private TextView tv_no;
    private TextView tv_sign;
    private TextView tv_address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        initView();
        initData();

    }

    private void  initView(){
        re_avatar = (RelativeLayout) this.findViewById(R.id.re_avatar);
        re_name = (RelativeLayout) this.findViewById(R.id.re_name);
        re_tel = (RelativeLayout) this.findViewById(R.id.re_tel);
        re_sex = (RelativeLayout) this.findViewById(R.id.re_sex);
        re_address = (RelativeLayout) this.findViewById(R.id.re_address);
        re_sign = (RelativeLayout)this.findViewById(R.id.re_sign);
        re_no = (RelativeLayout) this.findViewById(R.id.re_no);

        iv_avatar = (ImageView) this.findViewById(R.id.iv_avatar);//照片
        tv_name = (TextView) this.findViewById(R.id.tv_name);//昵称
        tv_tel = (TextView) this.findViewById(R.id.tv_tel);//电话
        tv_sex = (TextView) this.findViewById(R.id.tv_sex);//性别
        tv_sign = (TextView) this.findViewById(R.id.tv_sign);//签名
        tv_no = (TextView) this.findViewById(R.id.tv_no);//职工号
        tv_address = (TextView) this.findViewById(R.id.tv_address);//所在分院
    }

    private void initData(){
        Intent intent = getIntent();
        tv_name.setText(intent.getStringExtra("userName"));
        tv_tel.setText(user_tel);
        tv_sex.setText(intent.getStringExtra("userSex"));
        tv_no.setText(intent.getStringExtra("userZgid"));
        tv_address.setText(intent.getStringExtra("userSchool"));
        tv_sign.setText(intent.getStringExtra("userSign"));

    }

    public void back(View view) {
        this.finish();
    }
}
