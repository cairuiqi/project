package com.bwei.crq.presenter;

import android.view.View;

import com.bwei.crq.bean.FirstList;
import com.bwei.crq.model.FirstListModel;
import com.bwei.crq.view.FirstListView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/30 16:23:00
 * @Description:
 */
public class FirstListPresenter<T> {

    private final FirstListModel firstListModel;
    private final FirstListView firstListView;
    private Reference<T> reference;

    public FirstListPresenter(FirstListView view) {
        firstListModel = new FirstListModel();
        firstListView = view;
    }


    //内存泄漏
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }


    public void dettach(){
        if(reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }


    public void related(CompositeDisposable disposable) {
        firstListModel.getFirstList(disposable);
        firstListModel.setOnFristListListener(new FirstListModel.OnFristListListener() {
            @Override
            public void getFirstList(List<FirstList.ResultBean> result) {
                firstListView.getFirstList(result);
            }
        });
    }
}
