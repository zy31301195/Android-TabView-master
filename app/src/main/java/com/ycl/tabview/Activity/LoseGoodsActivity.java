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
import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.ExamBean;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/4/8.
 */

public class LoseGoodsActivity extends Activity{
    private ListView mListView;
    private List<Exam> mData = new ArrayList<>();
    private ListViewAdapter mAdapter;
    private TextView add;
    private TextView title;
    private Myapplication mMyapplication;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygoods_activivty);
        mMyapplication = (Myapplication) getApplication();
        initView();
        initData();
        mAdapter = new ListViewAdapter(LoseGoodsActivity.this,mData);
        mListView.setAdapter(mAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LoseGoodsActivity.this,GoodsActivity.class);
                intent.putExtra("examId",mData.get(position).getExam_id());
                intent.putExtra("userId",mData.get(position).getExam_user_id());
                intent.putExtra("state",-1);
                startActivity(intent);
                //Toast.makeText(MyGoodsActivity.this,mData.get(position).getExam_name(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.mygoods_list);
        add = (TextView) findViewById(R.id.add);
        add.setVisibility(View.GONE);
        title = (TextView) findViewById(R.id.tv_title);
        title.setText("已流拍的考试");

    }

    private void initData(){

        Retrofitutil.getmRetrofit().create(LoginHttps.class).losecompleteJson(mMyapplication.users.getUser_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<ExamBean>() {
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
                    protected void subscribeActual(Observer<? super ExamBean> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExamBean s) {
                        mData.clear();
                        mData.addAll(s.getList());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoseGoodsActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void back(View view) {
        this.finish();
    }
}
