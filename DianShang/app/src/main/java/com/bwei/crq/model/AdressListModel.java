package com.bwei.crq.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.AddressList;
import com.bwei.crq.utils.OkHttpUtils;
import com.bwei.crq.utils.RetroficUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 17:36:42
 * @Description:
 */
public class AdressListModel {


    //接口回调传数据
    public interface OnAdressListListener {
        void onAdressListResult( List<AddressList.ResultBean> result);
    }

    private OnAdressListListener onAdressListListener;

    public void setOnAdressListListener(OnAdressListListener onAdressListListener) {
        this.onAdressListListener = onAdressListListener;
    }






    public void getAdressList(String userId, String sessionId) {
        String url="http://172.17.8.100/small/user/verify/v1/receiveAddressList";
        OkHttpUtils.getInStence().doGet(url, userId, sessionId, new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=string;
                handler.sendMessage(message);
            }
        });

      /*  ApiService apiService = RetroficUtils.getInStence().getserviserHand(Api.nUrl, userId, sessionId, ApiService.class);
        Call<AddressList> addressListCall = apiService.addressList();
        addressListCall.enqueue(new Callback<AddressList>() {
            @Override
            public void onResponse(Call<AddressList> call, Response<AddressList> response) {
                AddressList body = response.body();
                List<AddressList.ResultBean> result = body.getResult();

                onAdressListListener.onAdressListResult(result);
            }

            @Override
            public void onFailure(Call<AddressList> call, Throwable t) {

            }
        });
*/
    }


    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                   String obj= (String) msg.obj;
                    Gson gson = new Gson();
                    AddressList addressList = gson.fromJson(obj, AddressList.class);
                    List<AddressList.ResultBean> result = addressList.getResult();
                    onAdressListListener.onAdressListResult(result);
                    break;
            }
        }
    };
}
