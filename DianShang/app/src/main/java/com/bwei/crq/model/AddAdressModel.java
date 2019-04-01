package com.bwei.crq.model;


import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.LoginJson;
import com.bwei.crq.bean.ShoppingCarBean;
import com.bwei.crq.utils.RetroficUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 16:04:21
 * @Description:
 */
public class AddAdressModel {

    //接口回调

    //接口回调传数据
    public interface OnAddAdressListener {
        void onMessage(String message );
    }

    private OnAddAdressListener  onAddAdressListener;

    public void setOnAddAdressListener(OnAddAdressListener onAddAdressListener) {
        this.onAddAdressListener = onAddAdressListener;
    }

    public void getAdressData(String userId, String sessionId, HashMap<String,String> map) {
        ApiService apiService = RetroficUtils.getInStence().getserviserHand(Api.nUrl, userId, sessionId, ApiService.class);
        Call<ShoppingCarBean> call = apiService.addaddress(map);
        call.enqueue(new Callback<ShoppingCarBean>() {
            @Override
            public void onResponse(Call<ShoppingCarBean> call, Response<ShoppingCarBean> response) {
                ShoppingCarBean body = response.body();
                String status = body.getStatus();
                String message = body.getMessage();
                Log.d("address",status+"**"+message);
                onAddAdressListener.onMessage(message);
            }

            @Override
            public void onFailure(Call<ShoppingCarBean> call, Throwable t) {

            }
        });


    }
}
