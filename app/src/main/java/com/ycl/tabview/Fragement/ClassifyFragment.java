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

import com.ycl.tabview.Activity.ChooseActivity;
import com.ycl.tabview.R;

/**
 * Created by Administrator on 2017/3/19.
 */

public class ClassifyFragment extends Fragment {
    private RelativeLayout grid1;
    private RelativeLayout grid2;
    private RelativeLayout grid3;
    private RelativeLayout grid4;
    private RelativeLayout grid5;
    private RelativeLayout grid6;
    private RelativeLayout grid7;
    private RelativeLayout grid8;
    private RelativeLayout grid9;


    private static ClassifyFragment classifyActivity =new ClassifyFragment();
    public static ClassifyFragment newInstance(){
        return classifyActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.classify_fragment,container,false);

        grid1 = (RelativeLayout) view.findViewById(R.id.grid1);
        grid2 = (RelativeLayout) view.findViewById(R.id.grid2);
        grid3 = (RelativeLayout) view.findViewById(R.id.grid3);
        grid4 = (RelativeLayout) view.findViewById(R.id.grid4);
        grid5 = (RelativeLayout) view.findViewById(R.id.grid5);
        grid6 = (RelativeLayout) view.findViewById(R.id.grid6);
        grid7 = (RelativeLayout) view.findViewById(R.id.grid7);
        grid8 = (RelativeLayout) view.findViewById(R.id.grid8);
        grid9 = (RelativeLayout) view.findViewById(R.id.grid9);

        grid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu1);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });

        grid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu2);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });

        grid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu3);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });

        grid4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu4);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });

        grid5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu5);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });
        grid6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu6);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });
        grid7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu7);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });
        grid8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu8);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });

        grid9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ChooseActivity.class);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_menu9);
                String tv1String = tv1.getText().toString();
                intent.putExtra("title",tv1String);
                startActivity(intent);

            }
        });

        return view;
    }




}
