package com.example.latte_core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.latte_core.app.Latte;

public class DimenUtil {

    /**
     * 获取手机屏幕的宽
     * @return
     */
    public static int getScreenWidth(){
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }


    /**
     * 获取手机屏幕的高
     * @return
     */
    public static int getScreenHeight(){
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


}
