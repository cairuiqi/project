package com.bwei.crq.model;

import android.net.Uri;
import android.util.Log;

import com.bwei.crq.api.Api;
import com.bwei.crq.api.ApiService;
import com.bwei.crq.bean.FirstList;
import com.bwei.crq.utils.RxjavaUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/30 16:22:45
 * @Description:
 */
public class FirstListModel {

    public interface OnFristListListener{
        void getFirstList(List<FirstList.ResultBean> result);
    }

    private OnFristListListener onFristListListener;

    public void setOnFristListListener(OnFristListListener onFristListListener) {
        this.onFristListListener = onFristListListener;
    }

    public void getFirstList(CompositeDisposable disposable) {
        ApiService service = RxjavaUtils.getInStence().getService(Api.nUrl, ApiService.class);
        Flowable<FirstList> firstListFlowable = service.oneList();
        DisposableSubscriber<FirstList> disposableSubscriber = firstListFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<FirstList>() {
                    @Override
                    public void onNext(FirstList firstList) {
                        List<FirstList.ResultBean> result = firstList.getResult();
                         onFristListListener.getFirstList(result);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加到订阅管理器
        disposable.add(disposableSubscriber);
    }
}
