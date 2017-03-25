package com.ycl.tabview.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ycl.tabview.Activity.MyGoodsActivity;
import com.ycl.tabview.Activity.SettingActivity;
import com.ycl.tabview.Activity.UserInfoActivity;
import com.ycl.tabview.R;

public class MyFragment extends Fragment {
    private RelativeLayout re_myinfo;
    private RelativeLayout re_setting;
    private RelativeLayout fabu;

    private static MyFragment fragmentCommon =new MyFragment();
    public static MyFragment newInstance(){
        return fragmentCommon;
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_fragment,container,false);
        re_myinfo = (RelativeLayout) view.findViewById(R.id.re_myinfo);
        re_setting = (RelativeLayout) view.findViewById(R.id.re_setting);
        fabu = (RelativeLayout) view.findViewById(R.id.fabu);

        re_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInfoActivity.class);
                startActivity(intent);
            }
        });

        re_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });

        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MyGoodsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}



