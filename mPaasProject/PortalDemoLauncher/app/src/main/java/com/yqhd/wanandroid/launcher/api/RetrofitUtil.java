package com.yqhd.wanandroid.launcher.api;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ibm on 2017/9/12.
 */

public class RetrofitUtil {

    public static final int DEFAULT_TIMEOUT = 10;
    public static final int READ_TIMEOUT = 10;
    public static final int WRITE_TIMEOUT = 10;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private static RetrofitUtil mInstance;
    /**
     * 私有构造方法
     */
    private RetrofitUtil(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT,TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS);
        builder.addInterceptor(new RspCheckInterceptor());
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(I.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstance(){
        if (mInstance == null){
            synchronized (RetrofitUtil.class){
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }
    public  ApiService getApiService(){
        return mApiService;
    }
}
