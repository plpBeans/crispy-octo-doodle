package vip.hengnai.wine.ui.cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import okhttp3.RequestBody;
import vip.hengnai.wine.entity.CartEntity;
import vip.hengnai.wine.entity.ChangeCartEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.CartModel;

/**
 * AUTHOR：dell
 * TIME:2019/8/20 2019/8/20
 * DESCRIPTION:OrderPresenter
 * @author Hugh
 */
public class CartPresenter extends BaseMvpListAuthPresenter<ICartView,CartEntity.GroupsBean> {
    private CartModel cartModel;

    public CartPresenter() {
        cartModel = new CartModel();
    }

    /**
     * 获取购物车列表
     */
    public void getCart(){

        addDisposable(cartModel.getCart(),new BaseCommonErrorSubscriber<CartEntity>(){

            @Override
            public void onNext(CartEntity cartEntity) {
                getView().showDatas(cartEntity.getGroups());
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }

    /**
     * 购物车分组商品变更
     * @param groupId
     * @param requestBody
     * @return
     */
    public void changeCart(int groupId, RequestBody requestBody, RecyclerView.Adapter adapter, TextView txAllPrice, TextView txConcDescription, CartEntity.GroupsBean.ItemsBean itemsBean,int goodsPosition,String changeType){

        addDisposable(cartModel.changeCart(groupId,requestBody),new BaseCommonErrorSubscriber<ChangeCartEntity>(){

            @Override
            public void onNext(ChangeCartEntity changeCartEntity) {
                getView().changeCartItem(changeCartEntity,adapter,txAllPrice,txConcDescription,itemsBean,goodsPosition,changeType);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }
}
