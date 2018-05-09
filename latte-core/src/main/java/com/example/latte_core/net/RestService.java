package com.example.latte_core.net;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap HashMap<String,Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap HashMap<String,Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap HashMap<String,Object> params);


    @DELETE
    Call<String> delete(@Url String url, @QueryMap HashMap<String,Object> params);

    @Streaming
    @GET
    Call<String> download(@Url String url, @QueryMap HashMap<String,Object> params);


    @Streaming
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part params);

}
