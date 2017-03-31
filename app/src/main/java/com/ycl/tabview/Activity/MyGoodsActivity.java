package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Adapter.ListViewAdapter;
import com.ycl.tabview.Bean.MyItemBean;
import com.ycl.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyGoodsActivity extends Activity{
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

        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyGoodsActivity.this,mData.get(position).exam_name,Toast.LENGTH_LONG).show();
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyGoodsActivity.this,mData.get(position).exam_name+"long",Toast.LENGTH_LONG).show();
                return true;
            }
        });
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

}
