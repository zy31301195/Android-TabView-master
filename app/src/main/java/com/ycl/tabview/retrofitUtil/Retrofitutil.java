package com.ycl.tabview.retrofitUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class Retrofitutil {
    public static final String API_BASE_URL = "http://192.168.1.14:8080/Paimai/";
    private static Retrofit mRetrofit = new Retrofit.Builder().
            baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    private static Retrofit Weather = new Retrofit.Builder().
            baseUrl("http://wthrcdn.etouch.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    public static Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public static Retrofit getWeather() {
        return Weather;
    }
}
