package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Bean.Message;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/4/8.
 */

public class AddMessageActivity extends Activity {
    private TextView title;
    private Button button;
    private EditText message;
    private Myapplication mMyapplication;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmessage_activity);
        mMyapplication = (Myapplication) getApplication();
        initView();
    }
    private void  initView(){
        title = (TextView) findViewById(R.id.tv_title);
        button = (Button) findViewById(R.id.tv_save);
        message = (EditText) findViewById(R.id.et_sign);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        button.setOnClickListener(new ButtonClickListener());

    }

    public void back(View view) {
        this.finish();
    }

    private final class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Message m =new Message();
            m.setMessage_time(df.format(new Date()));
            m.setMessage_content(message.getText().toString());
            m.setExam_id(getIntent().getIntExtra("Exam_id", -1));
            m.setUser_id(mMyapplication.users.getUser_id());
            Map<String, Object> map = m.createCommitParams();

            Retrofitutil.getmRetrofit().create(LoginHttps.class).addMessageJson(map)
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
                            if(s.getUser().equals("addMessageOk")){
                                Toast.makeText(AddMessageActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                                AddMessageActivity.this.finish();
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(AddMessageActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                            // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                        }
                    });




        }
    }
}
