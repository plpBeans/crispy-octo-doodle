package vip.hengnai.wine.ui.order;

import vip.hengnai.wine.entity.OrderDetailEntity;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.framework.IMvpListView;


/**
 * @author Hugh
 */
public interface IOrderView extends IMvpListView<OrderEntity> {
    /**
     * 订单详情
     * @param orderDetailEntity
     */
    void getOrderDetail(OrderDetailEntity orderDetailEntity);
}
