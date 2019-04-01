package com.bwei.crq.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.adapter.CircleListAdapter;
import com.bwei.crq.base.BaseFragment;
import com.bwei.crq.bean.CirclelistJosn;
import com.bwei.crq.presenter.CirclePresenter;
import com.bwei.crq.view.CircleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/17 16:29:21
 * @Description:
 */
public class CircleFragment extends BaseFragment implements CircleView {

    @BindView(R.id.rlv)
    RecyclerView rlv;

    Unbinder unbinder;

    private boolean isGetData = false;
    int page = 1;
    int count = 10;
    private CirclePresenter circlePresenter;

    @Override
    protected int layoutResID() {
        return R.layout.circlefragment;
    }

    @Override
    protected void initView(View view) {
    }


    @Override
    protected void loadData() {
        Log.d("frag", "CircleFragment");


    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            SharedPreferences cun = getActivity().getSharedPreferences("cun", getActivity().MODE_PRIVATE);
            String userId = cun.getString("userId", "");
            String sessionId = cun.getString("sessionId", "");
            Log.d("sb_circle", userId + "**" + sessionId);
            if (userId.equals("")) {
                Toast.makeText(getActivity(), " 圈子需要登陆", Toast.LENGTH_SHORT).show();
            } else {
                //圈子mvp
                circlePresenter = new CirclePresenter(this);
                circlePresenter.related(page, count);
                circlePresenter.attachView(this);
            }

        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }


    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    //圈子
    @Override
    public void onCircleResult(List<CirclelistJosn.ResultBean> result) {
                //设置适配器
        CircleListAdapter circleListAdapter = new CircleListAdapter(getActivity(),result);
        rlv.setAdapter(circleListAdapter);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        circlePresenter.dettach();
    }
}
