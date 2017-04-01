package com.ycl.tabview.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Activity.MyGoodsActivity;
import com.ycl.tabview.Activity.SettingActivity;
import com.ycl.tabview.Activity.UserInfoActivity;
import com.ycl.tabview.R;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.httpBean.UserBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ycl.tabview.Activity.LoginActivity.user_tel;
import static com.ycl.tabview.http.LoginHttps.API_BASE_URL;

public class MyFragment extends Fragment {
    private RelativeLayout re_myinfo;
    private RelativeLayout re_setting;
    private RelativeLayout fabu;
    private TextView name;
    private TextView tel;
    public static int user_id;
    private String names;
    private String sex;
    private String school;
    private String sign;
    private String zgid;



    private static MyFragment fragmentCommon =new MyFragment();
    public static MyFragment newInstance(){
        return fragmentCommon;
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_fragment,container,false);
        re_myinfo = (RelativeLayout) view.findViewById(R.id.re_myinfo);
        re_setting = (RelativeLayout) view.findViewById(R.id.re_setting);
        fabu = (RelativeLayout) view.findViewById(R.id.fabu);
        name = (TextView) view.findViewById(R.id.tv_name);
        tel = (TextView) view.findViewById(R.id.tv_fxid);
        tel.setText("电话："+ user_tel);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(LoginHttps.class).getJson(user_tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<UserBean>() {
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
                    protected void subscribeActual(Observer<? super UserBean> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean s) {
                        name.setText(s.getUsers().getUser_name());
                        user_id = s.getUsers().getUser_id();
                        names = s.getUsers().getUser_name();
                        sex = s.getUsers().getUser_sex();
                        zgid = s.getUsers().getUser_zgid();
                        sign = s.getUsers().getUser_sign();
                        school = s.getUsers().getUser_school();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                    }
                });

        re_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInfoActivity.class);
                intent.putExtra("userName",names);
                intent.putExtra("userSex",sex);
                intent.putExtra("userZgid",zgid);
                intent.putExtra("userSchool",school);
                intent.putExtra("userSign",sign);

                startActivity(intent);
            }
        });

        re_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });

        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MyGoodsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}



