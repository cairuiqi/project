package com.bwei.crq.utils;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 16:31:02
 * @Description:
 */
public class RxjavaUtils {

    private static RxjavaUtils rxjavaUtils;

    private RxjavaUtils(){

    }

    public static RxjavaUtils getInStence(){
        if(rxjavaUtils==null){
            synchronized (RxjavaUtils.class){
                rxjavaUtils=new RxjavaUtils();
            }
        }
        return rxjavaUtils;
    }


    //ok有请求头的对象

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOkHttp(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("拦截器",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(okHttpClient==null){
            okHttpClient= new OkHttpClient.Builder()
                         .addInterceptor(httpLoggingInterceptor)
                         .build();
        }

        return okHttpClient;
   }



    //retrfit对象

    public static Retrofit  getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


    //java的动态代理
    public <T>T getService(String url,Class<T> service){
        Retrofit retrofit = getRetrofit(url);
        T t = retrofit.create(service);
        return t;
    }





}
