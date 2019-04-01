package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.GoodsSerchJson;
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
 * @Date: 2019/3/19 20:22:41
 * @Description:
 */
public class GoodsSerchModel {


    //接口回调
    public interface OnGetGoodSerchsListen {

        void onGoodsSerchResult(List<GoodsSerchJson.ResultBean> result);
    }

    private OnGetGoodSerchsListen onGetGoodSerchsListen;

    public void setOnGetGoodSerchsListen(OnGetGoodSerchsListen onGetGoodSerchsListen) {
        this.onGetGoodSerchsListen = onGetGoodSerchsListen;
    }


    //获得数据
    public void getSerchData(String data, int page, int count) {
        //调用工具类
        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<GoodsSerchJson> goodsSerchData = service.getGoodsSerchData(data, page, count);
        goodsSerchData.enqueue(new Callback<GoodsSerchJson>() {
            @Override
            public void onResponse(Call<GoodsSerchJson> call, Response<GoodsSerchJson> response) {
                GoodsSerchJson body = response.body();
                List<GoodsSerchJson.ResultBean> result = body.getResult();
                onGetGoodSerchsListen.onGoodsSerchResult(result);
            }

            @Override
            public void onFailure(Call<GoodsSerchJson> call, Throwable t) {

            }
        });

    }
}
