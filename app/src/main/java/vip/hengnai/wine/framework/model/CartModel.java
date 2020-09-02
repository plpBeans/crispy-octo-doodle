package vip.hengnai.wine.framework.model;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import vip.hengnai.wine.entity.CartEntity;
import vip.hengnai.wine.entity.ChangeCartEntity;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.CartResponse;
import vip.hengnai.wine.framework.response.ChangeCartResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

/**
 * on 2019/12/9.
 * @author hua
 */

public class CartModel {

    /**
     * 获取购物车列表
     * @return
     */
    public Observable<CartEntity> getCart(){
        return RetrofitManager.getInstance().getCart()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<CartResponse,CartEntity>() {
                    @Override
                    public CartEntity convert(CartResponse value) {
                        return value.getData();
                    }
                },CartResponse.class));
    }

    /**
     * 购物车分组商品变更
     * @param groupId
     * @param requestBody
     * @return
     */
    public Observable<ChangeCartEntity> changeCart(int groupId, RequestBody requestBody){
        return RetrofitManager.getInstance().changeCart(groupId,requestBody)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<ChangeCartResponse,ChangeCartEntity>() {
                    @Override
                    public ChangeCartEntity convert(ChangeCartResponse value) {
                        return value.getData();
                    }
                },ChangeCartResponse.class));
    }

}
