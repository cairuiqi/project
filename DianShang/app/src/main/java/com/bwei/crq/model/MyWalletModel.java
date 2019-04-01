package com.bwei.crq.model;

import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.MyWallet;
import com.bwei.crq.utils.RxjavaUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 20:14:41
 * @Description:
 */
public class MyWalletModel {

    public interface OnMyWalletListener{
        void onMyWallet(MyWallet.ResultBean result);
    }

    private OnMyWalletListener onMyWalletListener;

    public void setOnMyWalletListener(OnMyWalletListener onMyWalletListener) {
        this.onMyWalletListener = onMyWalletListener;
    }

    public void getWallet(String userId, String sessionId, int page, int count) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<MyWallet> myWalletFlowable = service.myWallet(userId, sessionId, page, count);
        myWalletFlowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<MyWallet>() {
                            @Override
                            public void onNext(MyWallet myWallet) {
                                MyWallet.ResultBean result = myWallet.getResult();
                                if (onMyWalletListener!=null){
                                    onMyWalletListener.onMyWallet(result);
                                }
                                Log.d("mywallet",result.toString());
                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }
}
