package vip.hengnai.wine.ui.order;

import android.support.annotation.NonNull;

import java.util.List;

import vip.hengnai.wine.entity.OrderDetailEntity;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.entity.ShopEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.OrderModel;

/**
 * @author Hugh
 */
public class OrderPresenter extends BaseMvpListAuthPresenter<IOrderView,OrderEntity> {
    private OrderModel orderModel;

    public OrderPresenter() {
        orderModel = new OrderModel();
    }

    /**
     * 获取订单列表
     * @param pageIndex
     * @param pageSize
     * @param offset
     */
    public void getOrderList(String pageIndex,String pageSize , final int offset){
        if (offset % getPageSize() != 0) {
            return;
        }
        if (offset == 0) {
            getView().showLoadingView();
        } else {
            getView().showLoadingMore();
        }

        if (offset / getPageSize() == 0) {
            pageIndex = "0";
        } else {
            pageIndex = String.valueOf(offset / getPageSize());
        }
        addDisposable(orderModel.getOrderList(pageIndex, pageSize), new BaseCommonErrorSubscriber<List<OrderEntity>>() {
            @Override
            public void onNext(List<OrderEntity> orderEntities) {
                resolveNext(orderEntities,offset);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }

    /**
     * 订单详情
     * @param orderId
     */
    public void getOrderDetail(int orderId){
        addDisposable(orderModel.getOrderDetail(orderId), new BaseCommonErrorSubscriber<OrderDetailEntity>() {
            @Override
            public void onNext(OrderDetailEntity orderDetailEntity) {
                getView().getOrderDetail(orderDetailEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
}
