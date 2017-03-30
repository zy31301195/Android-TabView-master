package com.ycl.tabview.http;

import com.ycl.tabview.httpBean.LoginBeanTest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by falling on 2017/3/27.
 */

public interface LoginHttps {
    @GET("Login.action")
    Observable<LoginBeanTest> getJson(@Query("user_tel")String tel,@Query("pwd") String pwd);
}