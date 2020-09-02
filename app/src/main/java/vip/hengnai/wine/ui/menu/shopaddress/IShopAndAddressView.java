package vip.hengnai.wine.ui.menu.shopaddress;

import java.util.List;

import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.ShopDetailEntity;
import vip.hengnai.wine.entity.ShopEntity;
import vip.hengnai.wine.framework.IMvpListView;

/**
 * on 2019/11/19.
 *
 * @author hua.
 */

public interface IShopAndAddressView extends IMvpListView<ShopEntity> {
    /**
     * 送货上门界面获取我的收获地址数据回调
     * @param personAddressEntity
     */
    void getMyShopAddressSuccess(List<PersonAddressEntity> personAddressEntity);

    /**
     * 获取店铺信息
     * @param shopDetailEntity
     */
    void getShopDetail(ShopDetailEntity shopDetailEntity);
}
