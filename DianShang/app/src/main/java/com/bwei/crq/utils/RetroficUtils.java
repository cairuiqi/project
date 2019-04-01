package com.bwei.crq.utils;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 20:28:47
 * @Description:
 */
public class RetroficUtils {


    private static RetroficUtils retroficUtils;

    private RetroficUtils() {

    }

    public static RetroficUtils getInStence() {
        if (retroficUtils == null) {
            synchronized (RetroficUtils.class) {
                if (retroficUtils == null) {
                    retroficUtils = new RetroficUtils();
                }
            }
        }
        return retroficUtils;
    }

    //ok对象

    private static OkHttpClient okHttpClient;


    public static synchronized OkHttpClient getOkHttpClient() {
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("xxx", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    //日志拦截器
                    .addInterceptor(httpLoggingInterceptor)
                    //添加网络拦截器
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();

        }
        return okHttpClient;
    }


    private static OkHttpClient okHttpClientttt=null;
    public static OkHttpClient getOkHttpClient(final String uid, final String sid){
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
               Log.i("lanjie",message);
            }
        });
        //创建日志拦截器模式
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClientttt = new OkHttpClient.Builder()
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)

                //添加网络拦截器
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

                //添加运用拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request builder1 = chain.request().newBuilder()
                                .addHeader("userId",uid)
                                .addHeader("sessionId",sid)
                                .build();
                        return chain.proceed(builder1);
                    }
                })
                .build();

        return okHttpClientttt;
    }







    //得到retrofit对象
    public static  Retrofit getRetrofit(String url) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return builder;
    }




    //java的代理对象
    public <T> T getRetrofitService(String url, Class<T> service) {
        Retrofit retrofit = getRetrofit(url);
        T t = retrofit.create(service);
        return t;
    }


    //请求方式 带请求头
    public static Retrofit getretrofinHand(String url,String uid,String sid){
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient(uid,sid))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return builder;
    }

    public <T>T getserviserHand(String url,String uid,String sid,Class<T>service){
        Retrofit retrofit = getretrofinHand(url, uid, sid);
        T t = retrofit.create(service);
        return t;
    }








}

