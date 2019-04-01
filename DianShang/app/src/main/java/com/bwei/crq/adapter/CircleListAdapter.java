package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bwei.crq.R;
import com.bwei.crq.bean.CirclelistJosn;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 21:40:59
 * @Description:
 */
public class CircleListAdapter extends RecyclerView.Adapter<CircleListAdapter.MyHolder> {

    private Context context;
    private List<CirclelistJosn.ResultBean> list;
    public CircleListAdapter(Context context,List<CirclelistJosn.ResultBean> list) {
        this.context = context;
        this.list=list;
    }
    //给数据赋值的方法
    public void setList(List<CirclelistJosn.ResultBean> list){
        if (list!=null){
            this.list=list;
        }
        notifyDataSetChanged();
    }
    //给数据赋值的方法
    public void addList(List<CirclelistJosn.ResultBean> list){
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circlelist_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop());
        Glide.with(context).load(list.get(i).getHeadPic()).apply(requestOptions).into(myHolder.headpic);
        myHolder.nickname.setText(list.get(i).getNickName());
        myHolder.content.setText(list.get(i).getContent());
        String[] split = list.get(i).getImage().split(",");
        myHolder.image_rv.setAdapter(new CircleImageAdapter(context,split));
        int whetherGreat = list.get(i).getWhetherGreat();
        String s = whetherGreat + "";
        if (s.equals("1")){
            myHolder.whetherGreat.setChecked(true);
        }else{
            myHolder.whetherGreat.setChecked(false);
        }
        myHolder.whetherGreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hpp",list.get(i).getId()+"");
                if (myHolder.whetherGreat.isChecked()){
                    if (itemOnclck!=null){
                        itemOnclck.GreatOnclick(true,list.get(i).getId());
                    }
                }else{
                    if (itemOnclck!=null){
                        itemOnclck.GreatOnclick(false,list.get(i).getId());
                    }
                }
            }
        });
        myHolder.greatnum.setText(list.get(i).getGreatNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.headPic)
        ImageView headpic;
        @BindView(R.id.nickName)
        TextView nickname;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.image_rv)
        RecyclerView image_rv;
        @BindView(R.id.whetherGreat)
        CheckBox whetherGreat;
        @BindView(R.id.greatNum)
        TextView greatnum;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            image_rv.setLayoutManager(new GridLayoutManager(context,3));
        }
    }
    public ItemOnclck itemOnclck;

    public void setItemOnclck(ItemOnclck itemOnclck) {
        this.itemOnclck = itemOnclck;
    }

    public interface ItemOnclck{
        void GreatOnclick(boolean temp,int id);
    }

}
