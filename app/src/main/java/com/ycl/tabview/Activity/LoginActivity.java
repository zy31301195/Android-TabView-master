package com.ycl.tabview.Activity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Bean.Users;
import com.ycl.tabview.R;
import com.ycl.tabview.application.Myapplication;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBean;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

import static com.ycl.tabview.Activity.RegisterActivity.KEY_SEARCH_HISTORY_KEYWORD;
import static com.ycl.tabview.Activity.RegisterActivity.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;


/**
 * Created by Administrator on 2017/3/18.
 */

public class LoginActivity extends Activity {
    private Button btn_login;
    private TextView btn_register1;
    private EditText tel;
    private EditText pwd;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Myapplication mMyapplication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return;

        }

        initView();
        mPref =  getSharedPreferences("register", Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD,"");
        mMyapplication= (Myapplication) getApplication();
        if (!TextUtils.isEmpty(history)){
            tel.setText(history);
        }


    }

    private void initView(){
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register1 = (TextView) findViewById(R.id.register);
        tel = (EditText) findViewById(R.id.et_usertel);
        pwd = (EditText) findViewById(R.id.et_password);

        btn_login.setOnClickListener(new LoginButtonClickListener());
        btn_register1.setOnClickListener(new RegisterButtonCickListener());
    }

    private final class LoginButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            final String user = tel.getText().toString();
            String pwds = pwd.getText().toString();

            Retrofitutil.getmRetrofit().create(LoginHttps.class).getJson(user,pwds)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subject<LoginBean>() {
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
                        protected void subscribeActual(Observer<? super LoginBean> observer) {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean s) {
                            if(s.getUser().equals("3")){
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                Users users = mMyapplication.users;
                                users.setUser_id(s.getUsers().getUser_id());
                                users.setUser_zgid(s.getUsers().getUser_zgid());
                                users.setUser_tel(s.getUsers().getUser_tel());
                                users.setUser_sex(s.getUsers().getUser_sex());
                                users.setUser_name(s.getUsers().getUser_name());
                                users.setUser_school(s.getUsers().getUser_school());
                                users.setUser_sign(s.getUsers().getUser_sign());
                                users.setUser_date(s.getUsers().getUser_date());
                                mMyapplication.take = s.getTake();
                                mMyapplication.lose = s.getLose();
                                mMyapplication.my = s.getMy();
                                mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, tel.getText().toString());
                                mEditor.commit();
                                startActivity(intent);
                            }
                            else if(s.getUser().equals("2"))
                                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                            else if(s.getUser().equals("1"))
                                Toast.makeText(LoginActivity.this,"没有该账号",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                           // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                        }
                    });

        }

    }

    private final class RegisterButtonCickListener implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    }

}
