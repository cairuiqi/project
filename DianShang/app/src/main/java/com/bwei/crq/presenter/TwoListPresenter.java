package com.bwei.crq.presenter;

import com.bwei.crq.adapter.ListOneAdapter;
import com.bwei.crq.bean.TwoList;
import com.bwei.crq.model.TwoListModel;
import com.bwei.crq.view.TwoListView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/30 17:11:39
 * @Description:
 */
public class TwoListPresenter<T> {

    private final TwoListModel twoListModel;
    private Reference<T> reference;
    private final TwoListView twoListView;

    public TwoListPresenter(TwoListView view) {
        twoListModel = new TwoListModel();
        twoListView = view;
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

    public void related(CompositeDisposable disposable, String id) {
        twoListModel.getTwoList(disposable,id);
        twoListModel.setOnTwoListListener(new TwoListModel.OnTwoListListener() {
            @Override
            public void getTwoLit(List<TwoList.ResultBean> result) {
                twoListView.getTwoLit(result);
            }
        });
    }
}
