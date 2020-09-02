package vip.hengnai.wine.ui.order.confirm;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
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
import vip.hengnai.wine.entity.OrderConfirmEntity;
import vip.hengnai.wine.entity.OrderTimeEntity;
import vip.hengnai.wine.entity.PayEntity;
import vip.hengnai.wine.eventbus.ShopEvent;
import vip.hengnai.wine.eventbus.TakeAwayEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.ui.menu.shopaddress.ShopAndAddressActivity;
import vip.hengnai.wine.ui.order.coupon.OrderOrderCouponActivity;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.view.SwitchView;

/**
 * 订单确认-生成订单
 *
 * @author Hugh
 */
public class OrderConfirmActivity extends BaseMvpAppCompatActivity<IOrderConfirmView, OrderConfirmPresenter> implements IOrderConfirmView {


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
    @BindView(R.id.img_alert)
    ImageView imgAlert;
    @BindView(R.id.img_gone)
    ImageView imgGone;
    @BindView(R.id.ll_alert)
    RelativeLayout llAlert;
    @BindView(R.id.img_money)
    ImageView imgMoney;
    @BindView(R.id.tx_distribution)
    TextView txDistribution;
    @BindView(R.id.tx_distribution_msg)
    TextView txDistributionMsg;
    @BindView(R.id.tx_chooseTime)
    TextView txChooseTime;
    @BindView(R.id.img_address)
    ImageView imgAddress;
    @BindView(R.id.tx_name)
    TextView txName;
    @BindView(R.id.tx_byMyself)
    TextView txByMyself;
    @BindView(R.id.tx_outCall)
    TextView txOutCall;
    @BindView(R.id.tx_phone)
    TextView txPhone;
    @BindView(R.id.tx_address)
    TextView txAddress;
    @BindView(R.id.recyclerview_goods)
    RecyclerView recyclerviewGoods;
    @BindView(R.id.tx_concDescription)
    TextView txConcDescription;
    @BindView(R.id.tx_title_deliver)
    TextView txTitleDeliver;
    @BindView(R.id.tx_deliver_concPrice)
    TextView txDeliverConcPrice;
    @BindView(R.id.tx_deliver_price)
    TextView txDeliverPrice;
    @BindView(R.id.tx_orderPrice)
    TextView txOrderPrice;
    @BindView(R.id.tx_coupon)
    TextView txCoupon;
    @BindView(R.id.img_coupon)
    ImageView imgCoupon;
    @BindView(R.id.ll_coupon)
    LinearLayout llCoupon;
    @BindView(R.id.tx_title_integral)
    TextView txTitleIntegral;
    @BindView(R.id.tx_integral)
    TextView txIntegral;
    @BindView(R.id.tx_integral_money)
    TextView txIntegralMoney;
    @BindView(R.id.ll_integral)
    LinearLayout llIntegral;
    @BindView(R.id.tx_to_pay)
    TextView txToPay;
    @BindView(R.id.img_pay)
    ImageView imgPay;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;
    @BindView(R.id.tx_payTreaty)
    TextView txPayTreaty;
    @BindView(R.id.tx_orderFinalPrice)
    TextView txOrderFinalPrice;
    @BindView(R.id.tx_concItems)
    TextView txConcItems;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.tx_address_for)
    TextView txAddressFor;
    @BindView(R.id.switch_integral)
    SwitchView switchIntegral;
    @BindView(R.id.tx_goodsAllPrice)
    TextView txGoodsAllPrice;
    @BindView(R.id.tx_count_pay)
    TextView txCountPay;
    private OrderConfirmPresenter orderConfirmPresenter;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String payId = "1";
    /**
     * 商品id
     */
    private int goodsId = 0;
    /**
     * 商品规格
     */
    private String specCode = "";
    /**
     * 商品数量
     */
    private int quantity = 0;
    /**
     * 来自商品详情还是购物车
     * 0是商品详情
     * 1是购物车
     */
    private String type;
    /**
     * 购物车过来的分组id
     */
    private int groupId;
    /**
     * 购物车过来的itemId数组
     */
    private List<Integer> itemIdList = new ArrayList<>();
    /**
     * 获取的订单id
     */
    private int orderId;
    /**
     * 选择时间集合
     */
    private List<String> timeList = new ArrayList<>();
    /**
     * 时间选择器
     */
    private OptionsPickerView timePickerView;
    /**
     * 是否预约
     */
    private boolean booking;
    /**
     * 日期时间模式
     */
    private String mode;
    /**
     * 积分抵扣金额
     */
    private double deduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();

    }

    private void initView() {
           /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("确认订单");
        type = getIntent().getStringExtra("type");
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            if (Constants.VALUE_0.equals(type)) {
                //从商品详情
                goodsId = getIntent().getIntExtra("goodsId", 0);
                specCode = getIntent().getStringExtra("specCode");
                quantity = getIntent().getIntExtra("quantity", 0);
                jsonObject.put("goodsId", goodsId);
                jsonObject.put("specCode", specCode);
                jsonObject.put("quantity", quantity);
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                orderConfirmPresenter.goodsForOrder(body);
            } else {
                //从购物车进来
                groupId = getIntent().getIntExtra("groupId", 0);
                itemIdList = getIntent().getIntegerArrayListExtra("itemId");
                for (int i = 0; i < itemIdList.size(); i++) {
                    jsonArray.put(i, itemIdList.get(i));
                }
                jsonObject.put("groupId", groupId);
                jsonObject.put("itemId", jsonArray);
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                orderConfirmPresenter.cartForOrder(body);
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected OrderConfirmPresenter initPresenter() {
        return orderConfirmPresenter = new OrderConfirmPresenter();
    }

    @Override
    public void showLoadingView() {
        showProgressDialog(null, "正在加载", null, true);
    }

    @Override
    public void hideLoadingView() {
        dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showLongToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick({R.id.img_back, R.id.btn_pay, R.id.img_gone, R.id.ll_coupon,
            R.id.tx_chooseTime, R.id.tx_byMyself, R.id.tx_outCall, R.id.ll_pay, R.id.switch_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_gone:
                llAlert.setVisibility(View.GONE);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_pay:
                break;
            case R.id.tx_chooseTime:
                //选择时间
                orderConfirmPresenter.getTimeForOrder(orderId);

                break;
            case R.id.switch_integral:
                //积分
                //选中后调用更改订单信息接口
                JSONObject changeObject = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("used", switchIntegral.isOpened());
                    jsonObject.put("deduction", deduction);
                    //1:配送方式变更;2:预约变更;3:优惠券变更;4:积分变更
                    changeObject.put("changeType", "4");
                    changeObject.put("points", jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                orderConfirmPresenter.changeOrder(orderId, body);
                break;
            case R.id.ll_pay:
                //支付弹窗
                showPayDialog(payId);
                break;
            case R.id.ll_coupon:
                //优惠券
                startActivity(new Intent(OrderConfirmActivity.this, OrderOrderCouponActivity.class).putExtra("orderId", orderId));
                break;
            case R.id.tx_byMyself:
                //自提
                txByMyself.setBackgroundResource(R.mipmap.btn_choose_location);
                txByMyself.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.white));
                txOutCall.setBackgroundResource(0);
                txOutCall.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.lunarTextColor));
                startActivity(new Intent(OrderConfirmActivity.this, ShopAndAddressActivity.class).putExtra("type", "0").putExtra("from", "1"));
                break;
            case R.id.tx_outCall:
                //外送
                txByMyself.setBackgroundResource(0);
                txByMyself.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.lunarTextColor));
                txOutCall.setBackgroundResource(R.mipmap.btn_choose_location);
                txOutCall.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.white));
                startActivity(new Intent(OrderConfirmActivity.this, ShopAndAddressActivity.class).putExtra("type", "1").putExtra("from", "1"));
                break;
        }
    }

    /**
     * 时间选择器
     */
    private void showTimeDialog() {
        timePickerView = new OptionsPickerBuilder(OrderConfirmActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //选中后调用更改订单信息接口
                JSONObject changeObject = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("booking", booking);
                    jsonObject.put("mode", mode);
                    if (Constants.VALUE_1.equals(mode)) {
                        jsonObject.put("bookingDate", timeList.get(options1));
                    } else if (Constants.VALUE_2.equals(mode)) {
                        jsonObject.put("bookingTime", timeList.get(options1));
                    }
                    changeObject.put("changeType", "2");
                    changeObject.put("bookingInfo", jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                orderConfirmPresenter.changeOrder(orderId, body);
            }
        })
//                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                //标题文字
                .setTitleText("选择时间")
                //标题文字大小
                .setTitleSize(16)
                .setTitleColor(getResources().getColor(R.color.pickerview_center_text_color))
                //取消按钮文字
                .setCancelText("取消")
                .setSubCalSize(14)
                //取消按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))
                //确认按钮文字
                .setSubmitText("确定")
                //确定按钮文字颜色
                .setSubmitColor(getResources().getColor(R.color.pickerview_center_text_color))
                //滚轮文字大小
                .setContentTextSize(16)
                //设置选中文本的颜色值
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))
                //行间距
                .setLineSpacingMultiplier(1.8f)
                //设置分割线的颜色
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))
                //设置选择的值
                .setSelectOptions(0)
                .build();
        //添加数据
        timePickerView.setPicker(timeList);
        timePickerView.show();
    }

    /**
     * 支付方式选择弹窗
     */
    public void showPayDialog(String id) {
        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.ActionSheetDialogStyle).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setContentView(R.layout.alert_dialog_pay);
        window.setGravity(Gravity.BOTTOM);
        RecyclerView recyclerview = window.findViewById(R.id.recyclerview);
        List<PayEntity> discussEntityList = new ArrayList<>();
        discussEntityList.add(new PayEntity().setId("1").setName("微信"));
        discussEntityList.add(new PayEntity().setId("2").setName("支付宝"));
        recyclerview.setLayoutManager(new LinearLayoutManager(OrderConfirmActivity.this));

        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();

        PayProvider payProvider = new PayProvider(id);
        //点击监听
        payProvider.setOnClickListener(new PayProvider.OnClickListener() {
            @Override
            public void onItemClick(PayEntity payEntity) {
                payId = payEntity.getId();
                txToPay.setText(payEntity.getName());
                dlg.dismiss();
            }
        });

        mPatientsAdapter.register(PayEntity.class, payProvider);

        mPatientsAdapter.setItems(discussEntityList);
        recyclerview.setAdapter(mPatientsAdapter);
    }

    /**
     * 获取订单信息
     *
     * @param orderConfirmEntity
     */
    @Override
    public void orderConfirm(OrderConfirmEntity orderConfirmEntity) {
        orderId = orderConfirmEntity.getId();

        //判断自提还是外送  自提显示店铺名 营业时间 店铺地址    外送显示收货人  电话  地址
        if (orderConfirmEntity.getDeliver().isTakeaway()) {

            //外送
            txByMyself.setBackgroundResource(0);
            txByMyself.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.lunarTextColor));
            txOutCall.setBackgroundResource(R.mipmap.btn_choose_location);
            txOutCall.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.white));
            if (null != orderConfirmEntity.getDeliver() && null != orderConfirmEntity.getDeliver().getAddress()) {
                //收货人姓名
                txName.setText(orderConfirmEntity.getDeliver().getAddress().getAddressee());
                //收货人电话
                txPhone.setText("电话:" + orderConfirmEntity.getDeliver().getAddress().getPhone());
                //收货人地址
                txAddress.setText("地址:" + orderConfirmEntity.getDeliver().getAddress().getAddress());
                txAddressFor.setVisibility(View.VISIBLE);
                txAddressFor.setText(orderConfirmEntity.getDeliver().getAddress().getTag());
            }
        } else {
            txAddressFor.setVisibility(View.GONE);
            //自提
            txByMyself.setBackgroundResource(R.mipmap.btn_choose_location);
            txByMyself.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.white));
            txOutCall.setBackgroundResource(0);
            if(null != orderConfirmEntity.getDeliver() && null != orderConfirmEntity.getDeliver().getShop()) {
                txOutCall.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.lunarTextColor));
                //店铺名称
                txName.setText(orderConfirmEntity.getDeliver().getShop().getTitle());
                //营业时间
                txPhone.setText("营业时间:" + orderConfirmEntity.getDeliver().getShop().getHours());
                //店铺地址
                txAddress.setText("地址:" + orderConfirmEntity.getDeliver().getShop().getAddress());
            }
        }
        //判断订单是否可预约
        if (orderConfirmEntity.isBookable()) {
            //显示预约时间按钮
            txChooseTime.setVisibility(View.VISIBLE);
            //是否预约
            booking = orderConfirmEntity.getBookingInfo().isBooking();
        } else {
            //隐藏预约时间按钮
            txChooseTime.setVisibility(View.GONE);
        }
        //商品列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                //不能滑动
                return false;
            }
        };
        recyclerviewGoods.setLayoutManager(layoutManager);
        ConfirmProvider confirmProvider = new ConfirmProvider(this);
        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(orderConfirmEntity.getItems());

        mPatientsAdapter.register(OrderConfirmEntity.ItemsBean.class, confirmProvider);
        recyclerviewGoods.setAdapter(mPatientsAdapter);
        //订单信息如几点送达
        txDistributionMsg.setText(orderConfirmEntity.getMessage());
        //自提外送文字
        txDistribution.setText(orderConfirmEntity.getStatus());
        //运费优惠说明
        txConcDescription.setText("(" + orderConfirmEntity.getDeliver().getConcDescription() + ")");
        //运费原价
        txDeliverPrice.setText("￥" + orderConfirmEntity.getDeliver().getPrice());
        txDeliverPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        //运费优惠价格
        txDeliverConcPrice.setText("￥" + orderConfirmEntity.getDeliver().getConcPrice());
        //订单总价
        txOrderPrice.setText("￥" + orderConfirmEntity.getAmount());
        //应付多少钱
        txOrderFinalPrice.setText("￥" + orderConfirmEntity.getPayment());
        //商品总价
        txGoodsAllPrice.setText("￥"+orderConfirmEntity.getGoodsAmount());
        //多少件商品
        txCountPay.setText("共"+orderConfirmEntity.getGoodsQuantity()+"件 合计:");
        //优惠信息
        String concItems = "";
        for (int i = 0; i < orderConfirmEntity.getConcItems().size(); i++) {
            if (i == 0) {
                concItems = orderConfirmEntity.getConcItems().get(i);
            } else {
                concItems += "\n" + orderConfirmEntity.getConcItems().get(i);
            }

        }
        if (TextUtils.isEmpty(concItems)) {
            txConcItems.setVisibility(View.GONE);
        } else {
            txConcItems.setVisibility(View.VISIBLE);
            txConcItems.setText(concItems);
        }

        //优惠券信息
        if (null != orderConfirmEntity.getCoupon()) {
            //优惠券可用
            if (orderConfirmEntity.getCoupon().isAvaliable()) {
                if (NotNull.isNotNull(orderConfirmEntity.getCoupon().getCouponId())) {
                    //getCouponId为null代表不使用优惠券
                    txCoupon.setText(orderConfirmEntity.getCoupon().getCouponName() + " - ￥" + orderConfirmEntity.getCoupon().getDeduction());
                } else {
                    txCoupon.setText("不使用优惠券");
                }

            } else {
                txCoupon.setText("无可用优惠券");
            }

        } else {
            txCoupon.setText("");
        }
        //可用积分信息
        if (null != orderConfirmEntity.getPoints()) {
            if (orderConfirmEntity.getPoints().isAvaliable()) {
                deduction = orderConfirmEntity.getPoints().getAvaliableDuduction();
                //积分可用
                txIntegral.setText("(共" + orderConfirmEntity.getPoints().getAvaliablePoints() + "可抵扣" + deduction + "元)");
                switchIntegral.setVisibility(View.VISIBLE);
                txIntegralMoney.setVisibility(View.GONE);
            } else {
                //积分不可用
                txIntegral.setText("(共" + orderConfirmEntity.getPoints().getAvaliablePoints() + "满1000可用)");
                switchIntegral.setVisibility(View.GONE);
                txIntegralMoney.setVisibility(View.VISIBLE);
                txIntegralMoney.setText("不可用");
            }
        } else {
            txIntegral.setText("");
            txIntegralMoney.setText("");
        }


    }

    /**
     * 获取配上送时间
     *
     * @param orderTimeEntity
     */
    @Override
    public void getTime(OrderTimeEntity orderTimeEntity) {
        timeList.clear();
        //1:日期;2:时间;3:日期时间
        if (NotNull.isNotNull(orderTimeEntity)) {
            mode = orderTimeEntity.getMode();
            if (Constants.VALUE_1.equals(mode)) {
                timeList.addAll(orderTimeEntity.getDate());
            } else if (Constants.VALUE_2.equals(mode)) {
                timeList.addAll(orderTimeEntity.getTime());
            } else {

            }
        }
        showTimeDialog();
    }

    /**
     * 优惠券返回
     *
     * @param requestBody
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RequestBody requestBody) {
        orderConfirmPresenter.changeOrder(orderId, requestBody);
    }

    /**
     * 选中店铺信息
     *
     * @param shopEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShop(ShopEvent shopEvent) {
        //自提
        txByMyself.setBackgroundResource(R.mipmap.btn_choose_location);
        txByMyself.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.white));
        txOutCall.setBackgroundResource(0);
        txOutCall.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.lunarTextColor));
        //店铺名称
        txName.setText(shopEvent.getShopEntity().getTitle());
        //营业时间
        txPhone.setText("营业时间:" + shopEvent.getShopEntity().getHours());
        //店铺地址
        txAddress.setText("地址:" + shopEvent.getShopEntity().getAddress());
        JSONObject changeObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("takeaway", false);
            jsonObject.put("shopId", shopEvent.getShopEntity().getId());
            changeObject.put("changeType", "1");
            changeObject.put("deliver", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        orderConfirmPresenter.changeOrder(orderId, body);
    }

    /**
     * 选中个人地址信息外送
     *
     * @param takeAwayEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTakeAway(TakeAwayEvent takeAwayEvent) {
        //外送
        txByMyself.setBackgroundResource(0);
        txByMyself.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.lunarTextColor));
        txOutCall.setBackgroundResource(R.mipmap.btn_choose_location);
        txOutCall.setTextColor(ContextCompat.getColor(OrderConfirmActivity.this, R.color.white));

        //收货人姓名
        txName.setText(takeAwayEvent.getPersonAddressEntity().getAddressee());
        //收货人电话
        txPhone.setText("电话:" + takeAwayEvent.getPersonAddressEntity().getPhone());
        //收货人地址
        txAddress.setText("地址:" + takeAwayEvent.getPersonAddressEntity().getAddress());
        JSONObject changeObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("takeaway", true);
            jsonObject.put("addressId", takeAwayEvent.getPersonAddressEntity().getId());
            changeObject.put("changeType", "1");
            changeObject.put("deliver", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        //调用外送菜单
        orderConfirmPresenter.changeOrder(orderId, body);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
