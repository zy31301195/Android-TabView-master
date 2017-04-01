package com.ycl.tabview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Adapter.MessageListViewAdapter;
import com.ycl.tabview.Adapter.RecordListViewAdapter;
import com.ycl.tabview.Bean.Message;
import com.ycl.tabview.Bean.Record;
import com.ycl.tabview.R;
import com.ycl.tabview.View.AmountView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class GoodsActivity extends Activity {

    private TabHost mTabHost;
    private AmountView mAmountView;
    private TextView exam_name;
    private TextView exam_date;
    private TextView exam_time;
    private TextView exam_place;
    private TextView exam_newprice;
    private TextView exam_oldprice;
    private Button buy;
    private ListView recordlist;
    private ListView messagelist;
    private TextView user_name;
    private TextView user_tel;
    private TextView user_zgid;
    private TextView user_sex;
    private TextView user_school;
    private TextView user_sign;
    private List<Message> messageData;
    private List<Record> recordData;
    private MessageListViewAdapter messageListViewAdapter;
    private RecordListViewAdapter recordListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity);
        initView();
        this.messageListViewAdapter = new MessageListViewAdapter(this,messageData);
        this.messagelist.setAdapter(messageListViewAdapter);
        this.recordListViewAdapter = new RecordListViewAdapter(this,recordData);
        this.recordlist.setAdapter(recordListViewAdapter);
        setListViewHeightBasedOnChildren(recordlist);
    }

    private void initView(){
        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();// init
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("考试详情").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("发布者信息").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("留言").setContent(R.id.tab3));
        exam_name = (TextView) findViewById(R.id.et_exam_name);
        exam_date = (TextView) findViewById(R.id.et_exam_date);
        exam_time = (TextView) findViewById(R.id.et_exam_time);
        exam_place = (TextView) findViewById(R.id.et_exam_place);
        exam_newprice = (TextView) findViewById(R.id.tv_new_prices);
        exam_oldprice = (TextView) findViewById(R.id.tv_old_price);
        //recordlist = (ListView) findViewById(R.id.record_list);
        messagelist = (ListView) findViewById(R.id.comments_list);
        user_name = (TextView) findViewById(R.id.tv_name);
        user_school = (TextView) findViewById(R.id.tv_school);
        user_sex = (TextView) findViewById(R.id.tv_sex);
        user_sign = (TextView) findViewById(R.id.tv_sign);
        user_zgid = (TextView) findViewById(R.id.tv_no);
        user_tel = (TextView) findViewById(R.id.tv_tel);
        buy = (Button) findViewById(R.id.buy);

        exam_name.setText("aaa");
        mAmountView = (AmountView) findViewById(R.id.amount_view);
        mAmountView.setGoods_storage(50);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(){
        this.messageData = new ArrayList<Message>();
        for(int i=0;i<2;i++){
            Message bean = new Message();
            bean.setUser_name("Xmy"+i);
            bean.setMessage_content("你好啊"+i);
            bean.setMessage_time("2017-3-21");
            messageData.add(bean);
        }

        this.recordData = new ArrayList<Record>();
        for(int i=0;i<2;i++){
            Record beans = new Record();
            beans.setBuyer_name("Xmy"+i);
            beans.setRecord_date("2017-3-21");
            beans.setReocrd_time("8:3"+i);
            recordData.add(beans);
        }

    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
