package com.example.latte_core.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.SystemClock;

import com.example.latte_core.ui.LatteLoader;
import com.example.latte_core.util.LatteLogger;

import java.io.IOException;
import java.io.InputStream;

/**
 * 作者: 李纯
 * 时间: 2018/6/14
 * 说明: 从assets文件夹中读取json数据
 */
public class DataJsonUtil {

    public static  String getDataJson(Context context){
        try {
            //模拟网络操作
            SystemClock.sleep(2000);
            AssetManager assets = context.getAssets();
            InputStream ins = assets.open("data.json");
            byte[] bytes = new byte[ins.available()];
            ins.read(bytes);
            ins.close();
            return  new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
