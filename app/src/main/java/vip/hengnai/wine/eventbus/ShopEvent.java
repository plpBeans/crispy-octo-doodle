package vip.hengnai.wine.eventbus;

import vip.hengnai.wine.entity.ShopEntity;

/**
 * AUTHOR：dell
 * TIME:2019/8/30 2019/8/30
 * DESCRIPTION:ShopEvent
 * @author hua
 */
public class ShopEvent {
    private ShopEntity shopEntity;

    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public ShopEvent setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
        return this;
    }

    public ShopEvent(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }


}
