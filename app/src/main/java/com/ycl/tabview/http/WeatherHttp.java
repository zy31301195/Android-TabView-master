package com.ycl.tabview.http;

import com.ycl.tabview.Bean.Weather;
import com.ycl.tabview.httpBean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by falling on 2017/5/14.
 */

public interface WeatherHttp {
    @GET("weather_mini")
    Observable<Weather> getWeather(@Query("city")String tel);

}
