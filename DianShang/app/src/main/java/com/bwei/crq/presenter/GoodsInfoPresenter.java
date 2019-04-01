package com.bwei.crq.presenter;

import com.bwei.crq.bean.GoodsInfoJson;
import com.bwei.crq.model.GoodsInfoModel;
import com.bwei.crq.view.GoodsInfoView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/20 19:32:15
 * @Description:
 */
public class GoodsInfoPresenter<T>{

    private final GoodsInfoModel goodsModel;
    private final GoodsInfoView goodsView;
    private Reference<T> reference;
    //有参构造


    public GoodsInfoPresenter(GoodsInfoView view) {
        //创建model
        goodsModel = new GoodsInfoModel();

        goodsView = view;

    }

    public void attachView(T t) {
        //使用弱引用
        reference = new WeakReference<>(t);
    }


    //解除关联
    public void detchView(){
        if(reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }

    //关联
    public void related() {

       goodsModel.getGoodsInfoData();
       goodsModel.setOnGetGoodsListen(new GoodsInfoModel.OnGetGoodsInfoListen() {
           @Override
           public void onGoodsInfoData(GoodsInfoJson goodsInfoJson) {
               goodsView.onGoodsInfoData(goodsInfoJson);
           }
       });
    }

}
