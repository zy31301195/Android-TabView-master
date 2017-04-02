package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
 * Created by Administrator on 2017/3/23.
 */

public class UpdateUserActivity extends Activity{
    private Button save;
    private EditText et_password;
    private ImageView iv_hide;
    private ImageView iv_show;
    private EditText et_password2;
    private ImageView iv_hide2;
    private ImageView iv_show2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initView();

    }

    private void initView(){
        et_password = (EditText) findViewById(R.id.et_password);
        iv_hide = (ImageView) findViewById(R.id.iv_hide);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        et_password2 = (EditText) findViewById(R.id.et_password2);
        iv_hide2 = (ImageView) findViewById(R.id.iv_hide2);
        iv_show2 = (ImageView) findViewById(R.id.iv_show2);
        save = (Button) findViewById(R.id.tv_save);
        et_password.addTextChangedListener(new UpdateUserActivity.TextChange());
        et_password2.addTextChangedListener(new UpdateUserActivity.TextChange());

        save.setOnClickListener(new SaveButtonClickListener());
    }

    public void back(View view) {
        this.finish();
    }

    private final class SaveButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(!(et_password.getText().toString()).equals(et_password2.getText().toString())){
                Toast.makeText(UpdateUserActivity.this,"两次密码不匹配",Toast.LENGTH_SHORT).show();
            }
            else {
                Retrofitutil.getmRetrofit().create(LoginHttps.class).updateJson(users.getUser_tel(),"user_pwd",et_password.getText().toString())
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
                                    Toast.makeText(UpdateUserActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                                    UpdateUserActivity.this.finish();
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(UpdateUserActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {
                                // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                            }
                        });


            }
        }
    }


    // EditText监听器
    private class TextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {

            boolean Sign1 = et_password.getText().length() > 0;
            boolean Sign2 = et_password2.getText().length() > 0;

            if (Sign1 & Sign2 ) {
                save.setTextColor(0xFFFFFFFF);
                save.setEnabled(true);
            }
            // 在layout文件中，对Button的text属性应预先设置默认值，否则刚打开程序的时候Button是无显示的
            else {
                save.setTextColor(0xFFD0EFC6);
                save.setEnabled(false);
            }
        }

    }
}
