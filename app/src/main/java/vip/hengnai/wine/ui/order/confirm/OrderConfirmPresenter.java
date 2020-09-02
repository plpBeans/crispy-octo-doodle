package vip.hengnai.wine.ui.order.confirm;

import io.reactivex.annotations.NonNull;
import okhttp3.RequestBody;
import vip.hengnai.wine.entity.OrderConfirmEntity;
import vip.hengnai.wine.entity.OrderTimeEntity;
import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.OrderModel;

/**
 * @author Hugh
 */
public class OrderConfirmPresenter extends BaseMvpPresenter<IOrderConfirmView> {
    private OrderModel orderModel;

    public OrderConfirmPresenter() {
        orderModel = new OrderModel();
    }

    /**
     * 商品生成订单信息
     * @param requestBody
     */
    public void goodsForOrder(RequestBody requestBody){
        addDisposable(orderModel.goodsForOrder(requestBody), new BaseCommonErrorSubscriber<OrderConfirmEntity>() {
            @Override
            public void onNext(OrderConfirmEntity orderConfirmEntity) {
              getView().orderConfirm(orderConfirmEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
    /**
     * 购物车生成订单信息
     * @param requestBody
     */
    public void cartForOrder(RequestBody requestBody){
        addDisposable(orderModel.cartForOrder(requestBody), new BaseCommonErrorSubscriber<OrderConfirmEntity>() {
            @Override
            public void onNext(OrderConfirmEntity orderConfirmEntity) {
                getView().orderConfirm(orderConfirmEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }

    /**
     * 变更订单内容
     * @param orderId
     * @param requestBody
     */
    public void changeOrder(int orderId,RequestBody requestBody){
        addDisposable(orderModel.changeOrder(orderId,requestBody), new BaseCommonErrorSubscriber<OrderConfirmEntity>() {
            @Override
            public void onNext(OrderConfirmEntity orderConfirmEntity) {
                getView().orderConfirm(orderConfirmEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }

    /**
     * 订单模块获取可预约时间信息
     * @param orderId
     */
    public void getTimeForOrder(int orderId){
        addDisposable(orderModel.getTimeForOrder(orderId), new BaseCommonErrorSubscriber<OrderTimeEntity>() {
            @Override
            public void onNext(OrderTimeEntity orderTimeEntity) {
                getView().getTime(orderTimeEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
//    /**
//     * 获取订单列表
//     * @param pageIndex
//     * @param pageSize
//     * @param offset
//     */
//    public void getOrderList(String pageIndex,String pageSize , final int offset){
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
//        addDisposable(orderModel.getOrderList(pageIndex, pageSize), new BaseCommonErrorSubscriber<List<OrderEntity>>() {
//            @Override
//            public void onNext(List<OrderEntity> orderEntities) {
//                resolveNext(orderEntities,offset);
//            }
//
//            @NonNull
//            @Override
//            public IMvpView getMvpView() {
//                return getView();
//            }
//        });
//    }

}
