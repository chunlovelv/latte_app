package com.example.john.latte_ec;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.animation.AVLoadingIndicators;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.net.HttpMethod;
import com.example.latte_core.net.RestClientBuilder;
import com.example.latte_core.net.callback.IErrorCallback;
import com.example.latte_core.net.callback.ISuccessCallBack;
import com.wang.avi.AVLoadingIndicatorView;

public class TestDelegate extends LatteDelegate {

    AVLoadingIndicatorView avloadingview;

    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {

        avloadingview = rootView.findViewById(R.id.avloadingview);
        avloadingview.setIndicator(AVLoadingIndicators.BallClipRotateIndicator.name());
        avloadingview.show();
        new RestClientBuilder()
                .url("https://chuanke.baidu.com/")
                .method(HttpMethod.GET)
                .success(new ISuccessCallBack() {
                    @Override
                    public void success(String result) {
                        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IErrorCallback() {
                    @Override
                    public void error(int code, Throwable t) {

                    }
                })
                .build()
        .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_test;
    }
}
