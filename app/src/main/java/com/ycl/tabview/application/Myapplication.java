package com.ycl.tabview.application;

import android.app.Application;

import com.ycl.tabview.Bean.Users;

/**
 * Created by falling on 2017/4/2.
 */

public class Myapplication extends Application {
    public Users users;

    @Override
    public void onCreate() {
        super.onCreate();
        users = new Users();
    }
}
