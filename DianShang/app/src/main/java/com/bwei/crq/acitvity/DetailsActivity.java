package com.bwei.crq.acitvity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.crq.R;
import com.bwei.crq.acitvity.orderactivity.OrderActivity;
import com.bwei.crq.adapter.MyGoodsDetailsAdapter;
import com.bwei.crq.base.BaseActivity;
import com.bwei.crq.bean.AddMoreBean;
import com.bwei.crq.bean.GoodsDetailsJson;
import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.presenter.DetailsPresenter;
import com.bwei.crq.presenter.QueryShopCardPresenter;
import com.bwei.crq.presenter.ShppingAddPresenter;
import com.bwei.crq.view.DetailsView;
import com.bwei.crq.view.QueryShopCardView;
import com.bwei.crq.view.ShppingAddView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements DetailsView ,ShppingAddView,QueryShopCardView {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.goods_num)
    TextView goodsNum;
    @BindView(R.id.goods_name)
    TextView goodsName;
     @BindView(R.id.webView)
    WebView webView;
   @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.buy)
    ImageView buy;
    private ShppingAddPresenter shppingAddPresenter;
    private String userId;
    private String sessionId;
    private SharedPreferences cun;
    private int commodityId;
    private QueryShopCardPresenter queryShopCardPresenter;
    private int commodityId1;
    private DetailsPresenter detailsPresenter;
    private ArrayList<ImageView> imageViews;
    private String[] split;
    private int price;
    private int commentNum;
    private String commodityName;


    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        commodityId1 = intent.getExtras().getInt("commodityId");
        //详情mvp
        detailsPresenter = new DetailsPresenter(this);
        //详情的方法
        detailsPresenter.related(commodityId1);
        detailsPresenter.attachView(this);
        //得到uid, sid
        cun = getSharedPreferences("cun", Context.MODE_PRIVATE);
        queryShopCardPresenter = new QueryShopCardPresenter(this);


        //加入购物车
        shppingAddPresenter = new ShppingAddPresenter(this);
    }


    //详情的数据
    @Override
    public void onGoodsDetailsData(final GoodsDetailsJson.ResultBean result) {
        //获得图片字符串
        String picture = result.getPicture();
        //分割详情图片的字符串
        split = picture.split(",");
        //创建图片的集合
        imageViews = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            ImageView imageView = new ImageView(DetailsActivity.this);
            Glide.with(this).load(split[i]).into(imageView);
            imageViews.add(imageView);
        }
        //设置给适配器
        pager.setAdapter(new MyGoodsDetailsAdapter(imageViews));
        num.setText(1 + "/" + imageViews.size());


        //滑动页面
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                num.setText((i + 1) + "/" + imageViews.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        price = result.getPrice();
        commentNum = result.getCommentNum();
        commodityName = result.getCommodityName();
        goodsPrice.setText("¥" + price + "");
        goodsNum.setText("已售" + commentNum + "件");
        goodsName.setText(commodityName);


        //商品详情
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadData(result.getDetails(), "Text/html", "UTF-8");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());


        //加入购物车
        add.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                userId = cun.getString("userId", "");
                sessionId = cun.getString("sessionId", "");
                Log.d("sb_details",userId+"**"+sessionId);
                //先查询购物车
                if (!userId.equals("")){
                     queryShopCardPresenter.related(userId,sessionId);
                    Log.d("sb_shop",userId+"**"+sessionId);
                }else{

                    new AlertDialog.Builder(DetailsActivity.this).setTitle("需要登录")//设置对话框标题

                            .setMessage("得到登录的uid和sdi")//设置显示的内容

                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮


                                @Override

                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                    userId = cun.getString("userId", "");
                                    sessionId = cun.getString("sessionId", "");
                                    Log.d("sb_details",userId+"**"+sessionId);

                                    finish();

                                }

                            }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮



                        @Override

                        public void onClick(DialogInterface dialog, int which) {//响应事件


                            Log.i("alertdialog"," 请保存数据！");

                        }

                    }).show();//在按键响应事件中显示此对话框*/
                }




              }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, OrderActivity.class);
                //图片地址
                String img = split[0];
                intent.putExtra("goodsPrice",price+"");
                intent.putExtra("imageView",img);
                intent.putExtra("goodsName",commodityName+"");
                intent.putExtra("commodityId1",commodityId1+"");
                //跳转到待支付订单页面
                startActivity(intent);
            }
        });




        //详情
     /*   WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String s = "<script type=\"text/javascript\">" +
                "var imgs=document.getElementsByTagName('img');" +
                "for(var i = 0; i<imgs.length; i++){" +
                "imgs[i].style.width='100%';" +
                "imgs[i].style.height='auto';" +
                "}" +
                "</script>";
        String details = result.getDetails();
        webView.loadDataWithBaseURL(null, details + s + "<html><body>", "textml", "utf-8", null);*/


    }

    //添加购物车数据
    @Override
    public void onMessage(String message) {

            Toast.makeText(this, "同步成功", Toast.LENGTH_SHORT).show();
     }



    //查询购物车数据
    @Override
    public void onQueryShopData(List<QueryShopCartJson.ResultBean> result) {
        //获得查询的id和cout
        ArrayList<AddMoreBean> addMoreBeans = new ArrayList<>();
        for (int i=0;i<result.size();i++){
            //获得查询的id和count
            addMoreBeans.add(new AddMoreBean(result.get(i).getCommodityId(),1));
        }

        //详情的id和count
        addMoreBeans.add(new AddMoreBean(commodityId1,1));

        //添加购物车
        Gson gson = new Gson();
        String s = gson.toJson(addMoreBeans);
        shppingAddPresenter.related(s,userId,sessionId);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsPresenter.dettach();
     }
}
