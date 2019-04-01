package com.bwei.crq.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.crq.R;
import com.bwei.crq.acitvity.LoginActivity;
import com.bwei.crq.acitvity.myactivity.AddadresActivity;
import com.bwei.crq.acitvity.myactivity.CircleActivity;
import com.bwei.crq.acitvity.myactivity.FootActivity;
import com.bwei.crq.acitvity.myactivity.InfoActivity;
import com.bwei.crq.acitvity.myactivity.WalletActivity;
import com.bwei.crq.base.BaseFragment;
import com.bwei.crq.presenter.QueryShopCardPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/17 16:29:21
 * @Description:
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.information)
    Button information;
    @BindView(R.id.myuser)
    TextView myuser;
    @BindView(R.id.circle)
    Button circle;
    @BindView(R.id.mycircle)
    TextView mycircle;
    @BindView(R.id.foot)
    Button foot;
    @BindView(R.id.myfoot)
    TextView myfoot;
    @BindView(R.id.walle)
    Button walle;
    @BindView(R.id.mywalle)
    TextView mywalle;
    @BindView(R.id.myaddress)
    TextView myaddress;
    Unbinder unbinder;
    private SharedPreferences cun;
    private String userId;
    private String sessionId;


    @Override
    protected int layoutResID() {
        return R.layout.myfragment;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        text.setText("你好呀哈哈哈哈!");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void loadData() {
        Log.d("frag", "MyFragment");
        cun = getActivity().getSharedPreferences("cun", getActivity().MODE_PRIVATE);

        userId = cun.getString("userId", "");
        sessionId = cun.getString("sessionId", "");
        Log.d("sb_my", userId +"**"+ sessionId);



    }


    //接收EventBus发送的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUserInfo(String string) {

        String[] split = string.split(",");
        String headPic = split[0];
        String nickName1 = split[1];
        //设置头像
        Glide.with(getActivity()).load(headPic).into(img);
        //设置昵称
        text.setText(nickName1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.myuser, R.id.mycircle, R.id.myfoot, R.id.mywalle, R.id.myaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myuser:
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("sessionId",sessionId);
                startActivity(intent);

                break;
            case R.id.mycircle:
                Intent intent1 = new Intent(getActivity(), CircleActivity.class);
                intent1.putExtra("userId",userId);
                intent1.putExtra("sessionId",sessionId);
                startActivity(intent1);

                break;
            case R.id.myfoot:
                Intent intent2 = new Intent(getActivity(), FootActivity.class);
                intent2.putExtra("userId",userId);
                intent2.putExtra("sessionId",sessionId);
                startActivity(intent2);

                break;
            case R.id.mywalle:
                Intent intent3 = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent3);

                break;
            case R.id.myaddress:
                Intent intent4 = new Intent(getActivity(), AddadresActivity.class);

                startActivity(intent4);

                break;
        }
    }
}
