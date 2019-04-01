package com.bwei.crq.presenter;

import com.bwei.crq.bean.CirclelistJosn;
import com.bwei.crq.fragment.CircleFragment;
import com.bwei.crq.model.CircleModel;
import com.bwei.crq.view.CircleView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 21:13:29
 * @Description:
 */
public class CirclePresenter<T> {

    private final CircleModel circleModel;
    private final CircleView circleView;
    private Reference<T> reference;

    public CirclePresenter(CircleView view) {
        circleModel = new CircleModel();
        circleView = view;
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


    public void related(int page, int count) {
        circleModel.getCircle(page,count);
        circleModel.setOnCircleListener(new CircleModel.OnCircleListener() {
            @Override
            public void onCircleResult(List<CirclelistJosn.ResultBean> result) {
                circleView.onCircleResult(result);
            }
        });

    }
}
