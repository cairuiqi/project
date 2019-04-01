package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.bwei.crq.R;
import com.bwei.crq.bean.QueryShopCartJson;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatAdapter extends RecyclerView.Adapter<CreatAdapter.MyHolder> {

    private Context context;
    private   ArrayList<QueryShopCartJson.ResultBean> list;

    public CreatAdapter(Context context,  ArrayList<QueryShopCartJson.ResultBean>  list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.create_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        if (list.size()>0){
            myHolder.itemImg.setImageURI(list.get(i).getPic());
            myHolder.itemName.setText(list.get(i).getCommodityName());
            myHolder.itemPrice.setText("￥："+list.get(i).getPrice());
            myHolder.itemCount.setText(list.get(i).getCount()+"");
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        SimpleDraweeView itemImg;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_price)
        TextView itemPrice;
        @BindView(R.id.item_count)
        Button itemCount;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
