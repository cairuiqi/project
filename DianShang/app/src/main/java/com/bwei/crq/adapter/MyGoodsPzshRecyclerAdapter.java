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
public class MyGoodsPzshRecyclerAdapter extends RecyclerView.Adapter<MyGoodsPzshRecyclerAdapter.MyViewhodler> {

    private Context context;

    List<GoodsInfoJson.ResultBean.PzshBean.CommodityListBeanX> pzsh;

    public MyGoodsPzshRecyclerAdapter(Context context , List<GoodsInfoJson.ResultBean.PzshBean.CommodityListBeanX>  pzsh) {
        this.context = context;

        this.pzsh = pzsh;
    }

    @NonNull
    @Override
    public MyViewhodler onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item3, null, false);
        final MyViewhodler myViewhodler = new MyViewhodler(view);
        //设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onGoodsPzshListener!=null){
                    int layoutPosition = myViewhodler.getLayoutPosition();
                    onGoodsPzshListener.onGoodsPzshClickItem(layoutPosition);
                }
            }
        });



        return myViewhodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhodler myViewhodler, int i) {
                //赋值
       // Glide.with(context).load(pzsh.get(i).getMasterPic()).into(myViewhodler.img);
        myViewhodler.img.setImageURI(pzsh.get(i).getMasterPic());
        myViewhodler.title.setText(pzsh.get(i).getCommodityName());
        myViewhodler.price.setText("¥:"+pzsh.get(i).getPrice()+"");

    }


    @Override
    public int getItemCount() {
        return pzsh.size();
    }

    class MyViewhodler extends RecyclerView.ViewHolder {


        private final SimpleDraweeView img;
        private final TextView title;
        private final TextView price;

        public MyViewhodler(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);


        }
    }


    //接口回调
    public interface  OnGoodsPzshListener{
        void onGoodsPzshClickItem(int i);
    }

    private OnGoodsPzshListener onGoodsPzshListener;

    public void setOnGoodsPzshListener(OnGoodsPzshListener onGoodsPzshListener) {
        this.onGoodsPzshListener = onGoodsPzshListener;
    }
}
