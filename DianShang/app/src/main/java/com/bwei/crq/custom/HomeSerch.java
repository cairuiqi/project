package com.bwei.crq.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bwei.crq.R;

import butterknife.BindView;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 19:07:36
 * @Description:
 */
public class HomeSerch extends LinearLayout implements View.OnClickListener {


    private EditText et;

    public HomeSerch(Context context) {
        super(context);
    }

    public HomeSerch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.serch, this);
       Button sou=  findViewById(R.id.sou);
        et = findViewById(R.id.et);
        sou.setOnClickListener(this);
    }

    public HomeSerch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
             case R.id.sou:
                String sou = et.getText().toString();

                    OnGetSerchData.getSerchData(sou);


                break;

        }
    }



    //获得数据框中的数据
    public interface OnGetSerchDataListener{
        void getSerchData(String data);
    }


    private OnGetSerchDataListener OnGetSerchData;


    public void setOnGetSerchData(OnGetSerchDataListener onGetSerchData) {
        OnGetSerchData = onGetSerchData;
    }
}
