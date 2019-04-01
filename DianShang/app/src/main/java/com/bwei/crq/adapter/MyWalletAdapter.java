package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.MyWallet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/29 20:43:37
 * @Description:
 */
public class MyWalletAdapter extends RecyclerView.Adapter<MyWalletAdapter.MyViewHoder> {

    private Context context;
    private List<MyWallet.ResultBean.DetailListBean> detailList;


    public MyWalletAdapter(Context context, List<MyWallet.ResultBean.DetailListBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mywallet_item, null);
        MyViewHoder myViewHoder = new MyViewHoder(view);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder myViewHoder, int i) {
        //赋值
        long consumerTime = detailList.get(i).getConsumerTime();
        String s = stampToDate(consumerTime);
        myViewHoder.price.setText("¥:"+detailList.get(i).getAmount()+".00");
        myViewHoder.time.setText(s);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }


    class MyViewHoder extends RecyclerView.ViewHolder{

        private final TextView price;
        private final TextView time;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
        }
    }

    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(long timeMillis){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

}
