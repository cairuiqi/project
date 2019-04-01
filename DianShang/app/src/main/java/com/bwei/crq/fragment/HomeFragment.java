package com.bwei.crq.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bwei.crq.R;
import com.bwei.crq.acitvity.DetailsActivity;
import com.bwei.crq.acitvity.ThreeListActivity;
import com.bwei.crq.adapter.ListOneAdapter;
import com.bwei.crq.adapter.ListTwoAdapter;
import com.bwei.crq.adapter.MyGoodsMlssRecyclerAdapter;
import com.bwei.crq.adapter.MyGoodsPzshRecyclerAdapter;
import com.bwei.crq.adapter.MyGoodsRxxpRecyclerAdapter;
import com.bwei.crq.adapter.MyGoodsSerchAdapter;
import com.bwei.crq.adapter.ThreeListAdapter;
import com.bwei.crq.api.Api;
import com.bwei.crq.base.BaseFragment;
import com.bwei.crq.bean.FirstList;
import com.bwei.crq.bean.GoodsBannerJson;
import com.bwei.crq.bean.GoodsInfoJson;
import com.bwei.crq.bean.GoodsSerchJson;
import com.bwei.crq.bean.ThreeList;
import com.bwei.crq.bean.TwoList;
import com.bwei.crq.custom.HomeSerch;
import com.bwei.crq.presenter.FirstListPresenter;
import com.bwei.crq.presenter.GoodsBannerPresenter;
import com.bwei.crq.presenter.GoodsInfoPresenter;
import com.bwei.crq.presenter.GoodsSerchPresenter;
import com.bwei.crq.presenter.ThreeListPresenter;
import com.bwei.crq.presenter.TwoListPresenter;
import com.bwei.crq.view.FirstListView;
import com.bwei.crq.view.GoodsBannerView;
import com.bwei.crq.view.GoodsInfoView;
import com.bwei.crq.view.GoodsSerchView;
import com.bwei.crq.view.ThreeListView;
import com.bwei.crq.view.TwoListView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/17 16:29:21
 * @Description:
 */
public class HomeFragment extends BaseFragment implements GoodsSerchView, GoodsInfoView, GoodsBannerView ,FirstListView ,TwoListView ,ThreeListView {
    @BindView(R.id.serch)
    HomeSerch serch;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.serch_error)
    LinearLayout serch_error;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.flyBanner)
    FlyBanner flyBanner;
    @BindView(R.id.rlv2)
    RecyclerView rlv2;
    @BindView(R.id.rlv3)
    RecyclerView rlv3;
    @BindView(R.id.rlv4)
    RecyclerView rlv4;
    @BindView(R.id.tag)
    LinearLayout tag;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private String keyword = "高跟鞋";
    private int page = 1;
    private int count = 5;
    private GoodsSerchPresenter goodsSerchPresenter;
    private GoodsInfoPresenter goodsInfoPresenter;
    private GoodsBannerPresenter goodsBannerPresenter;
    private FirstListPresenter firstListPresenter;
    private Handler handler=new Handler();
    private ArrayList<GoodsSerchJson.ResultBean> resultBeans;
    //订阅管理器
    private CompositeDisposable disposable = new CompositeDisposable();
    private RecyclerView towre;
    private TwoListPresenter twoListPresenter;
    private ThreeListPresenter threeListPresenter;


    @Override
    protected int layoutResID() {
        return R.layout.homefragment;
    }

    //初始化布局
    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        //设置布局
        rlv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rlv2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rlv3.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv4.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //mvp
        goodsSerchPresenter = new GoodsSerchPresenter(this);
        goodsInfoPresenter = new GoodsInfoPresenter(this);
        goodsBannerPresenter = new GoodsBannerPresenter(this);

        goodsSerchPresenter.attachView(this);
        goodsInfoPresenter.attachView(this);
        goodsBannerPresenter.attachView(this);
        twoListPresenter = new TwoListPresenter(this);
        firstListPresenter = new FirstListPresenter(this);
        threeListPresenter = new ThreeListPresenter(this);



        //走banner的mvp
        goodsBannerPresenter.related();
        //走首页商品的mvp
        goodsInfoPresenter.attachView(this);
        goodsInfoPresenter.related();

        //点击搜索
        serch.setOnGetSerchData(new HomeSerch.OnGetSerchDataListener() {
            @Override
            public void getSerchData(final String data) {
                if (data.equals("")) {
                    //加载失败的布局
                    serch_error.setVisibility(View.VISIBLE);

                    return;
                } else {
                    //显示搜索的布局
                    swipeRefresh.setVisibility(View.VISIBLE);
                    tag.setVisibility(View.GONE);

                    //下拉刷新
                    //刷新
                    swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
                    //下拉刷新
                    swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            //有网

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    count = 5;
                                    goodsSerchPresenter.related(data,page,count);
                                    //停止刷新
                                    swipeRefresh.setRefreshing(false);
                                }
                            }, 3000);

                        }

                    });
                    //上拉加载
                  /*  rlv.addOnScrollListener(new onLoadMoreListener() {
                        @Override
                        protected void onLoading(int countItem, int lastItem) {
                            //有网

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        count++;
                                        goodsSerchPresenter.related(data,page,count);
                                        //停止刷新
                                        swipeRefresh.setRefreshing(false);
                                    }
                                }, 3000);


                        }
                    });*/



                    //走mvp
                    goodsSerchPresenter.related(data, page, count);

                    return;
                }


            }
        });

        //点击展示一级分类
        serch.findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                    //mvp one

                firstListPresenter.related(disposable);
                firstListPresenter.attachView(this);
            }
        });


    }

    @Override
    protected void loadData() {
        Log.d("frag","HomeFragment");

    }


    //搜索的接口数据
    @Override
    public void onGoodsSerchResult(final List<GoodsSerchJson.ResultBean> result) {

        //判断是否是第一页
        if(count==5){
            //创建新集合
            resultBeans = new ArrayList<>();
        }

        //加载旧集合
        resultBeans.addAll(result);


        //设置适配器
        MyGoodsSerchAdapter myGoodsSerchAdapter = new MyGoodsSerchAdapter(getActivity(), resultBeans);
        rlv.setAdapter(myGoodsSerchAdapter);
        //点击跳转到详情
        myGoodsSerchAdapter.setOnRecycleviewListener(new MyGoodsSerchAdapter.OnRecycleviewListener() {
            @Override
            public void onItemClick(int i) {
                //点击跳转详情
                int commodityId = result.get(i).getCommodityId();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);
                startActivity(intent);
            }
        });

    }

    //轮播的数据
    @Override
    public void onGoodsBannerData(List<GoodsBannerJson.ResultBean> result) {


        //创建集合
        ArrayList<String> banners = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            banners.add(result.get(i).getImageUrl());
        }

        flyBanner.setImagesUrl(banners);

    }


    //获得首页商品信息的数据
    @Override
    public void onGoodsInfoData(GoodsInfoJson goodsInfoJson) {

        final GoodsInfoJson.ResultBean result = goodsInfoJson.getResult();
        List<GoodsInfoJson.ResultBean.RxxpBean.CommodityListBean> rxxp = result.getRxxp().getCommodityList();
        MyGoodsRxxpRecyclerAdapter myGoodsRxxpRecyclerAdapter = new MyGoodsRxxpRecyclerAdapter(getActivity(), rxxp);
        rlv2.setAdapter(myGoodsRxxpRecyclerAdapter);
        //单击事件跳转
        myGoodsRxxpRecyclerAdapter.setOnGoodsRxxpListener(new MyGoodsRxxpRecyclerAdapter.OnGoodsRxxpListener() {
            @Override
            public void onGoodsRxxpClickItem(int i) {
                int commodityId = result.getRxxp().getCommodityList().get(i).getCommodityId();
                //点击跳转详情
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);

                startActivity(intent);


            }
        });

        List<GoodsInfoJson.ResultBean.MlssBean.CommodityListBeanXX> mlss = result.getMlss().getCommodityList();
        MyGoodsMlssRecyclerAdapter myGoodsMlssRecyclerAdapter = new MyGoodsMlssRecyclerAdapter(getActivity(), mlss);
        rlv3.setAdapter(myGoodsMlssRecyclerAdapter);
        //单击事件跳转
        myGoodsMlssRecyclerAdapter.setOnGoodsMlssListener(new MyGoodsMlssRecyclerAdapter.OnGoodsMlssListener() {
            @Override
            public void onGoodsMlssClickItem(int i) {
                int commodityId = result.getMlss().getCommodityList().get(i).getCommodityId();
                //点击跳转
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);

                startActivity(intent);
            }
        });

        List<GoodsInfoJson.ResultBean.PzshBean.CommodityListBeanX> pzsh = result.getPzsh().getCommodityList();
        MyGoodsPzshRecyclerAdapter myGoodsPzshRecyclerAdapter = new MyGoodsPzshRecyclerAdapter(getActivity(), pzsh);
        rlv4.setAdapter(myGoodsPzshRecyclerAdapter);
        myGoodsPzshRecyclerAdapter.setOnGoodsPzshListener(new MyGoodsPzshRecyclerAdapter.OnGoodsPzshListener() {
            @Override
            public void onGoodsPzshClickItem(int i) {
                int commodityId = result.getPzsh().getCommodityList().get(i).getCommodityId();
                //点击跳转详情
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);

                startActivity(intent);
            }
        });
    }


     //一级列表的数据
    @Override
    public void getFirstList(List<FirstList.ResultBean> result) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_onelist, null, false);
        //RecyclerView列表控件
        RecyclerView ontList = view.findViewById(R.id.one_list);
        towre = view.findViewById(R.id.two_list);
        //设置管理器
        ontList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        ListOneAdapter listOneAdapter = new ListOneAdapter(getActivity(), result);
        ontList.setAdapter(listOneAdapter);

        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_background));
        popupWindow.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        //这是背景色
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //显示
        popupWindow.showAsDropDown(serch);

        listOneAdapter.setOnOneClick(new ListOneAdapter.OnOneClick() {
            @Override
            public void setIdData(String id) {
                Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
                //请求二级列表

                twoListPresenter.related(disposable,id);

            }
        });

        for (int i=0;i<result.size();i++){
            twoListPresenter.related(disposable,result.get(i).getId());
        }

    }


    //二级列表的数据
    @Override
    public void getTwoLit(List<TwoList.ResultBean> result) {
        //设置管理器
        towre.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        ListTwoAdapter listTwoAdapter = new ListTwoAdapter(getActivity(), result);
        towre.setAdapter(listTwoAdapter);
        listTwoAdapter.setOnTwoClick(new ListTwoAdapter.OnTwoClick() {
            @Override
            public void setIdData(String id) {
               /* Intent intent = new Intent(getActivity(),ThreeListActivity.class);
                Toast.makeText(getActivity(), "id:"+id, Toast.LENGTH_SHORT).show();
                intent.putExtra("id",id);
                startActivity(intent);*/
                threeListPresenter.related(disposable,id,page,count);


            }
        });


    }



    //三级展示的页面
    @Override
    public void getThreeList(final List<ThreeList.ResultBean> result) {
        //显示搜索的布局
        swipeRefresh.setVisibility(View.VISIBLE);
        tag.setVisibility(View.GONE);

        //下拉刷新
        //刷新
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //停止刷新
                        swipeRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        //下拉刷新
         //设置适配器
        ThreeListAdapter threeListAdapter = new ThreeListAdapter(getActivity(),result);
        rlv.setAdapter(threeListAdapter);
        //设置适配器
   //    MyGoodsSerchAdapter myGoodsSerchAdapter = new MyGoodsSerchAdapter(getActivity(), resultBeans);
    //    rlv.setAdapter(myGoodsSerchAdapter);
        //点击跳转到详情
        threeListAdapter.setOnRecycleviewListener(new ThreeListAdapter.OnRecycleviewListener() {
            @Override
            public void onItemClick(int i) {
                //点击跳转详情
                int commodityId = result.get(i).getCommodityId();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityId);
                startActivity(intent);
            }
        });

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        goodsSerchPresenter.dettach();
        goodsInfoPresenter.detchView();
        goodsBannerPresenter.dettach();
        firstListPresenter.dettach();
        twoListPresenter.dettach();

        boolean disposed = disposable.isDisposed();
        if (!disposed) {
            //消除订阅
            disposable.clear();
            //解除订阅
            disposable.dispose();
            Log.d("取消订阅","取消了");
        }

    }


}
