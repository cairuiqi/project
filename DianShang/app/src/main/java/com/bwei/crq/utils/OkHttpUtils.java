package com.bwei.crq.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 09:05:27
 * @Description:
 */
public class OkHttpUtils {

    private static OkHttpUtils okHttpUtils;

    private OkHttpUtils() {

    }

    public static OkHttpUtils getInStence() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                okHttpUtils = new OkHttpUtils();

            }
        }
        return okHttpUtils;
    }


    //返回ok对象
    private static OkHttpClient okHttpClient;

    public static synchronized OkHttpClient getOkHttpClient(final String uid, final String sid) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("XXX", message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("userId", uid)
                                    .addHeader("sessionId", sid)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
        }
        return okHttpClient;
    }


    //get请求
    public static void doGet(String url, String uid, String sid, Callback callback) {
        //ok
        OkHttpClient okHttpClient = getOkHttpClient(uid, sid);
        //请求
        Request request = new Request.Builder()
                .url(url)
                .build();
        //执行请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }


    //post
    public static void postData(String url, String uid, String sid,Map<String, String> map, Callback callback) {
        //ok对象
        OkHttpClient okHttp = getOkHttpClient(uid,sid);
        //请求体
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }
        FormBody build = builder.build();
        //请求
        Request request = new Request.Builder().post(build).url(url).build();
        Call call = okHttp.newCall(request);
        call.enqueue(callback);


    }






}
