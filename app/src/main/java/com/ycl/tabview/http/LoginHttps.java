package com.ycl.tabview.http;

import com.ycl.tabview.httpBean.ExamBean;
import com.ycl.tabview.httpBean.LoginBean;
import com.ycl.tabview.httpBean.LoginBeanTest;
import com.ycl.tabview.httpBean.OkExamsBean;
import com.ycl.tabview.httpBean.RecordBean;
import com.ycl.tabview.httpBean.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;



public interface LoginHttps {
    @GET("Login.action")
    Observable<LoginBean> getJson(@Query("user_tel")String tel, @Query("pwd") String pwd);

    @GET("Register.action")
    Observable<LoginBeanTest> getJson(@QueryMap Map<String, String> map);

    @GET("AddExam.action")
    Observable<LoginBeanTest> addExamsJson(@QueryMap Map<String, Object> map);

    @GET("AddRecord.action")
    Observable<LoginBeanTest> addRecordJson(@QueryMap Map<String, Object> map);

    @GET("AddMessage.action")
    Observable<LoginBeanTest> addMessageJson(@QueryMap Map<String, Object> map);

    @GET("AllExam.action")
    Observable<ExamBean> getJson();

    @GET("MyExam.action")
    Observable<ExamBean> getJson(@Query("user_id")int user_id);

    @GET("Complete.action")
    Observable<ExamBean> completeJson(@Query("user_id")int user_id);

    @GET("TakeComplete.action")
    Observable<ExamBean> takecompleteJson(@Query("user_id")int user_id);

    @GET("LoseComplete.action")
    Observable<ExamBean> losecompleteJson(@Query("user_id")int user_id);

    @GET("ReadUser.action")
    Observable<UserBean> getJson(@Query("user_tel")String user_tel);

    @GET("UpdateUser.action")
    Observable<LoginBeanTest> updateJson(@Query("tel")String tel,@Query("param") String param,@Query("value") String value);

    @GET("Exam.action")
    Observable<RecordBean> recordJson(@Query("exam_id")int exam_id, @Query("user_id")int user_id);

    @GET("SearhExam.action")
    Observable<ExamBean> searchJson(@Query("key")String key, @Query("value")String value);

    @GET("OkExams.action")
    Observable<OkExamsBean> okExamsJson(@Query("exam_id")int exam_id);
}