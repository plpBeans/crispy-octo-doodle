package vip.hengnai.wine.ui.order;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderDetailEntity;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.framework.BaseMvpFragment;
import vip.hengnai.wine.ui.order.detail.OrderDetailActivity;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.StatusBarUtil;
import vip.hengnai.wine.util.glide.MyGlideModule;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;
import vip.hengnai.wine.util.rvwrapper.LoadMoreWrapper;

/**
 * 订单列表
 *
 * @author Hugh
 */
public class FragmentOrder extends BaseMvpFragment<IOrderView, OrderPresenter> implements IOrderView {


    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.recyclerView_order)
    RecyclerView recyclerViewOrder;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private View view;
    private OrderPresenter orderPresenter;
    private List<OrderEntity> orderEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private OrderProvider orderProvider;
    private LinearLayoutManager layoutManager;
    private LoadMoreWrapper  mLoadMoreWrapper;
    @Override
    protected OrderPresenter initPresenter() {
        return orderPresenter = new OrderPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 优化View减少View的创建次数
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order, null);
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
            MyMapUtils.startLocation();
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
                orderPresenter.getOrderList(Constants.GET_LIST_PAGE_INDEX,Constants.GET_LIST_PAGE_SIZE_20,0);
            }
        });
        orderPresenter.getOrderList(Constants.GET_LIST_PAGE_INDEX,Constants.GET_LIST_PAGE_SIZE_20,0);

    }

    /**
     * 订单列表布局
     */
    private void setAdapter() {
        //获取屏幕高度
        WindowManager wm = getActivity().getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point outSize = new Point();
        //不能省略,必须有
        display.getSize(outSize);
        //得到屏幕的宽度
        int screenWidth = outSize.x;

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewOrder.setLayoutManager(layoutManager);
        orderProvider = new OrderProvider(getActivity(),screenWidth);
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(orderEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mLoadMoreWrapper = new LoadMoreWrapper(getActivity(), recyclerViewOrder, emptyWrapper, 20);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //网络请求
                orderPresenter.getOrderList(Constants.GET_LIST_PAGE_INDEX,Constants.GET_LIST_PAGE_SIZE_20,orderEntityList.size());
            }
        });
        mPatientsAdapter.register(OrderEntity.class, orderProvider);
        recyclerViewOrder.setAdapter(mLoadMoreWrapper);
        orderProvider.setOnClickListener(new OrderProvider.OnClickListener() {
            @Override
            public void onItemClick(OrderEntity orderEntity) {
                //待支付
//                    startActivity(new Intent(getActivity(),OrderToPayActivity.class));
                //取消
//                startActivity(new Intent(getActivity(),OrderCancelActivity.class));
                //已完成
//                startActivity(new Intent(getActivity(),OrderFinishedActivity.class));
                //待送达
//                startActivity(new Intent(getActivity(),OrderToBeServedActivity.class));
                startActivity(new Intent(getActivity(),OrderDetailActivity.class).putExtra("orderId",orderEntity.getId()));
            }

            /**
             * 订单时间到刷新界面
             */
            @Override
            public void onOrderRefresh() {
                orderPresenter.getOrderList(Constants.GET_LIST_PAGE_INDEX,Constants.GET_LIST_PAGE_SIZE_20,0);
            }

            @Override
            public void onBtnClick(OrderEntity orderEntity,String type) {
                //1:去支付;2:取餐码;9:取消订单;11:去评价;12:开发票;19:再来一单
                if(Constants.VALUE_1.equals(type)){
                    showLongToast("支付");
                }else if(Constants.VALUE_3.equals(type)){
                    showLongToast("再来一单");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showDatas(List<OrderEntity> orderEntities) {
        orderEntityList.clear();
        orderEntityList.addAll(orderEntities);
        if(null==mPatientsAdapter){
            setAdapter();
        }else{
            mLoadMoreWrapper.notifyDataSetChanged();
        }

    }

    @Override
    public void appendDatas(List<OrderEntity> datas) {
        orderEntityList.addAll(datas);
        recyclerViewOrder.post(new Runnable() {
            @Override
            public void run() {
                mLoadMoreWrapper.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showLoadingMore() {
        mLoadMoreWrapper.loadingMore();
    }

    @Override
    public void noMoreData() {
        mLoadMoreWrapper.reachEnd();
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

    @Override
    public void getOrderDetail(OrderDetailEntity orderDetailEntity) {

    }
}
