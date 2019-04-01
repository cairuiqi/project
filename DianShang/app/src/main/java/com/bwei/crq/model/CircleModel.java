package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.CirclelistJosn;
import com.bwei.crq.utils.RetroficUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 21:13:16
 * @Description:
 */
public class CircleModel {

    public interface OnCircleListener{
        void onCircleResult( List<CirclelistJosn.ResultBean> result);
    }

    private OnCircleListener onCircleListener;

    public void setOnCircleListener(OnCircleListener onCircleListener) {
        this.onCircleListener = onCircleListener;
    }

    public void getCircle(int page, int count) {
        ApiService retrofitService = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<CirclelistJosn> circle = retrofitService.circle(page, count);
        circle.enqueue(new Callback<CirclelistJosn>() {
            @Override
            public void onResponse(Call<CirclelistJosn> call, Response<CirclelistJosn> response) {
                CirclelistJosn body = response.body();
                List<CirclelistJosn.ResultBean> result = body.getResult();
                onCircleListener.onCircleResult(result);
                Log.d("circle",result.size()+"");

            }

            @Override
            public void onFailure(Call<CirclelistJosn> call, Throwable t) {

            }
        });
    }

}
