package com.bwei.crq.acitvity.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bwei.crq.R;
import com.bwei.crq.base.BaseActivity;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 09:59:04
 * @Description:
 */
public class InfoActivity extends BaseActivity {



    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String userId = intent.getExtras().getString("userId");
        String sessionId = intent.getExtras().getString("sessionId");
        Log.d("sb_info",userId+"**"+sessionId);
        //用户信息


    }
}
