package com.bwei.crq.api;

import com.bwei.crq.bean.AddressList;
import com.bwei.crq.bean.ByStatusBean;
import com.bwei.crq.bean.CirclelistJosn;
import com.bwei.crq.bean.CreateOrder;
import com.bwei.crq.bean.DeleteOrder;
import com.bwei.crq.bean.FirstList;
import com.bwei.crq.bean.GoodsBannerJson;
import com.bwei.crq.bean.GoodsDetailsJson;
import com.bwei.crq.bean.GoodsInfoJson;
import com.bwei.crq.bean.GoodsSerchJson;
import com.bwei.crq.bean.LoginJson;
import com.bwei.crq.bean.MyWallet;
import com.bwei.crq.bean.PayOrder;
import com.bwei.crq.bean.QueryShopCartJson;
import com.bwei.crq.bean.RegJson;
import com.bwei.crq.bean.ShoppingAddCarJson;
import com.bwei.crq.bean.ShoppingCarBean;
import com.bwei.crq.bean.ThreeList;
import com.bwei.crq.bean.TwoList;

import java.util.HashMap;
import java.util.Map;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Auther: cairuiqi
 * @Date: 2019/3/19 20:25:21
 * @Description:
 */
public interface ApiService {


    //http://172.17.8.100/
/*    @GET("small/order/verify/v1/findOrderListByStatus")
    Flowable<ByStatusBean> getOrder(@Header("userId")String userId, @Header("sessionId")String sessionId, @Body(""));*/

  //删除订单
    @HTTP(method = "DELETE",hasBody = true)
    @FormUrlEncoded()
    Flowable<DeleteOrder> deleteOrder(@Url String uri,@Header("userId")String userId,@Header("sessionId")String sessionId,@Field("orderId")String orderId);



    //查询订单
    @GET("small/order/verify/v1/findOrderListByStatus")
    Flowable<ByStatusBean> bystatus(@Header("userId")String userId,@Header("sessionId")String sessionId, @QueryMap HashMap<String,String> map);

    //一级列表
    @GET("small/commodity/v1/findFirstCategory")
    Flowable<FirstList>  oneList();

    //二级列表
    ///commodity/v1/findFirstCategory?firstCategoryId=1001003
    @GET("small/commodity/v1/findSecondCategory")
    Flowable<TwoList>  twoList(@Query("firstCategoryId")String firstCategoryId);

    //三级分类  categoryId=1001003002&page=1&count=5
    @GET("small/commodity/v1/findCommodityByCategory")
    Flowable<ThreeList> threeList(@Query("categoryId")String categoryId, @Query("page")int page, @Query("count")int count);


    //我的钱包
    @GET("small/user/verify/v1/findUserWallet")
    Flowable<MyWallet> myWallet(@Header("userId")String userId,@Header("sessionId")String sessionId,@Query("page")int page,@Query("count")int count);


    //支付
    //http://172.17.8.100/
    @POST("small/order/verify/v1/pay")
    @FormUrlEncoded
    Flowable<PayOrder>  payOrder(@Header("userId")String userId,@Header("sessionId")String sessionId,@Field("orderId")String orderId,@Field("payType")int payType);
   //创建订单

    /* @POST("small/order/verify/v1/createOrder")
        @FormUrlEncoded
        Flowable<CreateOrder> creteOrder(@Field("orderInfo")String orderInfo, @Field("orderInfo")double totalPrice, @Field("totalPrice")int addressId);*/


    @FormUrlEncoded
    @POST("small/order/verify/v1/createOrder")
    Flowable<CreateOrder> createOrder(@Header("userId") String userId, @Header("sessionId") String sessionId, @Field("orderInfo") String orderInfo, @Field("totalPrice") double totalPrice, @Field("addressId") int addressId);

    //圈子列表small/circle/v1/findCircleList?page=1&count=5
    @GET("small/circle/v1/findCircleList")
    Call<CirclelistJosn> circle(@Query("page") int page, @Query("count") int count);


    //收货地址列表   有问题数据为空   用ok解析了
    @GET("small/order/verify/v1/findShoppingCart")
    Call<AddressList> addressList();

    //新增收货地址
    @POST("small/user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Call<ShoppingCarBean> addaddress(@FieldMap HashMap<String, String> map);


    //查询购物车
    //small/order/verify/v1/findShoppingCart
    @GET("small/order/verify/v1/findShoppingCart")
    Call<QueryShopCartJson> queryShopCart();


    //加入购物车
    @PUT("small/order/verify/v1/syncShoppingCart")
    @FormUrlEncoded
    Call<ShoppingAddCarJson> shoppingcar(@Field("data") String s);


    //商品搜索v1/findCommodityByKeyword?keyword=%E9%AB%98%E8%B7%9F%E9%9E%8B&page=1&count=5
    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<GoodsSerchJson> getGoodsSerchData(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);


    //轮播
    @GET("small/commodity/v1/bannerShow")
    Call<GoodsBannerJson> getBannerData();


    //登录post请求  user/v1/login  @Field("phone")String phone, @Field("pwd")String pwd
    @FormUrlEncoded
    @POST("small/user/v1/login")
    Call<LoginJson> getLoginData(@FieldMap Map<String, String> map);


    //注册
    @FormUrlEncoded
    @POST("small/user/v1/register")
    Call<RegJson> getRegData(@FieldMap Map<String, String> map);


    //商品详情 v1/findCommodityDetailsById?commodityId=20
    @GET("small/commodity/v1/findCommodityDetailsById")
    Call<GoodsDetailsJson> getGoodsDetailsData(@Query("commodityId") int commodityId);

    //商品首页信息
    @GET("small/commodity/v1/commodityList")
    Call<GoodsInfoJson> getGoodsInfoData();


}
