package com.ycl.tabview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.R;
import com.ycl.tabview.View.AmountView;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.YugaoBean;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/5/7.
 */

public class TodayActivity extends Activity {
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okexams);
        mMyapplication = (Myapplication) getApplication();
        initView();
        initData();

    }

    private void initView() {
        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();// init
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("考试详情").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("卖家者信息").setContent(R.id.tab2));
        // mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("留言").setContent(R.id.tab3));
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
        Retrofitutil.getmRetrofit().create(LoginHttps.class).YugaoJson(examId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<YugaoBean>() {
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
                    protected void subscribeActual(Observer<? super YugaoBean> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YugaoBean s) {
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

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TodayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
    public void back(View view) {
        this.finish();
    }

}
