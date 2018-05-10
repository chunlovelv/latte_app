package com.example.latte_core.net;

import com.example.latte_core.net.callback.IErrorCallback;
import com.example.latte_core.net.callback.IFailCallback;
import com.example.latte_core.net.callback.IRequestCallback;
import com.example.latte_core.net.callback.ISuccessCallBack;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.OkHttpClient;

public class RestClientBuilder {

    private String mUrl;
    private Map<String, Object> mParams = new WeakHashMap<>();
    private HttpMethod mMethod = HttpMethod.GET;
    private ISuccessCallBack mISuccessCallBack;
    private IErrorCallback mIErrorCallback;
    private IRequestCallback mIRequestCallback;
    private IFailCallback mIFailCallback;


    public RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params( Map<String, Object> params){
        this.mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params( String key, Object value){
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder method(HttpMethod method){
        this.mMethod = method;
        return this;
    }

    public final RestClientBuilder success(ISuccessCallBack successCallBack){
        this.mISuccessCallBack = successCallBack;
        return this;
    }
    public final RestClientBuilder error(IErrorCallback errorCallback){
        this.mIErrorCallback = errorCallback;
        return this;
    }
    public final RestClientBuilder faile(IFailCallback failCallback){
        this.mIFailCallback = failCallback;
        return this;
    }
    public final RestClientBuilder request(IRequestCallback requestCallback){
        this.mIRequestCallback = requestCallback;
        return this;
    }

    public final RestClient build(){
        return  new RestClient(mUrl,
                mParams,
                mMethod,
                mISuccessCallBack,
                mIErrorCallback,
                mIRequestCallback,
                mIFailCallback);
    }
}
