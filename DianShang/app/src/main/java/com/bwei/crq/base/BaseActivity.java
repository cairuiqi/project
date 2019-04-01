package com.bwei.crq.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwei.crq.app.MyApp;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/26 15:07:31
 * @Description:
 */
public abstract class BaseActivity<T> extends AppCompatActivity {
    //在父类声明
    public T presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        presenter = getPresenter();
        initView();
        initData();


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当activity销毁调用观察内存泄漏的方法
    //    MyApp.getRefWatcher().watch(this);
    }




    protected abstract T getPresenter();
    //返回布局id
    protected abstract int layoutResID();

    //初始化控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();





}
