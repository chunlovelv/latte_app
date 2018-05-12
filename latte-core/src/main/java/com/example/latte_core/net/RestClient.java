package com.example.latte_core.net;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;

import com.example.latte_core.app.Latte;
import com.example.latte_core.net.callback.IErrorCallback;
import com.example.latte_core.net.callback.IFailCallback;
import com.example.latte_core.net.callback.IRequestCallback;
import com.example.latte_core.net.callback.ISuccessCallBack;
import com.example.latte_core.ui.AVLoadingIndicators;
import com.example.latte_core.ui.LatteLoader;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestClient {
    private final String mUrl;
    private final Map<String, Object> mParams = new WeakHashMap<>();
    private final HttpMethod mMethod;
    private final ISuccessCallBack mISuccessCallBack;
    private final IErrorCallback mIErrorCallback;
    private final IRequestCallback mIRequestCallback;
    private final IFailCallback mIFailCallback;

    private final Context mContext;
    private final AVLoadingIndicators mIndicators;
    private final int mLoadingColor;

    private static Handler mHandler = new Handler();

    public RestClient(String url,
                      Map<String, Object> params,
                      HttpMethod method,
                      ISuccessCallBack successCallBack,
                      IErrorCallback errorCallback,
                      IRequestCallback requestCallback,
                      IFailCallback failCallback,
                      Context context,
                      AVLoadingIndicators indicators,
                      int loadingColor) {
        this.mUrl = url;
        this.mMethod = method;
        this.mParams.putAll(params);
        this.mISuccessCallBack = successCallBack;
        this.mIErrorCallback = errorCallback;
        this.mIRequestCallback = requestCallback;
        this.mIFailCallback = failCallback;
        this.mContext = context;
        this.mIndicators = indicators;
        this.mLoadingColor = loadingColor;
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getRestService();
        if (mIRequestCallback != null) {
            mIRequestCallback.requestStart();
        }

        if(mIndicators != null){
            if(mLoadingColor == 0){
                LatteLoader.showLoading(mContext, mIndicators);
            }else {
                LatteLoader.showLoading(mContext, mIndicators, mLoadingColor);
            }
        }

        Call<String> call = null;
        switch (httpMethod) {
            case GET:
                call = service.get(mUrl, mParams);
                break;
            case POST:
                call = service.post(mUrl, mParams);
                break;
            case PUT:
                call = service.put(mUrl, mParams);
                break;
            case DELETE:
                call = service.delete(mUrl, mParams);
                break;
            case DOWNLOAD:
                call = service.download(mUrl, mParams);
                break;
            case UPLOAD:
//                call = service.upload(mUrl, mParams);
                break;
            default:
                break;
        }
        call.enqueue(getRequestCallBack());
    }


    private RequestCallBack getRequestCallBack(){
        return  new RequestCallBack(mISuccessCallBack,
                mIErrorCallback,
                mIRequestCallback,
                mIFailCallback,
                mIndicators);
    }

    public void get(){
        request(HttpMethod.GET);
    }
    public void post(){
        request(HttpMethod.POST);
    }
    public void delete(){
        request(HttpMethod.DELETE);
    }
    public void put(){
        request(HttpMethod.PUT);
    }
    public void download(){
        request(HttpMethod.DOWNLOAD);
    }

    public static void stopLoading(int timeDelay){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.cancel();
            }
        }, timeDelay);
    }


    /**
     * 请求回调接口
     */
    private static final class RequestCallBack implements Callback<String> {
        private final ISuccessCallBack mISuccessCallBack;
        private final IErrorCallback mIErrorCallback;
        private final IRequestCallback mIRequestCallback;
        private final IFailCallback mIFailCallback;

        private final AVLoadingIndicators mAVLoadingIndicators;

        public RequestCallBack(ISuccessCallBack successCallBack,
                               IErrorCallback errorCallback,
                               IRequestCallback requestCallback,
                               IFailCallback failCallback,
                               AVLoadingIndicators indicators) {
            this.mISuccessCallBack = successCallBack;
            this.mIErrorCallback = errorCallback;
            this.mIRequestCallback = requestCallback;
            this.mIFailCallback = failCallback;
            this.mAVLoadingIndicators = indicators;
        }

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if (response.isSuccessful()) {
                if(mISuccessCallBack!= null){
                    mISuccessCallBack.success(response.body());
                }

                if(mIRequestCallback!= null){
                    mIRequestCallback.requestEnd();
                }

            } else {
                if (mIFailCallback != null) {
                    try {
                        mIFailCallback.failed(response.errorBody().string());
                    } catch (Exception e) {
                        if (mIErrorCallback != null) {
                            mIErrorCallback.error(response.code(), e);
                        }
                    }
                }
            }

            if(mAVLoadingIndicators != null){
                stopLoading(5000);
            }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            if (mIFailCallback != null) {
                mIFailCallback.failed(t.toString());
            }

            if(mAVLoadingIndicators != null){
                stopLoading(5000);
            }
        }
    }
}
