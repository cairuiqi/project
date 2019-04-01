package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.ByStatusBean;
import com.bwei.crq.custom.AdderSubtractor;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/31 16:22:15
 * @Description:
 */
public class MyByStatusAdapter extends RecyclerView.Adapter<MyByStatusAdapter.MyViewHoder> {

    private Context context;
    private List<ByStatusBean.OrderListBean.DetailListBean> list;

    public MyByStatusAdapter(Context context, List<ByStatusBean.OrderListBean.DetailListBean> detailList) {
        this.context = context;
        this.list = detailList;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.bystatus_shopovercar_item, null);
        MyViewHoder myViewHoder = new MyViewHoder(view);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder myViewHoder, int i) {
        String[] split = list.get(i).getCommodityPic().split(",");
        myViewHoder.item_img.setImageURI(split[0]);
        myViewHoder.item_name.setText(list.get(i).getCommodityName());
        myViewHoder.item_price.setText("￥：" + list.get(i).getCommodityPrice());
        myViewHoder.add.setCount(list.get(i).getCommodityCount());
       /* myHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 =list.get(i).getCommodityCount();
                num1++;
                list.get(i).setCommodityCount(num1);
                if (qxOnclick!=null){
                    qxOnclick.OnClick();
                }
            }
        });
        myHolder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2=list.get(i).getCommodityCount();
                if (num2>1){
                    num2--;
                    list.get(i).setCommodityCount(num2);
                    if (qxOnclick!=null){
                        qxOnclick.OnClick();
                    }
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHoder extends RecyclerView.ViewHolder {


        private final SimpleDraweeView item_img;
        private final TextView item_name;
        private final TextView item_price;
        private final AdderSubtractor add;


        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
            add = itemView.findViewById(R.id.add);


        }
    }

}
