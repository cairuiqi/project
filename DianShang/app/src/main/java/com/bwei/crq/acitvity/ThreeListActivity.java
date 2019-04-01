package com.bwei.crq.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.crq.R;
import com.bwei.crq.adapter.MyGoodsSerchAdapter;
import com.bwei.crq.adapter.ThreeListAdapter;
import com.bwei.crq.bean.ThreeList;
import com.bwei.crq.custom.HomeSerch;
import com.bwei.crq.presenter.ThreeListPresenter;
import com.bwei.crq.view.ThreeListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class ThreeListActivity extends AppCompatActivity  implements ThreeListView {

    @BindView(R.id.serch)
    HomeSerch serch;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.serch_error)
    LinearLayout serchError;
    private int page = 1;
    private int count = 5;
    //订阅管理器
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    private ThreeListPresenter threeListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_list);
        ButterKnife.bind(this);
        rlv.setLayoutManager(new GridLayoutManager(this,2));
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        //mvp
        threeListPresenter = new ThreeListPresenter(this);
        threeListPresenter.related(compositeDisposable,id,page,count);
        threeListPresenter.attachView(this);


    }


    //得到三级的数据展示
    @Override
    public void getThreeList(List<ThreeList.ResultBean> result) {
        ThreeListAdapter threeListAdapter = new ThreeListAdapter(this,result);
        rlv.setAdapter(threeListAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        threeListPresenter.dettach();
        //解除订阅
        boolean disposed = compositeDisposable.isDisposed();
        if(!disposed){
            //消除订阅
            compositeDisposable.clear();
            //解除订阅
            compositeDisposable.dispose();
        }
    }
}
