package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwei.crq.R;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 21:53:33
 * @Description:
 */
public class CircleImageAdapter extends RecyclerView.Adapter<CircleImageAdapter.MyHolder> {


    private Context context;
    private String[] image;
    public CircleImageAdapter(Context context, String[] image) {
        this.context = context;
        this.image = image;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circle_image,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Glide.with(context).load(image[i]).into(myHolder.imageview);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imageview;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageview);
        }
    }
}
