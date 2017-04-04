package com.ycl.tabview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Adapter.MessageListViewAdapter;
import com.ycl.tabview.Adapter.RecordListViewAdapter;
import com.ycl.tabview.Bean.Record;
import com.ycl.tabview.R;
import com.ycl.tabview.View.AmountView;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.httpBean.RecordBean;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

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
    private int mount;
    private int examId;
    private int userId;
    private Myapplication mMyapplication;
    private List<RecordBean.AllMessageBean> messageData = new ArrayList<>();
    private List<RecordBean.AllRecordBean> recordData =new ArrayList<>();
    private MessageListViewAdapter messageListViewAdapter;
    private RecordListViewAdapter recordListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity);
        mMyapplication= (Myapplication) getApplication();
        initView();
        initData();

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
        recordlist = (ListView) findViewById(R.id.record_list);
        messagelist = (ListView) findViewById(R.id.comments_list);
        user_name = (TextView) findViewById(R.id.tv_name);
        user_school = (TextView) findViewById(R.id.tv_school);
        user_sex = (TextView) findViewById(R.id.tv_sex);
        user_sign = (TextView) findViewById(R.id.tv_sign);
        user_zgid = (TextView) findViewById(R.id.tv_no);
        user_tel = (TextView) findViewById(R.id.tv_tel);
        buy = (Button) findViewById(R.id.buy);
        buy.setOnClickListener(new ButtonClickListener());

        mAmountView = (AmountView) findViewById(R.id.amount_view);

        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                mount = amount;
                //Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(){
        examId =  getIntent().getIntExtra("examId",-1);
        userId =  getIntent().getIntExtra("userId",-1);

        Retrofitutil.getmRetrofit().create(LoginHttps.class).recordJson(examId,userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<RecordBean>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super RecordBean> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecordBean s) {
                        exam_name.setText(s.getExam().getExam_name());
                        exam_oldprice.setText(s.getExam().getExam_prices());
                        exam_date.setText(s.getExam().getExam_date());
                        exam_time.setText(s.getExam().getExam_time()+"-"+s.getExam().getExam_endtime());
                        exam_place.setText(s.getExam().getExam_place());
                        exam_newprice.setText(s.getExam().getExam_newprice());
                        user_name.setText(s.getUser().getUser_name());
                        user_tel.setText(s.getUser().getUser_tel());
                        user_zgid.setText(s.getUser().getUser_zgid());
                        user_sex.setText(s.getUser().getUser_sex());
                        user_sign.setText(s.getUser().getUser_sign());
                        user_school.setText(s.getUser().getUser_school());
                        messageData.clear();

                        mAmountView.setGoods_storage(Integer.valueOf(s.getExam().getExam_newprice())-1);
                        mAmountView.setTextValue(Integer.valueOf(s.getExam().getExam_newprice())-1);
                        messageData.addAll(s.getAllMessage());
                        messageListViewAdapter.notifyDataSetChanged();
                        recordData.clear();
                        recordData.addAll(s.getAllRecord());
                        recordListViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GoodsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    private final class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = simpleDateFormat.format(new Date());
            Record record = new Record();
            record.setBuyer_id(mMyapplication.users.getUser_id());
            record.setExam_id(examId);
            record.setRecord_price(String.valueOf(mount));
            record.setReocrd_time(datetime);
            Map<String, Object> map = record.createCommitParams();

            Retrofitutil.getmRetrofit().create(LoginHttps.class).addRecordJson(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subject<LoginBeanTest>() {
                        @Override
                        public boolean hasObservers() {
                            return false;
                        }

                        @Override
                        public boolean hasThrowable() {
                            return false;
                        }

                        @Override
                        public boolean hasComplete() {
                            return false;
                        }

                        @Override
                        public Throwable getThrowable() {
                            return null;
                        }

                        @Override
                        protected void subscribeActual(Observer<? super LoginBeanTest> observer) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBeanTest s) {
                            if(s.getUser().equals("addOk")){
                                initData();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(GoodsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


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