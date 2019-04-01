package com.bwei.crq.app;

import android.app.Application;
import android.content.Context;

import com.bwei.crq.Constants;
import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 21:10:10
 * @Description:
 */
public class MyApp extends Application {



   /* private static MyApp instance;
    //初始化Watcher
    private RefWatcher mRefWatcher;
*/

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        //清空
        getSharedPreferences("cun", Context.MODE_PRIVATE).edit().clear().commit();
    /*    instance = this;
        mRefWatcher = Constants.DEBUG ?  LeakCanary.install(this) : RefWatcher.DISABLED;*/

    }



}
