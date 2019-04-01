package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.custom.AdderSubtractor;
import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/26 09:45:30
 * @Description:
 */
public class MyShppingAdapter extends RecyclerView.Adapter<MyShppingAdapter.MyViewHoder> {

    private Context context;
    private List<QueryShopCartJson.ResultBean> result;

    public MyShppingAdapter(Context context, List<QueryShopCartJson.ResultBean> result) {
        this.context = context;
        this.result = result;
    }
    //商品总价
    private double numprice;



    //1全选反选计算总价方法
    public double qx(boolean b){
        numprice=0.0;
        for (QueryShopCartJson.ResultBean resultBean : result) {
            //设置给bean类存全选反选的状态
            resultBean.setCheck(b);
            if (resultBean.isCheck()) {
                numprice+=new BigDecimal(resultBean.getPrice()).doubleValue()*resultBean.getCount();
            }
        }
        //刷新适配器
        notifyDataSetChanged();
        return numprice;
    }



    //3条目点击控制qx状态的接口回调
    public QxOnclick qxOnclick;

    public void setQxOnclick(QxOnclick qxOnclick) {
        this.qxOnclick = qxOnclick;
    }

    public interface QxOnclick{
        void OnClick();
        // void delete(List<QueryShopCartJson.ResultBean> list);
    }


    //4子条目的状态在遍历 控制是否全选的状态
    //判断值
    public boolean checknotity(){
        boolean temp=true;
        for (QueryShopCartJson.ResultBean resultBean : result) {
            if (resultBean.isCheck()) {
                temp=true;
            }else{
                temp=false;
            }
        }
        return temp;
    }


    //5计算子条目的价格的方法

    public double getprice(){
        numprice=0.0;
        for (QueryShopCartJson.ResultBean resultBean : result) {
            if (resultBean.isCheck()) {
                numprice+=new BigDecimal(resultBean.getPrice()).doubleValue()*resultBean.getCount();
            }
        }
        notifyDataSetChanged();
        return numprice;
    }






    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.shopcart_parennt, null);
        MyViewHoder myViewHoder = new MyViewHoder(inflate);

        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder myViewHoder, final int i) {
            //赋值
        myViewHoder.img.setImageURI(result.get(i).getPic());
        myViewHoder.text.setText(result.get(i).getCommodityName());
        myViewHoder.price.setText("¥:"+result.get(i).getPrice()+"");
        //1.点击全选把qx()中刷新bean类中的全选反选状态设置给条目的状态
        myViewHoder.cb_prent.setChecked(result.get(i).isCheck());
        //2条目的点击状态存到集合中刷新
        myViewHoder.cb_prent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("check",myViewHoder.cb_prent.isChecked()+"");
                result.get(i).setCheck(myViewHoder.cb_prent.isChecked());
                notifyItemChanged(i);
                if (qxOnclick!=null){
                    qxOnclick.OnClick();
                }
            }
        });

        //得到改变的数量设置bena类
        myViewHoder.addersub.setOnGetNumsListenr(new AdderSubtractor.OnGetNumsListenr() {
            @Override
            public void getNums(int num) {
                  //设置bena类
                 result.get(i).setCount(num);
                if (qxOnclick!=null){
                    qxOnclick.OnClick();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private final CheckBox cb_prent;
        private final SimpleDraweeView img;
        private final TextView text;
        private final TextView price;
        private final AdderSubtractor addersub;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            cb_prent = itemView.findViewById(R.id.cb_prent);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.text);
            price = itemView.findViewById(R.id.price);
            addersub = itemView.findViewById(R.id.addersub);

        }
    }



}
