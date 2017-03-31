package com.ycl.tabview.http;

import com.ycl.tabview.Bean.Exam;
import com.ycl.tabview.httpBean.LoginBeanTest;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by falling on 2017/3/27.
 */

public interface LoginHttps {
    public static final String API_BASE_URL = "http://192.168.191.1:8080/Paimai/";
    @GET("Login.action")
    Observable<LoginBeanTest> getJson(@Query("user_tel")String tel,@Query("pwd") String pwd);

    @GET("Register.action")
    Observable<LoginBeanTest> getJson(@QueryMap Map<String, String> map);

    @GET("AllExam.action")
    Observable<Exam> getJson();
}