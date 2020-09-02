package vip.hengnai.wine.framework.model;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import vip.hengnai.wine.entity.OrderCouponEntity;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.OrderCouponResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

/**
 * on 2019/12/11.
 *
 * @author hua.
 */

public class OrderCouponModel {
    /**
     * 订单模块获取可用优惠券集合
     * @param orderId
     * @return
     */
    public Observable<List<OrderCouponEntity>> getCouponsForOrder(int orderId){
        return RetrofitManager.getInstance().getCouponsForOrder(Integer.valueOf(orderId))
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OrderCouponResponse,List<OrderCouponEntity>>() {
                    @Override
                    public List<OrderCouponEntity> convert(OrderCouponResponse value) {

                        List<OrderCouponEntity> response=value.getData();
                        return null== response ? Collections.<OrderCouponEntity>emptyList() : response;
                    }
                },OrderCouponResponse.class));
    }
}
