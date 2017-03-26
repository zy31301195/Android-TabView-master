package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ycl.tabview.Adapter.MyAdapter;
import com.ycl.tabview.Bean.MyItemBean;
import com.ycl.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyGoodsActivity extends Activity implements MyAdapter.OnRecycleItemClick{
    private RecyclerView mRecyclerView;
    private TextView add;
    private List<MyItemBean> mData;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygoods_activivty);
        add = (TextView) findViewById(R.id.add);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyGoodsActivity.this, AddgoodsActivity.class);
                startActivity(intent);
            }
        });
        this.mData = new ArrayList<MyItemBean>();
        for(int i=0;i<2;i++){
            MyItemBean bean = new MyItemBean();
            bean.exam_name = "Xmy"+i;

            mData.add(bean);
        }
        this.mAdapter = new MyAdapter(mData);
        this.mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    public void back(View view) {
        this.finish();
    }

    @Override
    public void onItemClick(View view, Object object) {
        Intent intent = new Intent(MyGoodsActivity.this, GoodsActivity.class);
        startActivity(intent);
    }
}
