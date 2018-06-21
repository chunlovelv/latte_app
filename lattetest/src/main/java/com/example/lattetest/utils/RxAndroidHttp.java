package com.example.lattetest.utils;

import android.content.Context;
import android.os.Environment;

import com.example.lattetest.bean.DouBanBook;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者: 李纯
 * 时间: 2018/6/20
 * 说明: 网络操作工具类
 */
public class RxAndroidHttp {
    public static final String DOUBAN_URL = "https://api.douban.com/v2/book/search?q=金瓶梅tag=&start=0&count=1";
    private static Gson GSON;

    private static RxAndroidHttp INSTANCE = null;
    private static OkHttpClient OKHTTPCLIENT = null;
    private static String FILE_CATCH_PATH = Environment.getExternalStorageDirectory().getPath()
            + "/catch.json";
    volatile static boolean isFromDisk = false;


    private RxAndroidHttp(Context context) {
        OKHTTPCLIENT = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        GSON = new Gson();
    }

    public static RxAndroidHttp newInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RxAndroidHttp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RxAndroidHttp(context);
                }
            }
        }

        return INSTANCE;
    }

    public void request1(final RxAndroidHttpCallback callback) {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                Request okHttpRequest = new Request.Builder()
                        .url(DOUBAN_URL)
                        .get()
                        .build();
                Response response = OKHTTPCLIENT.newCall(okHttpRequest).execute();
                emitter.onNext(response);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).map(new Function<Response, DouBanBook>() {
            @Override
            public DouBanBook apply(Response response) throws Exception {
                ResponseBody body = response.body();
                if (response.isSuccessful()) {
                    return GSON.fromJson(body.string(), DouBanBook.class);
//                    return GSON.fromJson(body.string(),
//                            ((ParameterizedType) callback.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                } else {
                    callback.error(null);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DouBanBook>() {
            @Override
            public void accept(DouBanBook douBanBook) throws Exception {
                callback.success(douBanBook);
            }
        });
    }

    public void request2() {
        StringBuffer stringBuffer = new StringBuffer("request2");

        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {

            }
        });


    }

    public void request_cat() {

        //读取文件缓存
        Observable<DouBanBook> catchData = Observable.create(new ObservableOnSubscribe<DouBanBook>() {
            @Override
            public void subscribe(ObservableEmitter<DouBanBook> emitter) throws Exception {
                File file = new File(FILE_CATCH_PATH);
                if (file.exists()) {
                    FileInputStream fis = null;
                    fis = new FileInputStream(file);
                    StringBuffer stringBuffer = new StringBuffer();
                    int len;
                    byte[] buffer = new byte[1024];
                    String line;
                    while ((len = fis.read(buffer)) != -1) {
                        line = new String(buffer, 0, len);
                        stringBuffer.append(line);
                    }
                    if (fis != null) {
                        fis.close();
                    }
                    Gson gson = new Gson();
                    DouBanBook douBanBook = gson.fromJson(stringBuffer.toString(), DouBanBook.class);
                    emitter.onNext(douBanBook);
                    isFromDisk = true;
                }
                emitter.onComplete();
            }
        });

        //读取网络数据
        Observable<DouBanBook> netData = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                if (!isFromDisk) {
                    Request okHttpRequest = new Request.Builder()
                            .url(DOUBAN_URL)
                            .get()
                            .build();
                    Response response = OKHTTPCLIENT.newCall(okHttpRequest).execute();
                    emitter.onNext(response);
                    emitter.onComplete();
                }

            }
        }).map(new Function<Response, DouBanBook>() {
            @Override
            public DouBanBook apply(Response response) throws Exception {
                ResponseBody body = response.body();
                if (response.isSuccessful()) {
                    String s = body.toString();
                    DouBanBook douBanBook = GSON.fromJson(body.string(), DouBanBook.class);
                    File file = new File(FILE_CATCH_PATH);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(GSON.toJson(douBanBook).getBytes());
                    fos.flush();
                    fos.close();
                    return  douBanBook;
                }
                return null;
            }
        });

        Observable.concat(catchData, netData)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<DouBanBook>() {
                    @Override
                    public void accept(DouBanBook book) throws Exception {
                        if(book == null) return;
                        if (isFromDisk) {
                            System.out.println("isFromDisk==> " + book.toString());
                        } else {
                            System.out.println("isNotFromDisk==> " + book.toString());
                        }
                        isFromDisk = false;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("throwable==> " + throwable.toString());
                    }
                });


    }


    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                Request okHttpRequest = new Request.Builder()
                        .url("https://www.baidu.com/")
                        .get()
                        .build();
                OkHttpClient okHttpClient = new OkHttpClient();
                Response response = okHttpClient.newCall(okHttpRequest).execute();
                emitter.onNext(response);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                System.out.println("isSuccessful==> " + response.body());
                System.out.println("");
            }
        });
    }


    public interface RxAndroidHttpCallback<T> {
        void success(T t);

        void error(Throwable throwable);
    }

}
