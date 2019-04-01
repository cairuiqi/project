package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.GoodsDetailsJson;
import com.bwei.crq.utils.RetroficUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 11:24:03
 * @Description:
 */
public class DetailsModel {

    //接口回调
    public interface OnGoodsDetailsListener{
        void onGoodsDetailsData(GoodsDetailsJson.ResultBean result);
    }

    private OnGoodsDetailsListener onGoodsDetailsListener;

    public void setOnGoodsDetailsListener(OnGoodsDetailsListener onGoodsDetailsListener) {
        this.onGoodsDetailsListener = onGoodsDetailsListener;
    }

    public void getGoodsDetails(int commodityId) {
        //请求数据
        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<GoodsDetailsJson> call = service.getGoodsDetailsData(commodityId);
        call.enqueue(new Callback<GoodsDetailsJson>() {
            @Override
            public void onResponse(Call<GoodsDetailsJson> call, Response<GoodsDetailsJson> response) {
                GoodsDetailsJson body = response.body();
                GoodsDetailsJson.ResultBean result = body.getResult();
                onGoodsDetailsListener.onGoodsDetailsData(result);
            }

            @Override
            public void onFailure(Call<GoodsDetailsJson> call, Throwable t) {

            }
        });

        /* Flowable<GoodsDetailsJson> flowable = service.getGoodsDetailsData(commodityId);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<GoodsDetailsJson>() {
                    @Override
                    public void onNext(GoodsDetailsJson goodsDetailsJson) {
                        //数据
                        GoodsDetailsJson.ResultBean result = goodsDetailsJson.getResult();
                        Log.d("uuu",result.toString());
                        onGoodsDetailsListener.onGoodsDetailsData(result);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
