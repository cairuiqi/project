package com.bwei.crq.presenter;

import com.bwei.crq.bean.GoodsBannerJson;
import com.bwei.crq.fragment.HomeFragment;
import com.bwei.crq.model.GoodsBannerModel;
import com.bwei.crq.view.GoodsBannerView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 21:36:25
 * @Description:
 */
public class GoodsBannerPresenter<T> {

    private final GoodsBannerModel goodsBannerModel;
    private final GoodsBannerView goodsBannerView;
    private Reference<T> reference;

    public GoodsBannerPresenter(GoodsBannerView view) {
        goodsBannerModel = new GoodsBannerModel();
        goodsBannerView = view;
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


    public void related() {
        goodsBannerModel.getGoodsBannerData();
         goodsBannerModel.setOnGoodsBannerListener(new GoodsBannerModel.OnGoodsBannerListener() {
            @Override
            public void onGoodsBannerData(List<GoodsBannerJson.ResultBean> result) {
                goodsBannerView.onGoodsBannerData(result);
            }
        });
    }
}
