package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.R;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

import static com.ycl.tabview.Activity.LoginActivity.users;

/**
 * Created by Administrator on 2017/4/2.
 */

public class AddSignActivity extends Activity{
    private TextView title;
    private Button button;
    private EditText sign;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmessage_activity);
        initView();
    }
    private void  initView(){
        title = (TextView) findViewById(R.id.tv_title);
        button = (Button) findViewById(R.id.tv_save);
        sign = (EditText) findViewById(R.id.et_sign);
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
            Retrofitutil.getmRetrofit().create(LoginHttps.class).updateJson(users.getUser_tel(),"user_sign",sign.getText().toString())
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
                            if(s.getUser().equals("ok")){
                                Toast.makeText(AddSignActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddSignActivity.this,UserInfoActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(AddSignActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                            // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                        }
                    });




        }
    }
}
