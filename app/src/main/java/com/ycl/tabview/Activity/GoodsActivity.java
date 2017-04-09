package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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
    private LinearLayout linearLayout;
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
    private TextView message_button;
    private int mount;
    private int examId;
    private int userId;
    private int state;
    private Myapplication mMyapplication;
    private List<RecordBean.AllMessageBean> messageData = new ArrayList<>();
    private List<RecordBean.AllRecordBean> recordData = new ArrayList<>();
    private MessageListViewAdapter messageListViewAdapter;
    private RecordListViewAdapter recordListViewAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_activity);
        mMyapplication = (Myapplication) getApplication();
        initView();
        initData();

        this.messageListViewAdapter = new MessageListViewAdapter(this, messageData);
        this.messagelist.setAdapter(messageListViewAdapter);
        this.recordListViewAdapter = new RecordListViewAdapter(this, recordData);
        this.recordlist.setAdapter(recordListViewAdapter);
        //setListViewHeightBasedOnChildren(recordlist);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();// init
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("考试详情").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("发布者信息").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("留言").setContent(R.id.tab3));
        linearLayout = (LinearLayout) findViewById(R.id.chujia);
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
        message_button = (TextView) findViewById(R.id.message_button);
        buy = (Button) findViewById(R.id.buy);
        buy.setOnClickListener(new ButtonClickListener());
        message_button.setOnClickListener(new MessageButtonClickListener());

        mAmountView = (AmountView) findViewById(R.id.amount_view);

        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                mount = amount;
                //Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        examId = getIntent().getIntExtra("examId", -1);
        userId = getIntent().getIntExtra("userId", -1);
        state = getIntent().getIntExtra("state", -2);
        if (state == -1) {
            linearLayout.setVisibility(View.GONE);
        }
        Retrofitutil.getmRetrofit().create(LoginHttps.class).recordJson(examId, userId)
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
                        exam_time.setText(s.getExam().getExam_time() + "-" + s.getExam().getExam_endtime());
                        exam_place.setText(s.getExam().getExam_place());
                        exam_newprice.setText(s.getExam().getExam_newprice());
                        user_name.setText(s.getUser().getUser_name());
                        user_tel.setText(s.getUser().getUser_tel());
                        user_zgid.setText(s.getUser().getUser_zgid());
                        user_sex.setText(s.getUser().getUser_sex());
                        user_sign.setText(s.getUser().getUser_sign());
                        user_school.setText(s.getUser().getUser_school());
                        messageData.clear();

                        mAmountView.setGoods_storage(Integer.valueOf(s.getExam().getExam_newprice()) - 1);
                        mAmountView.setTextValue(Integer.valueOf(s.getExam().getExam_newprice()) - 1);
                        messageData.addAll(s.getAllMessage());
                        messageListViewAdapter.notifyDataSetChanged();
                        recordData.clear();
                        recordData.addAll(s.getAllRecord());
                        recordListViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GoodsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Goods Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
//    }


    private final class ButtonClickListener implements View.OnClickListener {

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
                            if (s.getUser().equals("addOk")) {
                                initData();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(GoodsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        }
    }

    private final class MessageButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(GoodsActivity.this,AddMessageActivity.class);
            intent.putExtra("Exam_id",examId);
            intent.putExtra("title","添加留言");
            startActivity(intent);

        }
    }

}