package com.example.latte_core.net;

import com.example.latte_core.net.callback.IErrorCallback;
import com.example.latte_core.net.callback.IFailCallback;
import com.example.latte_core.net.callback.IRequestCallback;
import com.example.latte_core.net.callback.ISuccessCallBack;

import java.util.Map;
import java.util.WeakHashMap;

public class RestClient {
    private final String mUrl;
    private final Map<String, Object> mParams = new WeakHashMap<>();
    private final HttpMethod mMethod;
    private final ISuccessCallBack mISuccessCallBack;
    private final IErrorCallback mIErrorCallback;
    private final IRequestCallback mIRequestCallback;
    private final IFailCallback mIFailCallback;

    public RestClient(String url,
                      Map<String, Object> params,
                      HttpMethod method,
                      ISuccessCallBack successCallBack,
                      IErrorCallback errorCallback,
                      IRequestCallback requestCallback,
                      IFailCallback failCallback) {
        this.mUrl = url;
        this.mMethod = method;
        this.mParams.putAll(params);
        this.mISuccessCallBack = successCallBack;
        this.mIErrorCallback = errorCallback;
        this.mIRequestCallback = requestCallback;
        this.mIFailCallback = failCallback;
    }
}
