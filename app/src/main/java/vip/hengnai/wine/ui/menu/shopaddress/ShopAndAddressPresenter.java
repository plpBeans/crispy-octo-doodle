package vip.hengnai.wine.ui.menu.shopaddress;

import android.support.annotation.NonNull;

import java.util.List;

import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.ShopDetailEntity;
import vip.hengnai.wine.entity.ShopEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.ShopModel;

/**
 * on 2019/11/19.
 *
 * @author hua.
 */

public class ShopAndAddressPresenter extends BaseMvpListAuthPresenter<IShopAndAddressView,ShopEntity> {
    private ShopModel shopModel;
    public ShopAndAddressPresenter(){
        shopModel=new ShopModel();
    }

    /**
     * 获取门店自提列表
     * @param q
     */
    public void getShopList(String q,String pageIndex,String pageSize , final int offset){
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
        addDisposable(shopModel.getShopList(q,pageIndex, pageSize), new BaseCommonErrorSubscriber<List<ShopEntity>>() {
            @Override
            public void onNext(List<ShopEntity> shopEntities) {
                resolveNext(shopEntities,offset);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }

    /**
     * 获取店铺信息
     * @param shopId
     */
    public void getShop(int shopId){

        addDisposable(shopModel.getShop(shopId),new BaseCommonErrorSubscriber<ShopDetailEntity>(){

            @Override
            public void onNext(ShopDetailEntity shopDetailEntity) {
                getView().getShopDetail(shopDetailEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }

    /**
     * 获取收货地址列表
     */
    public void getPersonAddressList(){

        addDisposable(shopModel.getPersonAddressList(), new BaseCommonErrorSubscriber<List<PersonAddressEntity>>() {
            @Override
            public void onNext(List<PersonAddressEntity> personAddressEntities) {
                getView().getMyShopAddressSuccess(personAddressEntities);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
}

