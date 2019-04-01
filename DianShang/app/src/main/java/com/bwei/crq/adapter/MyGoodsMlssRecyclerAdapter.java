package com.bwei.crq.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.GoodsInfoJson;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;



/**
 * @Auther: cairuiqi
 * @Date: 2019/2/21 20:08:00
 * @Description:
 */
public class MyGoodsMlssRecyclerAdapter extends RecyclerView.Adapter<MyGoodsMlssRecyclerAdapter.MyViewhodler> {

    private Context context;

    List<GoodsInfoJson.ResultBean.MlssBean.CommodityListBeanXX> mlss;

    public MyGoodsMlssRecyclerAdapter(Context context , List<GoodsInfoJson.ResultBean.MlssBean.CommodityListBeanXX> mlss) {
        this.context = context;
        this.mlss = mlss;
    }

    @NonNull
    @Override
    public MyViewhodler onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2, null, false);
        final MyViewhodler myViewhodler = new MyViewhodler(view);
        //设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onGoodsMlssListener!=null){
                    int layoutPosition = myViewhodler.getLayoutPosition();
                    onGoodsMlssListener.onGoodsMlssClickItem(layoutPosition);
                }
            }
        });



        return myViewhodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhodler myViewhodler, int i) {
                //赋值
        myViewhodler.img.setImageURI(mlss.get(i).getMasterPic());
        myViewhodler.title.setText(mlss.get(i).getCommodityName());
        myViewhodler.price.setText("¥:"+mlss.get(i).getPrice()+"");

    }


    @Override
    public int getItemCount() {
        return mlss.size();
    }

    class MyViewhodler extends RecyclerView.ViewHolder {


        private final SimpleDraweeView img;
        private final TextView title;
        private final TextView price;

        public MyViewhodler(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.mlss_simple_View);
            title = itemView.findViewById(R.id.mlss_title);
            price = itemView.findViewById(R.id.mlss_price);


        }
    }

    //接口回调
    public interface  OnGoodsMlssListener{
        void onGoodsMlssClickItem(int i);
    }

    private  OnGoodsMlssListener onGoodsMlssListener;

    public void setOnGoodsMlssListener(OnGoodsMlssListener onGoodsMlssListener) {
        this.onGoodsMlssListener = onGoodsMlssListener;
    }
}
