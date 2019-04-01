package com.bwei.crq.fragment.orderfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.acitvity.orderactivity.OrderActivity;
import com.bwei.crq.acitvity.shopingactivity.PayActivity;
import com.bwei.crq.adapter.ByStatusAdapter;
import com.bwei.crq.base.BaseFragment;
import com.bwei.crq.bean.ByStatusBean;
import com.bwei.crq.presenter.ByStatusPresenter;
import com.bwei.crq.presenter.DeleteOrderPresenter;
import com.bwei.crq.view.ByStatusView;
import com.bwei.crq.view.DeleteOrderView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.internal.operators.observable.ObservableNever;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:26:18
 * @Description:
 */
public class PaymentFragment extends BaseFragment  implements ByStatusView,DeleteOrderView {
    @BindView(R.id.payment_rv)
    RecyclerView paymentRv;
    Unbinder unbinder;


    private int page=1;
    private int count=20;
    private ByStatusPresenter byStatusPresenter;
    private DeleteOrderPresenter deleteOrderPresenter;


    @Override
    protected int layoutResID() {
        return R.layout.payfragment;
    }

    @Override
    protected void initView(View view) {
         unbinder = ButterKnife.bind(this, view);
         paymentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void loadData() {
        //mvp
        HashMap<String,String> map = new HashMap<>();
        map.put("status","1");
        map.put("page",page+"");
        map.put("count",count+"");
        SharedPreferences cun = getActivity().getSharedPreferences("cun", getActivity().MODE_PRIVATE);
        String userId = cun.getString("userId", "");
        String sessionId = cun.getString("sessionId", "");
        Log.d("paystatus",userId+"***"+sessionId);
        byStatusPresenter = new ByStatusPresenter(this);
        byStatusPresenter.related(userId,sessionId,map);
        byStatusPresenter.attachView(this);
        deleteOrderPresenter = new DeleteOrderPresenter(this);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //根据订单状态查询的数据
    @Override
    public void getByStatus(List<ByStatusBean.OrderListBean> orderList) {
        ByStatusAdapter byStatusAdapte=new ByStatusAdapter(getActivity(),orderList);
        paymentRv.setAdapter(byStatusAdapte);
        byStatusAdapte.setStatusClick(new ByStatusAdapter.StatusClick() {
            @Override
            public void delete(String orderId) {
                SharedPreferences cun = getActivity().getSharedPreferences("cun", getActivity().MODE_PRIVATE);

                String userId = cun.getString("userId", "");
                String sessionId = cun.getString("sessionId", "");
                Log.d("orderid",orderId);
                deleteOrderPresenter.related(userId,sessionId,orderId);
                deleteOrderPresenter.attachView(this);
            }

            @Override
            public void gopay(String orderId, String price) {
                //跳转到支付页面
                Intent intent = new Intent(getActivity(), PayActivity.class);

                intent.putExtra("orderId",orderId);
                intent.putExtra("tempprice",price);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        byStatusPresenter.dettach();
        deleteOrderPresenter.dettach();
    }

    //删除
    @Override
    public void deleteOrder(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
