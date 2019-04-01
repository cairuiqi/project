package com.bwei.crq.model;


import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.GoodsBannerJson;
import com.bwei.crq.utils.RetroficUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 21:36:40
 * @Description:
 */
public class GoodsBannerModel {

    public interface OnGoodsBannerListener{
        void  onGoodsBannerData(List<GoodsBannerJson.ResultBean> result);
    }

    private OnGoodsBannerListener onGoodsBannerListener;


    public void setOnGoodsBannerListener(OnGoodsBannerListener onGoodsBannerListener) {
        this.onGoodsBannerListener = onGoodsBannerListener;
    }

    public void getGoodsBannerData() {

        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<GoodsBannerJson> call = service.getBannerData();
        call.enqueue(new Callback<GoodsBannerJson>() {
            @Override
            public void onResponse(Call<GoodsBannerJson> call, Response<GoodsBannerJson> response) {
                GoodsBannerJson body = response.body();
                List<GoodsBannerJson.ResultBean> result = body.getResult();
                Log.d("ppp",result.size()+"");
                onGoodsBannerListener.onGoodsBannerData(result);
            }

            @Override
            public void onFailure(Call<GoodsBannerJson> call, Throwable t) {

            }
        });


    }
}
