package vip.hengnai.wine.ui.menu;

import android.support.annotation.NonNull;

import vip.hengnai.wine.entity.GoodsDetailEntity;
import vip.hengnai.wine.entity.MenuEntity;
import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.MenuModel;

/**
 * AUTHOR：dell
 * TIME:2019/8/20 2019/8/20
 * DESCRIPTION:MenuPresenter
 * @author Hugh
 */
public class MenuPresenter extends BaseMvpPresenter<IMenuView> {
    private MenuModel menuModel;

    public MenuPresenter() {
        menuModel = new MenuModel();
    }

    /**
     * 获取默认菜单
     */
    public void getDefaultMenus(){

        addDisposable(menuModel.getDefaultMenus(),new BaseCommonErrorSubscriber<MenuEntity>(){

            @Override
            public void onNext(MenuEntity menuEntity) {
                getView().showDefaultMenu(menuEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }
    /**
     * 获取自提门店菜单
     */
    public void getShopMenus(int shopId){

        addDisposable(menuModel.getShopMenus(shopId),new BaseCommonErrorSubscriber<MenuEntity>(){

            @Override
            public void onNext(MenuEntity menuEntity) {
                getView().showDefaultMenu(menuEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }
    /**
     * 获取外送菜单
     */
    public void getTakeAwayMenus(int shopId){

        addDisposable(menuModel.getTakeAwayMenus(shopId),new BaseCommonErrorSubscriber<MenuEntity>(){

            @Override
            public void onNext(MenuEntity menuEntity) {
                getView().showDefaultMenu(menuEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }

    /**
     * 获取商品详信息
     * @param goodsId
     */
    public void getGoods(int goodsId){

        addDisposable(menuModel.getGoods(goodsId),new BaseCommonErrorSubscriber<GoodsDetailEntity>(){

            @Override
            public void onNext(GoodsDetailEntity goodsDetailEntity) {
                getView().showGoods(goodsDetailEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }

    /**
     * 添加购物车
     * @param goodsId
     * @param specCodes
     * @param quantity
     */
    public void addCart(int goodsId,String specCodes,int quantity){

        addDisposable(menuModel.addCart(goodsId,specCodes,quantity),new BaseCommonErrorSubscriber<String>(){

            @Override
            public void onNext(String message) {
                getView().addCartMessage(message);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }



}

