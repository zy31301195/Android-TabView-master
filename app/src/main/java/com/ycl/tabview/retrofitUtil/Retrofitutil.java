package com.ycl.tabview.retrofitUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by falling on 2017/4/2.
 */

public class Retrofitutil {
    public static final String API_BASE_URL = "http://39.181.80.222:8080/Paimai/";
    private static Retrofit mRetrofit = new Retrofit.Builder().
            baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    public static Retrofit getmRetrofit() {
        return mRetrofit;
    }

}
