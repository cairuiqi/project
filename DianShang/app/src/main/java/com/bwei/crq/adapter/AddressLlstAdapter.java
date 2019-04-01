package com.bwei.crq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwei.crq.R;
import com.bwei.crq.bean.AddressList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/28 17:27:59
 * @Description:
 */
public class AddressLlstAdapter extends RecyclerView.Adapter<AddressLlstAdapter.MyHolder> {

    private Context context;
    private List<AddressList.ResultBean> list;

    public AddressLlstAdapter(Context context, List<AddressList.ResultBean> result) {
        this.context = context;
        this.list =result;
    }

    public void setList(List<AddressList.ResultBean> list){
        if (list!=null){
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.addresslist,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.realname.setText(list.get(i).getRealName());
        myHolder.address.setText(list.get(i).getAddress());
        myHolder.phone.setText(list.get(i).getPhone());
        int whetherDefault = list.get(i).getWhetherDefault();
        String s = whetherDefault + "";
        if (s.equals("1")){
            myHolder.btn.setChecked(true);
        }else{
            myHolder.btn.setChecked(false);
        }
        myHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItenClick!=null){
                    onItenClick.idOnclick(list.get(i).getId());
                }
            }
        });
        myHolder.updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItenClick!=null){
                    onItenClick.update(list.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.realname)
        TextView realname;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.btn)
        RadioButton btn;
        @BindView(R.id.updata)
        Button updata;
        @BindView(R.id.delete)
        Button delete;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public OnItenClick onItenClick;

    public void setOnItenClick(OnItenClick onItenClick) {
        this.onItenClick = onItenClick;
    }

    public interface OnItenClick{
        void idOnclick(int id);
        void update(AddressList.ResultBean resultBean);
    }

}
