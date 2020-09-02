package vip.hengnai.wine.ui.order.coupon;

import java.util.List;

import io.reactivex.annotations.NonNull;
import vip.hengnai.wine.entity.OrderCouponEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.OrderCouponModel;

/**
 * on 2019/12/11.
 *
 * @author hua.
 */

public class OrderCouponPresenter extends BaseMvpListAuthPresenter<IOrderCouponView,OrderCouponEntity> {
    private OrderCouponModel orderCouponModel;

    public OrderCouponPresenter() {
        orderCouponModel = new OrderCouponModel();
    }

    /**
     * 获取可用优惠券列表
     * @param orderId
     */
    public void getCouponsForOrder(int orderId){
//        if (offset % getPageSize() != 0) {
//            return;
//        }
//        if (offset == 0) {
//            getView().showLoadingView();
//        } else {
//            getView().showLoadingMore();
//        }
//
//        if (offset / getPageSize() == 0) {
//            pageIndex = "0";
//        } else {
//            pageIndex = String.valueOf(offset / getPageSize());
//        }
        addDisposable(orderCouponModel.getCouponsForOrder(orderId), new BaseCommonErrorSubscriber<List<OrderCouponEntity>>() {
            @Override
            public void onNext(List<OrderCouponEntity> orderCouponEntityList) {
//                resolveNext(orderEntities,offset);
                getView().showDatas(orderCouponEntityList);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
}
