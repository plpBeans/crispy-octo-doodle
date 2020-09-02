package vip.hengnai.wine.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.CartEntity;
import vip.hengnai.wine.entity.ChangeCartEntity;
import vip.hengnai.wine.eventbus.MainEvent;
import vip.hengnai.wine.framework.BaseMvpFragment;
import vip.hengnai.wine.ui.order.confirm.OrderConfirmActivity;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.util.StatusBarUtil;
import vip.hengnai.wine.util.glide.MyGlideModule;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;
import vip.hengnai.wine.view.AddDeleteView;

/**
 * 购物车
 *
 * @author Hugh
 */
public class FragmentCart extends BaseMvpFragment<ICartView, CartPresenter> implements ICartView {

    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.recyclerView_shoppingCart)
    RecyclerView recyclerViewShoppingCart;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private View view;
    private CartPresenter cartPresenter;
    private List<CartEntity.GroupsBean> groupsBeanList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter=null;
    private CartProvider cartProvider;
    private LinearLayoutManager layoutManager;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected CartPresenter initPresenter() {
        return cartPresenter = new CartPresenter();
    }

    /**
     * 添加还是减少
     */
    private boolean isAdd;
    /**
     * 修改的数量
     */
    private int quantity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 优化View减少View的创建次数
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_shopping_cart, null);
//            ButterKnife.bind(this, view);
//            initView();
            return view;
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
//            initData();
    }
    /**
     * 每次fragment显示的时候在此方法调取定位
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            // 不在最前端界面显示

        } else {
            StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
//            MyMapUtils.startLocation();
        }
    }
    private void initView() {
        StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
        /*设置头部栏高度*/
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgBanner.getLayoutParams();
//        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
//        imgBanner.setLayoutParams(layoutParams);
        MyGlideModule.loadImage(getActivity(), imgBanner, "", R.mipmap.ic_home_bottom);
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getCart();
            }
        });
        presenter.getCart();
//        setAdapter();

    }

    /**
     * 购物车列表布局
     */
    private void setAdapter() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewShoppingCart.setLayoutManager(layoutManager);
        cartProvider = new CartProvider(getActivity());
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(groupsBeanList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter,0);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mPatientsAdapter.register(CartEntity.GroupsBean.class, cartProvider);
        recyclerViewShoppingCart.setAdapter(emptyWrapper);
        /**
         * 防止recyclerViewShoppingCart刷新闪一下
         */
        ((SimpleItemAnimator)recyclerViewShoppingCart.getItemAnimator()).setSupportsChangeAnimations(false);
        cartProvider.setOnClickListener(new CartProvider.OnClickListener() {
            @Override
            public void onItemClick(CartEntity.GroupsBean groupsBean) {
                    //遍历出itemId
                    ArrayList<Integer> itemList=new ArrayList<>();
                    for(int i=0;i<groupsBean.getItems().size();i++){
                        itemList.add(groupsBean.getItems().get(i).getItemId());
                    }
                    startActivity(new Intent(getActivity(), OrderConfirmActivity.class).putExtra("type","1")
                            .putExtra("groupId",groupsBean.getGroupId())
                            .putIntegerArrayListExtra("itemId",itemList));
            }

            @Override
            public void modifyCount(TextView txAllPrice, TextView txConcDescription, RecyclerView.Adapter adapter, CartEntity.GroupsBean groupsBean, CartEntity.GroupsBean.ItemsBean itemsBean, boolean Add, int goodsPosition) {
                isAdd=Add;
                 quantity = itemsBean.getQuantity();
                if(isAdd){
                    quantity+=1;
                }else{
                    if(itemsBean.getQuantity()==1) {
                        showLongToast("最小数量只能为1");
                        return;
                    }else{
                        quantity-=1;
                    }
                }
                JSONObject jsonObject=new JSONObject();
                try {
//                    jsonObject.put("groupId", groupsBean.getGroupId());
                    //1:结算项目选择;2:商品数量变更
                    jsonObject.put("changeType", "2");
                    JSONArray jsonArray=new JSONArray();
                    for(int i=0;i<groupsBean.getItems().size();i++){
                        //遍历group选中的itemId
                        if(groupsBean.getItems().get(i).isChoose()){
                            jsonArray.put(groupsBean.getItems().get(i).getItemId());
                        }
                    }
                    jsonObject.put("checkedItems",jsonArray);
                    jsonObject.put("itemId",itemsBean.getItemId());
                    jsonObject.put("quantity",quantity);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                //最后一个参数0为修改数量1为选中取消选中
                cartPresenter.changeCart(groupsBean.getGroupId(),body,adapter,txAllPrice,txConcDescription,itemsBean,goodsPosition,"0");
                //修改item数据
//                if(isAdd){
//                    itemsBean.setQuantity(itemsBean.getQuantity()+1);
//
//                }else{
//                    if(itemsBean.getQuantity()==1){
//                        if(groupsBean.getItems().size()==1){
//                            showLongToast("最小数量只能为1");
//                            return;
//                        }else{
//                            //如果group组有多种商品了那就移除某个商品
//                            groupsBean.getItems().remove(goodsPosition);
//                            adapter.notifyItemRemoved(goodsPosition);
//                            return;
//                        }
//                    }else{
//                        itemsBean.setQuantity(itemsBean.getQuantity()-1);
//                    }
//                }
//                adapter.notifyItemChanged(goodsPosition);
            }

            @Override
            public void onItemChoose(TextView txAllPrice, TextView txConcDescription, RecyclerView.Adapter adapter, CartEntity.GroupsBean groupsBean, CartEntity.GroupsBean.ItemsBean itemsBean, int goodsPosition) {
                JSONObject jsonObject=new JSONObject();
                try {
//                    jsonObject.put("groupId", groupsBean.getGroupId());
                    //1:结算项目选择;2:商品数量变更
                    jsonObject.put("changeType", "1");
                    JSONArray jsonArray=new JSONArray();
                    for(int i=0;i<groupsBean.getItems().size();i++){
                        //判断是否是选中的 item
                        if(itemsBean.getItemId()==groupsBean.getItems().get(i).getItemId()){
                            if(!itemsBean.isChoose()){
                                //点击前是未选中，现在应该是选中状态要添加到集合种
                                jsonArray.put(itemsBean.getItemId());
                            }
                        }else{
                            //判断group中其他item是否是选中的
                            if(groupsBean.getItems().get(i).isChoose()){
                                jsonArray.put(groupsBean.getItems().get(i).getItemId());
                            }
                        }
                    }
                    jsonObject.put("checkedItems",jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                //最后一个参数0为修改数量1为选中取消选中
                cartPresenter.changeCart(groupsBean.getGroupId(),body,adapter,txAllPrice,txConcDescription,itemsBean,goodsPosition,"1");
            }

        });
        emptyWrapper.setOnClickListener(new EmptyWrapper.OnClickListener() {
            @Override
            public void onButtonClick() {

//                ((MainActivity)getActivity()).goMenu();
                EventBus.getDefault().post(new MainEvent().setIndex("1"));

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showDatas(List<CartEntity.GroupsBean> datas) {
        groupsBeanList.clear();
            if(NotNull.isNotNull(datas)){
                groupsBeanList.addAll(datas);
                /**
                 * 默认所有都是 选中
                 */
                for(int i=0;i<groupsBeanList.size();i++){
                    for(int j = 0; j<groupsBeanList.get(i).getItems().size(); j++){
                        groupsBeanList.get(i).getItems().get(j).setChoose(true);
                    }
                }
            }
        if(null==mPatientsAdapter){
            setAdapter();
        }else{
             mPatientsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void appendDatas(List<CartEntity.GroupsBean> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }


    @Override
    public void showLoadingView() {
        swiprefresh.setRefreshing(true);
    }

    @Override
    public void hideLoadingView() {
        swiprefresh.setRefreshing(false);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 修改购物车group信息
     * @param changeCartEntity
     */
    @Override
    public void changeCartItem(ChangeCartEntity changeCartEntity, RecyclerView.Adapter adapter, TextView txAllPrice, TextView txConcDescription, CartEntity.GroupsBean.ItemsBean itemsBean,int goodsPosition,String changeType) {

        if(Constants.VALUE_1.equals(changeType)){
            //修改选中
            if(itemsBean.isChoose()){
                //点击前之前是选中
                itemsBean.setChoose(false);
            }else{
                //点击前是未选中
                itemsBean.setChoose(true);
            }
        }else{
            //修改数量
            itemsBean.setQuantity(quantity);
        }
        txAllPrice.setText("￥" + changeCartEntity.getAmount());
        //是否有优惠
        if(changeCartEntity.isPreferential()){
            txConcDescription.setVisibility(View.VISIBLE);
            txConcDescription.setText(changeCartEntity.getConcDescription());
        }else {
            txConcDescription.setVisibility(View.GONE);
        }

        adapter.notifyItemChanged(goodsPosition);
    }
}
