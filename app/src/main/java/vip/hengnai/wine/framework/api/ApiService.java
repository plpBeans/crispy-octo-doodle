package vip.hengnai.wine.framework.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * AUTHOR：dell
 * TIME:2019/7/24 2019/7/24
 * DESCRIPTION:ApiService
 * @author hua
 */
public interface ApiService {
    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("login/mobile/code")
    Observable<Response<String>> getMobileCode(@Field("mobile") String mobile);

    /**
     * 短信验证码登录
     * @param mobile
     * @param key
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("login/mobile")
    Observable<Response<String>> login(@Field("mobile") String mobile,@Field("key") String key,
                                       @Field("code") String code);

    /**
     * 登出
     * @param accessToken
     * @return
     */
    @GET("loginout")
    Observable<Response<String>> loginout(@Query("accessToken") String accessToken);

    /**
     * 收货地址列表
     * @return
     */
    @GET("addresses")
    Observable<Response<String>> getPersonAddressList();

    /**
     * 新增个人地址
     * @param requestBody
     * @return
     */
    @POST("addresses")
    Observable<Response<String>> addPersonAddress(@Body RequestBody requestBody);

    /**
     * 修改个人地址
     * @param addressId
     * @param requestBody
     * @return
     */
    @POST("addresses/{addressId}")
    Observable<Response<String>> modifyPersonAddress(@Path("addressId") Integer addressId, @Body RequestBody requestBody);

    /**
     * 删除个人地址
     * @param addressId
     * @return
     */
    @DELETE("addresses/{addressId}")
    Observable<Response<String>> deletePersonAddress(@Path("addressId") Integer addressId);

    /**
     * 默认菜单
     * @return
     */
    @GET("menus/")
    Observable<Response<String>> getDefaultMenus();

    /**
     * 获取自提门店菜单
     * @param shopId
     * @return
     */
    @GET("menus/self")
    Observable<Response<String>> getShopMenus(@Query("shopId") Integer shopId);
    /**
     * 获取外送菜单
     * @param addressId
     * @return
     */
    @GET("menus/takeaway")
    Observable<Response<String>> getTakeAwayMenus(@Query("addressId") Integer addressId);
    /**
     *获取商品详细信息
     * @param goodsId
     * @return
     */
    @GET("goods/{goodsId}")
    Observable<Response<String>> getGoods(@Path("goodsId") Integer goodsId );
    /**
     * 自提门店列表
     * @param q
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("shops/self")
    Observable<Response<String>> getShopList(@Query("q") String q,@Query("pageIndex") String pageIndex,@Query("pageSize") String pageSize);

    /**
     *获取店铺信息
     * @param shopId
     * @return
     */
    @GET("shops/{shopId}")
    Observable<Response<String>> getShop(@Path("shopId") Integer shopId );

    /**
     * 获取购物车信息
     * @return
     */
    @GET("cart")
    Observable<Response<String>> getCart();

    /**
     * 购物车分组商品变更
     * @param groupId
     * @param requestBody
     * @return
     */
    @POST("cart/{groupId}")
    Observable<Response<String>> changeCart(@Path("groupId") Integer groupId,@Body RequestBody requestBody);
    /**
     * 添加商品到购物车
     * @param requestBody
     * @return
     */
    @POST("cart")
    Observable<Response<String>> addCart(@Body RequestBody requestBody);

    /**
     * 商品生成订单
     * @param requestBody
     * @return
     */
    @POST("orders/goods")
    Observable<Response<String>> goodsForOrder(@Body RequestBody requestBody);
    /**
     * 购物车生成订单
     * @param requestBody
     * @return
     */
    @POST("orders/cart")
    Observable<Response<String>> cartForOrder(@Body RequestBody requestBody);

    /**
     * 变更订单内容
     * @param orderId
     * @param requestBody
     * @return
     */
    @POST("orders/{orderId}/change")
    Observable<Response<String>> changeOrder(@Path("orderId") Integer orderId ,
                                              @Body RequestBody requestBody);

    /**
     * 订单模块获取可用优惠券集合
     * @param orderId
     * @return
     */
    @GET("orders/{orderId}/coupons")
    Observable<Response<String>> getCouponsForOrder(@Path("orderId") Integer orderId );
    /**
     * 订单模块获取可预约时间信息
     * @param orderId
     * @return
     */
    @GET("orders/{orderId}/booking")
    Observable<Response<String>> getTimeForOrder(@Path("orderId") Integer orderId );
    /**
     * 获取订单列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("orders")
    Observable<Response<String>> getOrderList(@Query("pageIndex") Integer pageIndex,@Query("pageSize") Integer pageSize);

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    @GET("orders/{orderId}")
    Observable<Response<String>> getOrderDetail(@Path("orderId") Integer orderId );
}
