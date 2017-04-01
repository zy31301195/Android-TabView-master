package com.ycl.tabview.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycl.tabview.Activity.GoodsActivity;
import com.ycl.tabview.Activity.SearchActivity;
import com.ycl.tabview.Adapter.MyAdapter;
import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.behavior.MyBehavior;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MyAdapter.OnRecycleItemClick{
    private RecyclerView recyclerView;
    private List<Exam> mData;
    private MyAdapter mAdapter;
    private View search;
    private View view;
    private TextView qd;

    private static HomeFragment homeFragment =new HomeFragment();
    public static HomeFragment newInstance(){
        return homeFragment;
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_fragment,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        search = view.findViewById(R.id.ll_search);
        qd = (TextView) view.findViewById(R.id.qd);


        this.mData = new ArrayList<>();
        for(int i=0;i<2;i++){
            Exam bean = new Exam();
            bean.setExam_name("数据库"+i);
            bean.setExam_date("2017-3-21");
            bean.setExam_prices("100");
            bean.setExam_school("计算");
            mData.add(bean);
        }
        this.mAdapter = new MyAdapter(mData);
        this.recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);



        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);

            }


        });

        final MyBehavior myBehavior = new MyBehavior(HomeFragment.this);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setBehavior(myBehavior);

        myBehavior.setRefreshListener(new MyBehavior.RefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myBehavior.stopRefresh();
                    }
                },2000);

            }
        });



        return view;
    }

    @Override
    public void onItemClick(View view, Object object) {
        Intent intent = new Intent(getContext(), GoodsActivity.class);
        startActivity(intent);
       // Toast.makeText(view.getContext(),((MyItemBean)object).exam_name,Toast.LENGTH_LONG).show();
    }
}







