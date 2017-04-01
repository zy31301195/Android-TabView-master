package com.ycl.tabview.Activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.R;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.LoginBeanTest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ycl.tabview.Activity.RegisterActivity.KEY_SEARCH_HISTORY_KEYWORD;
import static com.ycl.tabview.http.LoginHttps.API_BASE_URL;


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
    public static String user_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        mPref =  getSharedPreferences("register", Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD,"");
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
            String user = tel.getText().toString();
            String pwds = pwd.getText().toString();
            user_tel = user;

//            OkHttpClient client = new OkHttpClient.Builder().
//                    connectTimeout(60, TimeUnit.SECONDS).
//                    readTimeout(60, TimeUnit.SECONDS).
//                    writeTimeout(60, TimeUnit.SECONDS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    //.client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            retrofit.create(LoginHttps.class).getJson(user,pwds)
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
                            if(s.getUser().equals("3")){
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, tel.getText().toString());
                                mEditor.commit();
                                startActivity(intent);
                            }
                            else if(s.getUser().equals("2"))
                                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                            else
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
