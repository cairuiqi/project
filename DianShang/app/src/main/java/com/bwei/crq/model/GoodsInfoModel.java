package com.bwei.crq.model;


import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.GoodsInfoJson;
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
 * @Date: 2019/3/20 19:32:03
 * @Description:
 */
public class GoodsInfoModel {


    //接口回调
    public interface OnGetGoodsInfoListen {
        void onGoodsInfoData(GoodsInfoJson goodsInfoJson);
    }

    private OnGetGoodsInfoListen onGetGoodsInfoListen;

    public void setOnGetGoodsListen(OnGetGoodsInfoListen onGetGoodsInfoListen) {
        this.onGetGoodsInfoListen = onGetGoodsInfoListen;
    }


    //得到商品信息
    public void getGoodsInfoData() {
        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<GoodsInfoJson> call = service.getGoodsInfoData();
        call.enqueue(new Callback<GoodsInfoJson>() {
            @Override
            public void onResponse(Call<GoodsInfoJson> call, Response<GoodsInfoJson> response) {
                GoodsInfoJson body = response.body();
                onGetGoodsInfoListen.onGoodsInfoData(body);
            }

            @Override
            public void onFailure(Call<GoodsInfoJson> call, Throwable t) {

            }
        });

     /*   Flowable<GoodsInfoJson> flowable = service.getGoodsInfoData();
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<GoodsInfoJson>() {
                    @Override
                    public void onNext(GoodsInfoJson goodsInfoJson) {
                        onGetGoodsInfoListen.onGoodsInfoData(goodsInfoJson);
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
