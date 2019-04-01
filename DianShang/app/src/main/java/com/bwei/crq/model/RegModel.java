package com.bwei.crq.model;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.RegJson;
import com.bwei.crq.utils.RetroficUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/23 14:27:12
 * @Description:
 */
public class RegModel {


    //接口回调传数据
    public interface OnRegListener {
        void onMessage(String message);
    }

    private OnRegListener onRegListener;

    public void setOnLoginListener(RegModel.OnRegListener onRegListener) {
        this.onRegListener = onRegListener;
    }


    public void getRegData(HashMap<String,String> hash) {
        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<RegJson> regData = service.getRegData(hash);
        regData.enqueue(new Callback<RegJson>() {
            @Override
            public void onResponse(Call<RegJson> call, Response<RegJson> response) {
                RegJson body = response.body();
                String message = body.getMessage();
                onRegListener.onMessage(message);
            }

            @Override
            public void onFailure(Call<RegJson> call, Throwable t) {

            }
        });


    }

}
