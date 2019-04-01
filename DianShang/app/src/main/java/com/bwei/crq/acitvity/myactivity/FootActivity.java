package com.bwei.crq.acitvity.myactivity;

import android.content.Intent;
import android.util.Log;

import com.bwei.crq.R;
import com.bwei.crq.base.BaseActivity;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 11:43:20
 * @Description:
 */
public class FootActivity extends BaseActivity {
    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_foot;
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
