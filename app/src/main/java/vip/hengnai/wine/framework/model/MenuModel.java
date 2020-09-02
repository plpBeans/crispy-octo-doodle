package vip.hengnai.wine.framework.model;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vip.hengnai.wine.entity.GoodsDetailEntity;
import vip.hengnai.wine.entity.MenuEntity;
import vip.hengnai.wine.framework.base.BaseResponse;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.DefaultMenuResponse;
import vip.hengnai.wine.framework.response.GoodsDetailResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

/**
 * on 2019/9/19.
 *
 * @author hua
 */

public class MenuModel {
    public   MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 获取默认菜单
     * @return
     */
    public Observable<MenuEntity> getDefaultMenus(){

        return RetrofitManager.getInstance().getDefaultMenus()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<DefaultMenuResponse,MenuEntity>() {
                    @Override
                    public MenuEntity convert(DefaultMenuResponse value) {
                        return value.getData();
                    }
                },DefaultMenuResponse.class));
    }
    /**
     * 获取自提门店菜单
     * @return
     */
    public Observable<MenuEntity> getShopMenus(int shopId){

        return RetrofitManager.getInstance().getShopMenus(shopId)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<DefaultMenuResponse,MenuEntity>() {
                    @Override
                    public MenuEntity convert(DefaultMenuResponse value) {
                        return value.getData();
                    }
                },DefaultMenuResponse.class));
    }
    /**
     * 获取自提菜单
     * @return
     */
    public Observable<MenuEntity> getTakeAwayMenus(int addressId){

        return RetrofitManager.getInstance().getTakeAwayMenus(addressId)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<DefaultMenuResponse,MenuEntity>() {
                    @Override
                    public MenuEntity convert(DefaultMenuResponse value) {
                        return value.getData();
                    }
                },DefaultMenuResponse.class));
    }
    /**
     * 获取商品详情信息
     * @param goodsId
     * @return
     */
    public Observable<GoodsDetailEntity> getGoods(int goodsId){
        return RetrofitManager.getInstance().getGoods(goodsId)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<GoodsDetailResponse,GoodsDetailEntity>() {
                    @Override
                    public GoodsDetailEntity convert(GoodsDetailResponse value) {
                        return value.getData();
                    }
                },GoodsDetailResponse.class));
    }

    /**
     * 添加商品到购物车
     * @param goodsId
     * @param specCodes
     * @param quantity
     * @return
     */
    public Observable<String> addCart(int goodsId,String specCodes,int quantity){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("goodsId", goodsId);
            jsonObject.put("specCodes", specCodes);
            jsonObject.put("quantity",quantity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        return RetrofitManager.getInstance().addCart(body)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<BaseResponse, String>() {
                    @Override
                    public String convert(BaseResponse value) {
                        return value.getMessage();
                    }
                },BaseResponse.class));
    }

}
