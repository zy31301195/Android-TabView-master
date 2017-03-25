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

import com.ycl.tabview.Activity.SearchActivity;
import com.ycl.tabview.Adapter.MyAdapter;
import com.ycl.tabview.Bean.MyItemBean;
import com.ycl.tabview.R;
import com.ycl.tabview.behavior.MyBehavior;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<MyItemBean> mData;
    private MyAdapter mAdapter;
    private View search;
    private View view;
    private TextView qd;
    public String[] name = {"计算","传媒","商学院","信电","工程","医学院","法学院","外国语","创意"};
    public String[] iamgeids ={};//图表id
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

//        List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
//        for (int i=0;i<name.length;i++){
//            Map<String,Object> list = new HashMap<String,Object>();
//            list.put("names",name[i]);
//            list.put("images",iamgeids[i]);
//            lists.add(list);
//        }

        this.mData = new ArrayList<MyItemBean>();
        for(int i=0;i<2;i++){
            MyItemBean bean = new MyItemBean();
            bean.exam_name = "Xmy"+i;

            mData.add(bean);
        }
        this.mAdapter = new MyAdapter(mData);
        this.recyclerView.setAdapter(mAdapter);




        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);

            }


        });

//        recyclerView.setAdapter(new  RecyclerView.Adapter() {
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new ViewHolder(getLayoutInflater(savedInstanceState).inflate(R.layout.item, parent, false));
//            }
//
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                ViewHolder vh = (ViewHolder) holder;
//
////                vh.text.setText("  Item " + (position + 1));
////                vh.text2.setText("  Do one thing at a time, and do well.");
//            }
//
//            @Override
//            public int getItemCount() {
//                return 20;
//            }
//
//            class ViewHolder extends RecyclerView.ViewHolder {
//
////                TextView text;
////                TextView text2;
//                //LinearLayout goods;
//
//                public ViewHolder(View itemView) {
//                    super(itemView);
//                    //goods= (LinearLayout) itemView.findViewById(R.id.items);
//
////                    text = (TextView) itemView.findViewById(R.id.text);
////                    text2 = (TextView) itemView.findViewById(R.id.text2);
//                }
//
//            }
//        });

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
}







