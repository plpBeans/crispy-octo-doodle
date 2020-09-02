package vip.hengnai.wine.framework.model;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import vip.hengnai.wine.entity.OrderConfirmEntity;
import vip.hengnai.wine.entity.OrderDetailEntity;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.entity.OrderTimeEntity;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.OrderConfirmResponse;
import vip.hengnai.wine.framework.response.OrderDetailResponse;
import vip.hengnai.wine.framework.response.OrderListResponse;
import vip.hengnai.wine.framework.response.OrderTimeResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

/**
 * on 2020/1/22.
 *
 * @author hua.
 */

public class OrderModel {
    /**
     * 获取订单列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Observable<List<OrderEntity>> getOrderList(String pageIndex, String pageSize){
        return RetrofitManager.getInstance().getOrderList(Integer.valueOf(pageIndex), Integer.valueOf(pageSize))
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderListResponse, List<OrderEntity>>() {
                    @Override
                    public List<OrderEntity> convert(OrderListResponse value) {
                        List<OrderEntity> response=value.getData();
                        return null== response ? Collections.<OrderEntity>emptyList() : response;
                    }
                },OrderListResponse.class));
    }

    /**
     * 商品生成订单
     * @param requestBody
     * @return
     */
    public Observable<OrderConfirmEntity> goodsForOrder(RequestBody requestBody){
        return RetrofitManager.getInstance().goodsForOrder(requestBody)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderConfirmResponse,OrderConfirmEntity>() {
                    @Override
                    public OrderConfirmEntity convert(OrderConfirmResponse value) {

                        return value.getData();
                    }
                },OrderConfirmResponse.class));
    }
    /**
     * 购物车生成订单
     * @param requestBody
     * @return
     */
    public Observable<OrderConfirmEntity> cartForOrder(RequestBody requestBody){
        return RetrofitManager.getInstance().cartForOrder(requestBody)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderConfirmResponse,OrderConfirmEntity>() {
                    @Override
                    public OrderConfirmEntity convert(OrderConfirmResponse value) {

                        return value.getData();
                    }
                },OrderConfirmResponse.class));
    }

    /**
     * 变更订单内容
     * @param orderId
     * @param requestBody
     * @return
     */
    public Observable<OrderConfirmEntity> changeOrder(int orderId, RequestBody requestBody){
        return RetrofitManager.getInstance().changeOrder(orderId,requestBody)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderConfirmResponse,OrderConfirmEntity>() {
                    @Override
                    public OrderConfirmEntity convert(OrderConfirmResponse value) {

                        return value.getData();
                    }
                },OrderConfirmResponse.class));
    }

    /**
     * 订单模块获取可预约时间信息
     * @param orderId
     * @return
     */
    public Observable<OrderTimeEntity> getTimeForOrder(int orderId){
        return RetrofitManager.getInstance().getTimeForOrder(orderId)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderTimeResponse,OrderTimeEntity>() {
                    @Override
                    public OrderTimeEntity convert(OrderTimeResponse value) {

                        return value.getData();
                    }
                },OrderTimeResponse.class));
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    public Observable<OrderDetailEntity> getOrderDetail(int orderId){
        return RetrofitManager.getInstance().getOrderDetail(orderId)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderDetailResponse,OrderDetailEntity>() {
                    @Override
                    public OrderDetailEntity convert(OrderDetailResponse value) {

                        return value.getData();
                    }
                },OrderDetailResponse.class));
    }
}
