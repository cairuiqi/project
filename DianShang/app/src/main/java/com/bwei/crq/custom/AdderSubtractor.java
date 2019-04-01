package com.bwei.crq.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.crq.R;

import butterknife.BindView;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/24 14:59:35
 * @Description:
 */
public class AdderSubtractor extends LinearLayout implements View.OnClickListener {


    private Button jia;
    private TextView num;
    private Button jian;
    int count=1;

    public AdderSubtractor(Context context) {
        super(context);
    }

    public AdderSubtractor(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.adder_subtractor, this, true);
        jia = findViewById(R.id.jia);
        num = findViewById(R.id.num);
        jian = findViewById(R.id.jian);
        //注册
        jia.setOnClickListener(this);
        num.setOnClickListener(this);
        //默认输入框设置1
        num.setText(1+"");
        jian.setOnClickListener(this);
    }

    public AdderSubtractor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jia:
                //获得数据框的值
                String trim = num.getText().toString().trim();
                Integer integer = Integer.valueOf(trim);
                count=integer+1;
                //加1设置数据框的值
                num.setText(count+"");
                //得到数量给接口回调
                if(onGetNumsListenr!=null){
                    //得到改变的商品数量
                    onGetNumsListenr.getNums(count);
                }


                break;
            case R.id.num:

                break;
            case R.id.jian:
                /* if (count>1){
                     count--;
                     String s1 = num.getText().toString();
                     Integer integer1 = Integer.valueOf(s1);
                     integer1=count;
                     num.setText(integer1+"");
                 }*/


                //获得数据框的值
                String trim1 = num.getText().toString().trim();
                Integer integer1 = Integer.valueOf(trim1);

                if(count>1){
                    //点击减号把数值设置给中间
                    count=integer1-1;
                    num.setText(count+"");
                    //得到数量给接口回调
                    if(onGetNumsListenr!=null){
                        //得到改变的商品数量
                        onGetNumsListenr.getNums(count);
                    }

                    return;
                }else{
                    Toast.makeText(getContext(), "不能再减了", Toast.LENGTH_SHORT).show();


                }



                break;

        }

    }


    //设置数量
    public void setCount(int count) {
        num.setText(count+"");
    }

    //定义接口回调
    public interface  OnGetNumsListenr{
        void getNums(int num);
    }

    private OnGetNumsListenr onGetNumsListenr;

    public void setOnGetNumsListenr(OnGetNumsListenr onGetNumsListenr) {
        this.onGetNumsListenr = onGetNumsListenr;
    }



    //改变商品数量的方法的监听
    /*public interface OnChangeNumListener{
        int getNum(int num);
    }*/

}
