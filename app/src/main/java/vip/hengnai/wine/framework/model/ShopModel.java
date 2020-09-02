package vip.hengnai.wine.framework.model;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.ShopDetailEntity;
import vip.hengnai.wine.entity.ShopEntity;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.PersonAddressResponse;
import vip.hengnai.wine.framework.response.ShopDetailResponse;
import vip.hengnai.wine.framework.response.ShopResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

/**
 * on 2020/1/4.
 *
 * @author hua.
 */

public class ShopModel {
    /**
     * 获取门店自提列表
     * @param q
     * @return
     */
    public Observable<List<ShopEntity>> getShopList(String q,String pageIndex,String pageSize){
        return RetrofitManager.getInstance().getShopList(q,pageIndex, pageSize)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<ShopResponse, List<ShopEntity>>() {
                    @Override
                    public List<ShopEntity> convert(ShopResponse value) {
                        List<ShopEntity> response=value.getData();
                        return null== response ? Collections.<ShopEntity>emptyList() : response;
                    }
                },ShopResponse.class));
    }

    /**
     * 获取门店信息
     * @param shopId
     * @return
     */
    public Observable<ShopDetailEntity> getShop(int shopId){
        return RetrofitManager.getInstance().getShop(Integer.valueOf(shopId))
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<ShopDetailResponse,ShopDetailEntity>() {
                    @Override
                    public ShopDetailEntity convert(ShopDetailResponse value) {
                        return value.getData();
                    }
                },ShopDetailResponse.class));
    }

    /**
     * 个人地址列表
     * @return
     */
    public Observable<List<PersonAddressEntity>> getPersonAddressList(){
        return RetrofitManager.getInstance().getPersonAddressList()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<PersonAddressResponse, List<PersonAddressEntity>>() {
                    @Override
                    public List<PersonAddressEntity> convert(PersonAddressResponse value) {
                        List<PersonAddressEntity> response=value.getData();
                        return null== response ? Collections.<PersonAddressEntity>emptyList() : response;
                    }
                },PersonAddressResponse.class));
    }
}
