package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.LoginJson;
import com.bwei.crq.utils.RetroficUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 14:55:32
 * @Description:
 */
public class LoginModel {

    //接口回调传数据
    public interface OnLoginListener {
        void onMessage(LoginJson body);
    }

    private OnLoginListener onLoginListener;

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }


    public void login(String uphone, String upwd) {

        HashMap<String, String> map = new HashMap<>();
        map.put("phone",uphone);
        map.put("pwd",upwd);

        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.nUrl, ApiService.class);
        Call<LoginJson> loginData = service.getLoginData(map);
        loginData.enqueue(new Callback<LoginJson>() {
            @Override
            public void onResponse(Call<LoginJson> call, Response<LoginJson> response) {
                LoginJson body = response.body();

                onLoginListener.onMessage(body);
            }

            @Override
            public void onFailure(Call<LoginJson> call, Throwable t) {

            }
        });



         /*   //请求登录数据
        ApiService service = RetroficUtils.getInStence().getRetrofitService(Api.loginUrl, ApiService.class);
        Call<LoginJson> loginData = service.getLoginData(uphone, upwd);
        loginData.enqueue(new Callback<LoginJson>() {
            @Override
            public void onResponse(Call<LoginJson> call, Response<LoginJson> response) {
                LoginJson body = response.body();
                String message = body.getMessage();

                // LoginJson.ResultBean result = body.getResult();
                Log.d("zzzz",message);
            }

            @Override
            public void onFailure(Call<LoginJson> call, Throwable t) {

            }
        });*/



    }
}
