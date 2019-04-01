package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.ShoppingAddCarJson;
import com.bwei.crq.utils.RetroficUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 20:34:06
 * @Description:
 */
public class ShppingAddModel {


    public interface OnShoppingAddListener{
        void  onMessage(String message );
    }

    private OnShoppingAddListener onShoppingAddListener;

    public void setOnShoppingAddListener(OnShoppingAddListener onShoppingAddListener) {
        this.onShoppingAddListener = onShoppingAddListener;
    }

    public void getShppingAddData(String map, String userId, String sessionId) {

        ApiService retrofitService = RetroficUtils.getInStence().getserviserHand(Api.nUrl,userId,sessionId,ApiService.class);
        Call<ShoppingAddCarJson> shoppingcar = retrofitService.shoppingcar(map);
        shoppingcar.enqueue(new Callback<ShoppingAddCarJson>() {
            @Override
            public void onResponse(Call<ShoppingAddCarJson> call, Response<ShoppingAddCarJson> response) {
                ShoppingAddCarJson body = response.body();
                String message = body.getMessage();
                onShoppingAddListener.onMessage(message);
            }

            @Override
            public void onFailure(Call<ShoppingAddCarJson> call, Throwable t) {

            }
        });

    }
}
