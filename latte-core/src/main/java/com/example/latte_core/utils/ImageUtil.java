package com.example.latte_core.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.latte_core.app.Latte;

import java.lang.annotation.Target;

/**
 * 作者: 李纯
 * 时间: 2018/6/15
 * 说明: 图片加载工具类
 */
public class ImageUtil {

    public static void loadImage(String url, ImageView imageTarget){
        Glide.with(Latte.getApplicationContext())
                .load(url)
                .into(imageTarget);
    }
}
