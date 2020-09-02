package vip.hengnai.wine.ui.order.confirm;

import vip.hengnai.wine.entity.OrderConfirmEntity;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.entity.OrderTimeEntity;
import vip.hengnai.wine.framework.IMvpListView;
import vip.hengnai.wine.framework.IMvpView;


/**
 * @author Hugh
 */
public interface IOrderConfirmView extends IMvpView {
    /**
     *生成订单信息
     * @param orderConfirmEntity
     */
    void orderConfirm(OrderConfirmEntity orderConfirmEntity);

    /**
     * 获取订单配送时间
     * @param orderTimeEntity
     */
    void getTime(OrderTimeEntity orderTimeEntity);
}
