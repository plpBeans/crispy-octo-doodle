package vip.hengnai.wine.ui.order.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderCouponEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;

/**
 * 订单模块优惠券
 *
 * @author Hugh
 */
public class OrderOrderCouponActivity extends BaseMvpAppCompatActivity<IOrderCouponView, OrderCouponPresenter> implements IOrderCouponView {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tx_couponCount)
    TextView txCouponCount;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private OrderCouponPresenter orderCouponPresenter;
    private List<OrderCouponEntity> orderCouponEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private OrderCouponProvider orderCouponProvider;
    private LinearLayoutManager layoutManager;
    private int orderId;
    private int couponId;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coupon);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("选择优惠券");
        textTitleRight.setText("不使用");
        textTitleRight.setTextColor(ContextCompat.getColor(this, R.color.text_gray6));
        textTitleRight.setVisibility(View.VISIBLE);

        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderCouponPresenter.getCouponsForOrder(orderId);
            }
        });
        orderId = getIntent().getIntExtra("orderId", 0);
        orderCouponPresenter.getCouponsForOrder(orderId);

    }

    /**
     * 优惠券列表布局
     */
    private void setAdapter() {
        if(0!=orderCouponEntityList.size()){
            txCouponCount.setText("共"+orderCouponEntityList.size()+"张可用优惠券");
        }
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        orderCouponProvider = new OrderCouponProvider(this);
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(orderCouponEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mPatientsAdapter.register(OrderCouponEntity.class, orderCouponProvider);
        recyclerview.setAdapter(emptyWrapper);
        orderCouponProvider.setOnClickListener(new OrderCouponProvider.OnClickListener() {
            @Override
            public void onItemClick(OrderCouponEntity orderCouponEntity) {
                couponId=orderCouponEntity.getId();
                JSONObject changeObject=new JSONObject();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("used",true);
                    jsonObject.put("couponId",couponId);
                    changeObject.put("changeType","3");
                    changeObject.put("coupon",jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, changeObject.toString());
                EventBus.getDefault().post(body);
                finish();
            }

        });
    }

    @Override
    protected OrderCouponPresenter initPresenter() {
        return orderCouponPresenter = new OrderCouponPresenter();
    }

    @Override
    public void showDatas(List<OrderCouponEntity> datas) {
        orderCouponEntityList.clear();
        if(null!=datas){
            orderCouponEntityList.addAll(datas);
        }
        if (null == mPatientsAdapter) {
            setAdapter();
        } else {
            emptyWrapper.notifyDataSetChanged();
        }
    }

    @Override
    public void appendDatas(List<OrderCouponEntity> datas) {

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
        showLongToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick({R.id.img_back, R.id.text_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.text_title_right:
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("used",false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                EventBus.getDefault().post(body);
                finish();
                break;
        }
    }
}
