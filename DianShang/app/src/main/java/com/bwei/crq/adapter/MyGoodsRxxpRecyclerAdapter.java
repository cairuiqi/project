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
public class MyGoodsRxxpRecyclerAdapter extends RecyclerView.Adapter<MyGoodsRxxpRecyclerAdapter.MyViewhodler> {

    private Context context;

    List<GoodsInfoJson.ResultBean.RxxpBean.CommodityListBean> rxxp;

    public MyGoodsRxxpRecyclerAdapter(Context context , List<GoodsInfoJson.ResultBean.RxxpBean.CommodityListBean> rxxp) {
        this.context = context;

        this.rxxp = rxxp;
    }

    @NonNull
    @Override
    public MyViewhodler onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null, false);
        final MyViewhodler myViewhodler = new MyViewhodler(view);
        //设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onGoodsRxxpListener!=null){
                    int layoutPosition = myViewhodler.getLayoutPosition();
                    onGoodsRxxpListener.onGoodsRxxpClickItem(layoutPosition);
                }
            }
        });



        return myViewhodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhodler myViewhodler, int i) {
                //赋值
        myViewhodler.img.setImageURI(rxxp.get(i).getMasterPic());
        myViewhodler.title.setText(rxxp.get(i).getCommodityName());
        myViewhodler.price.setText("¥:"+rxxp.get(i).getPrice()+"");

    }


    @Override
    public int getItemCount() {
        return rxxp.size();
    }

    class MyViewhodler extends RecyclerView.ViewHolder {


        private final SimpleDraweeView img;
        private final TextView title;
        private final TextView price;

        public MyViewhodler(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.rxxp_simple_View);
            title = itemView.findViewById(R.id.rxxp_title);
            price = itemView.findViewById(R.id.rxxp_price);


        }
    }

    //接口回调
    public interface  OnGoodsRxxpListener{
         void onGoodsRxxpClickItem(int i);
    }

    private OnGoodsRxxpListener onGoodsRxxpListener;

    public void setOnGoodsRxxpListener(OnGoodsRxxpListener onGoodsRxxpListener) {
        this.onGoodsRxxpListener = onGoodsRxxpListener;
    }


}
