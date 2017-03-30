package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ycl.tabview.Adapter.ListViewAdapter;
import com.ycl.tabview.Bean.MyItemBean;
import com.ycl.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyGoodsActivity extends Activity {
    private ListView mListView;
    private TextView add;
    private List<MyItemBean> mData;
    private ListViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygoods_activivty);
        initView();
        initData();
        this.mAdapter = new ListViewAdapter(MyGoodsActivity.this,mData);
        this.mListView.setAdapter(mAdapter);
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.mygoods_list);
        add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(new AddButtonClickListener());

    }

    private void initData(){
        this.mData = new ArrayList<MyItemBean>();
        for(int i=0;i<2;i++){
            MyItemBean bean = new MyItemBean();
            bean.exam_name = "Xmy"+i;
            mData.add(bean);
        }
    }

//    @Override
//    public void onItemLongClick(View view, int postion) {
//        Toast.makeText(MyGoodsActivity.this, "点击"+postion, Toast.LENGTH_SHORT).show();
//    }

    private final class AddButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyGoodsActivity.this, AddgoodsActivity.class);
            startActivity(intent);
        }
    }


    public void back(View view) {
        this.finish();
    }

//    @Override
//    public void onItemClick(View view, Object object) {
//        Intent intent = new Intent(MyGoodsActivity.this, GoodsActivity.class);
//        startActivity(intent);
//    }
}
