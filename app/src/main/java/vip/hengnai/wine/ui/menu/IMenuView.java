package vip.hengnai.wine.ui.menu;

import vip.hengnai.wine.entity.GoodsDetailEntity;
import vip.hengnai.wine.entity.MenuEntity;
import vip.hengnai.wine.framework.IMvpView;

/**
 *
 * @author Hugh
 */
public interface IMenuView extends IMvpView {
    /**
     * 商品列表中的点击事件
     * @param position
     * @param isScroll
     */
    void check(int position,boolean isScroll);

    /**
     * 默认菜单列表
     * @param menuEntity
     */
    void showDefaultMenu(MenuEntity menuEntity);

//    /**
//     * 自提门店菜单列表
//     * @param menuEntity
//     */
//    void showShopMenu(MenuEntity menuEntity);
//    /**
//     * 外送菜单
//     * @param menuEntity
//     */
//    void showTakeAwayMenu(MenuEntity menuEntity);

    /**
     * 获取商品详细信息
     * @param goodsDetailEntity
     */
    void showGoods(GoodsDetailEntity goodsDetailEntity);

    /**
     * 添加购物车回调
     * @param message
     */
    void addCartMessage(String message);
}
