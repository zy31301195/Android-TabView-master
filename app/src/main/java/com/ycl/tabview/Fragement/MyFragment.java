package com.ycl.tabview.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ycl.tabview.Activity.LoseGoodsActivity;
import com.ycl.tabview.Activity.MyGoodsActivity;
import com.ycl.tabview.Activity.SettingActivity;
import com.ycl.tabview.Activity.TakeGoodsActivity;
import com.ycl.tabview.Activity.UserInfoActivity;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;


public class MyFragment extends Fragment {
    private RelativeLayout re_myinfo;
    private RelativeLayout re_setting;
    private RelativeLayout fabu;
    private RelativeLayout tiaopei;
    private RelativeLayout liupai;
    private TextView name;
    private TextView tel;
    private Myapplication mMyapplication;



    private static MyFragment fragmentCommon =new MyFragment();
    public static MyFragment newInstance(){
        return fragmentCommon;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMyapplication = (Myapplication) getActivity().getApplication();
        tel.setText("电话："+ mMyapplication.users.getUser_tel());
        name.setText(mMyapplication.users.getUser_name());
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_fragment,container,false);
        re_myinfo = (RelativeLayout) view.findViewById(R.id.re_myinfo);
        re_setting = (RelativeLayout) view.findViewById(R.id.re_setting);
        fabu = (RelativeLayout) view.findViewById(R.id.fabu);
        name = (TextView) view.findViewById(R.id.tv_name);
        tel = (TextView) view.findViewById(R.id.tv_fxid);
        tiaopei = (RelativeLayout) view.findViewById(R.id.re_tiaopei);
        liupai = (RelativeLayout) view.findViewById(R.id.re_liupai);


        re_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),UserInfoActivity.class);
                startActivity(intent);
            }
        });

        re_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SettingActivity.class);
                startActivity(intent);
            }
        });

        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MyGoodsActivity.class);
                startActivity(intent);
            }
        });

        tiaopei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),TakeGoodsActivity.class);
                startActivity(intent);

            }
        });

        liupai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),LoseGoodsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}



