package com.bwei.crq.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.ByStatusBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 15:53:33
 * @Description:
 */
public class ByStatusAdapter extends RecyclerView.Adapter<ByStatusAdapter.MyHolder> {
    private Context context;
    private List<ByStatusBean.OrderListBean> list;

    public ByStatusAdapter(Context context, List<ByStatusBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bystatus_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.numbername.setText(list.get(i).getOrderId());
        //自提改变
        SpannableStringBuilder style=new SpannableStringBuilder("共计"+list.get(i).getDetailList().size()+"件商品,需付款"+list.get(i).getPayAmount()+"元");
        style.setSpan(new ForegroundColorSpan(Color.RED),2,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.RED),10,12,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        List<ByStatusBean.OrderListBean.DetailListBean> detailList = list.get(i).getDetailList();

        myHolder.price.setText(style);

        MyByStatusAdapter shopOverAdapter = new MyByStatusAdapter(context,detailList );
        myHolder.sta_rv.setAdapter(shopOverAdapter);
        myHolder.cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusClick!=null){
                    statusClick.delete(list.get(i).getOrderId());
                }
            }
        });
        myHolder.payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusClick!=null){
                    statusClick.gopay(list.get(i).getOrderId(),list.get(i).getPayAmount()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.numbername)
        TextView numbername;
        @BindView(R.id.data_time)
        TextView data_time;
        @BindView(R.id.sta_rv)
        RecyclerView sta_rv;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.cancle)
        Button cancle;
        @BindView(R.id.payment)
        Button payment;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            sta_rv.setLayoutManager(new LinearLayoutManager(context));
        }
    }
    public StatusClick statusClick;

    public void setStatusClick(StatusClick statusClick) {
        this.statusClick = statusClick;
    }

    public interface StatusClick{
        void delete(String orderId);
        void gopay(String orderId,String price);
    }
}
