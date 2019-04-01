package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.GoodsSerchJson;
import com.bwei.crq.bean.ThreeList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 21:01:36
 * @Description:
 */
public class ThreeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ThreeList.ResultBean> list;

/*

    private final static int TYPE_CONTENT=0;//正常内容
    private final static int TYPE_FOOTER=1;//上拉加载
*/


    public ThreeListAdapter(Context context,  List<ThreeList.ResultBean> list) {
        this.context=context;
        this.list=list;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // 两种类型控制不同的条目样式

            final View view = View.inflate(context, R.layout.serch_item, null);
            final MyViewhodler myViewhodler = new MyViewhodler(view);
            //设置点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onRecycleviewListener!=null){
                        int layoutPosition = myViewhodler.getLayoutPosition();
                        onRecycleviewListener.onItemClick(layoutPosition);
                    }
                }
            });

            return myViewhodler;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);

            MyViewhodler myViewhodler= (MyViewhodler) viewHolder;
            String name = list.get(i).getCommodityName();
            int price = list.get(i).getPrice();
            //数据
            myViewhodler.img.setImageURI(list.get(i).getMasterPic());
            myViewhodler.title.setText(name);
            myViewhodler.price.setText(price+"");


    }



    @Override
    public int getItemCount() {
        //底部的条目
        return list.size();
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

   /* //多条目
    @Override
    public int getItemViewType(int position) {
        if (position==list.size()){
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }*/

    /**
     * 底部FooterViewHolder
     */
  /*  class MyFooterViewHolder extends RecyclerView.ViewHolder{

        private final ProgressBar pb;

        public MyFooterViewHolder(@NonNull View itemView) {
            super(itemView);
            pb = itemView.findViewById(R.id.pb);
        }

    }

*/
    //接口回调
    public interface  OnRecycleviewListener{
         void onItemClick(int i);
    }

    private OnRecycleviewListener onRecycleviewListener;

    public void setOnRecycleviewListener(OnRecycleviewListener onRecycleviewListener) {
        this.onRecycleviewListener = onRecycleviewListener;
    }

    //条目删除
    public void onItemRemoveData(int i){
        list.remove(i);
        notifyDataSetChanged();
    };
}
