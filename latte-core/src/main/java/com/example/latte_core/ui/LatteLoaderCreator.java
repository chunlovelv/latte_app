package com.example.latte_core.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.latte_core.R;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class LatteLoaderCreator {

    private static final Map<String, Indicator> INDICATORS = new WeakHashMap<>();
    private static final int LOADING_COLOR = 0xffffffff;
    public static AVLoadingIndicatorView create(AVLoadingIndicators indicators, Context context){
        return create(indicators, context, LOADING_COLOR);
    }

    public static AVLoadingIndicatorView create(AVLoadingIndicators indicators, Context context, int loadingColor){
        if(indicators == null){
            throw new  NullPointerException("indicators is null");
        }
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        avLoadingIndicatorView.setIndicator(getIndicator(indicators.name()));
        avLoadingIndicatorView.setIndicatorColor(loadingColor);
        return avLoadingIndicatorView;
    }




    /**
     * 通过缓存的方式获取Indicator
     * @param indicatorName
     * @return
     */
    private static Indicator getIndicator(String indicatorName){
        if (TextUtils.isEmpty(indicatorName)){
            return null;
        }
        Indicator indicator = null;
        if(INDICATORS.containsKey(indicatorName)){
            indicator = INDICATORS.get(indicatorName);
        }else {
            StringBuilder drawableClassName=new StringBuilder();
            if (!indicatorName.contains(".")){
                String defaultPackageName= AVLoadingIndicatorView.class.getPackage().getName();
                drawableClassName.append(defaultPackageName)
                        .append(".indicators")
                        .append(".");
            }
            drawableClassName.append(indicatorName);
            try {
                Class<?> drawableClass = Class.forName(drawableClassName.toString());
                indicator = (Indicator) drawableClass.newInstance();
                INDICATORS.put(indicatorName, indicator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return indicator;
    }

}
