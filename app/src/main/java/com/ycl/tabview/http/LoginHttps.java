package com.ycl.tabview.http;

import com.ycl.tabview.httpBean.ExamBean;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.httpBean.UserBean;

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

    @GET("AddExam.action")
    Observable<LoginBeanTest> addExamsJson(@QueryMap Map<String, Object> map);

    @GET("AllExam.action")
    Observable<ExamBean> getJson();

    @GET("MyExam.action")
    Observable<ExamBean> getJson(@Query("user_id")int user_id);

    @GET("ReadUser.action")
    Observable<UserBean> getJson(@Query("user_tel")String user_tel);

    @GET("UpdateUser.action")
    Observable<LoginBeanTest> updateJson(@Query("tel")String tel,@Query("param") String param,@Query("value") String value);
}