package com.ycl.tabview.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.Activity.GoodsActivity;
import com.ycl.tabview.Activity.NewActivity;
import com.ycl.tabview.Activity.SearchActivity;
import com.ycl.tabview.Adapter.MyAdapter;
import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.Bean.Weather;
import com.ycl.tabview.R;
import com.ycl.tabview.behavior.MyBehavior;
import com.ycl.tabview.http.LoginHttps;
import com.ycl.tabview.http.WeatherHttp;
import com.ycl.tabview.httpBean.ExamBean;
import com.ycl.tabview.retrofitUtil.Retrofitutil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;


public class HomeFragment extends Fragment implements MyAdapter.OnRecycleItemClick{
    private RecyclerView recyclerView;
    private List<Exam> mData = new ArrayList<>();
    private MyAdapter mAdapter;
    private View search;
    private View view;
    private TextView qd;

    private static HomeFragment homeFragment =new HomeFragment();
    public static HomeFragment newInstance(){
        return homeFragment;
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_fragment,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        search = view.findViewById(R.id.ll_search);
        qd = (TextView) view.findViewById(R.id.qd);
        mAdapter = new MyAdapter(mData);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);


        Retrofitutil.getmRetrofit().create(LoginHttps.class).getJson()
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
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                           // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                        }
                    });

        Retrofitutil.getWeather().create(WeatherHttp.class)
                .getWeather("杭州")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull Weather weather) throws Exception {
                        Toast.makeText(getContext(), weather.getData().getForecast().get(0).getHigh(), Toast.LENGTH_LONG).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);

            }


        });

        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),NewActivity.class);
                startActivity(intent);
            }
        });

        final MyBehavior myBehavior = new MyBehavior(HomeFragment.this);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setBehavior(myBehavior);

        myBehavior.setRefreshListener(new MyBehavior.RefreshListener(){
            @Override
            public void onRefresh() {
                Retrofitutil.getmRetrofit().create(LoginHttps.class).getJson()
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
                                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {
                                // Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_LONG).show();
                                myBehavior.stopRefresh();
                            }
                        });

            }
        });

        return view;
    }

    @Override
    public void onItemClick(View view, Object object) {
        Intent intent = new Intent(getContext(), GoodsActivity.class);
        intent.putExtra("examId",((Exam)object).getExam_id());
        intent.putExtra("userId",((Exam)object).getExam_user_id());
        intent.putExtra("state",0);
        startActivity(intent);
    }
}







