package com.example.lattetest;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * 作者: 李纯
 * 时间: 2018/6/21
 * 说明:
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(this);
    }
}
