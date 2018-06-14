package com.example.latte_lib.delegates.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.latte_core.app.UrlConfig;
import com.example.latte_core.delegates.index.MultipleFields;
import com.example.latte_core.delegates.main.BaseContentDelegate;
import com.example.latte_core.net.HttpMethod;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccessCallBack;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.LatteLogger;
import com.example.latte_lib.R;

import java.util.ArrayList;

public class IndexDelegate extends BaseContentDelegate {
    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url(UrlConfig.JSONURL)
                .method(HttpMethod.GET)
                .success(new ISuccessCallBack() {
                    @Override
                    public void success(String result) {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(result);
                        ArrayList<MultipleItemEntity> list = converter.converter();
                        Object field = list.get(0).getField(MultipleFields.BANNERS);
                        LatteLogger.d(field.toString());

                    }
                }).build().get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
