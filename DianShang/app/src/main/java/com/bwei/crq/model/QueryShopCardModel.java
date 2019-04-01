package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.utils.RetroficUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 17:06:19
 * @Description:
 */
public class QueryShopCardModel {

    public interface OnShppingListener{
        void  onShoppingData(List<QueryShopCartJson.ResultBean> result);
    }

    private OnShppingListener onShppingListener;

    public void setOnShppingListener(OnShppingListener onShppingListener) {
        this.onShppingListener = onShppingListener;
    }

    public void getQueryShopCartData(String userId, String sessionId) {


       ApiService service = RetroficUtils.getInStence().getserviserHand(Api.nUrl,userId,sessionId, ApiService.class);
        Call<QueryShopCartJson> queryShopCartJsonCall = service.queryShopCart();
        queryShopCartJsonCall.enqueue(new Callback<QueryShopCartJson>() {
            @Override
            public void onResponse(Call<QueryShopCartJson> call, Response<QueryShopCartJson> response) {
                QueryShopCartJson body = response.body();
                List<QueryShopCartJson.ResultBean> result = body.getResult();
                if(onShppingListener!=null){
                    onShppingListener.onShoppingData(result);
                }
             //   OnQueryShopCardListener.onQueryShopData(result);
                Log.d("result","result*****"+result);
            }

            @Override
            public void onFailure(Call<QueryShopCartJson> call, Throwable t) {
                Log.d("safdgf","sfdg");
            }
        });

    }
}
