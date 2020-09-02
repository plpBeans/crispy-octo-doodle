package vip.hengnai.wine.ui.cart;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import vip.hengnai.wine.entity.CartEntity;
import vip.hengnai.wine.entity.ChangeCartEntity;
import vip.hengnai.wine.framework.IMvpListView;


/**
 * @author Hugh
 */
public interface ICartView extends IMvpListView<CartEntity.GroupsBean> {
    /**
     * 变更刷新
     * @param changeCartEntity
     * @param adapter
     * @param txAllPrice
     * @param txConcDescription
     * @param itemsBean
     * @param goodsPosition
     * @param changeType 0为修改数量 1是修改选中
     */
    void changeCartItem(ChangeCartEntity changeCartEntity, RecyclerView.Adapter adapter, TextView txAllPrice, TextView txConcDescription, CartEntity.GroupsBean.ItemsBean itemsBean,int goodsPosition,String changeType);
}
