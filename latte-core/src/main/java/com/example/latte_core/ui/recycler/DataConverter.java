package com.example.latte_core.ui.recycler;

import android.text.TextUtils;

import java.util.ArrayList;

import retrofit2.Retrofit;

/**
 * 作者: 李纯
 * 时间: 2018/6/14
 * 说明: 数据转换器
 */
public abstract class DataConverter {


    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData;

    protected abstract ArrayList<MultipleItemEntity> converter();

    public DataConverter setJsonData(String jsonData){
        mJsonData = jsonData;
        return this;
    }

    public String getJsonData(){
        if(TextUtils.isEmpty(mJsonData)){
            throw new NullPointerException("Data is null!");
        }
        return mJsonData;
    }
}
