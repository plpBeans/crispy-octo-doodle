package vip.hengnai.wine.ui.order.detail;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.DiscussEntity;
import vip.hengnai.wine.entity.OrderDetailEntity;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.ui.order.IOrderView;
import vip.hengnai.wine.ui.order.OrderActionsAdapter;
import vip.hengnai.wine.ui.order.OrderPresenter;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.util.TimeUtil;
import vip.hengnai.wine.view.TimerView;

/**
 * 订单详情
 *
 * @author Hugh
 */
public class OrderDetailActivity extends BaseMvpAppCompatActivity<IOrderView, OrderPresenter> implements IOrderView {
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
    @BindView(R.id.tx_address_for)
    TextView txAddressFor;
    @BindView(R.id.tx_phone)
    TextView txPhone;
    @BindView(R.id.tx_address)
    TextView txAddress;
    @BindView(R.id.recyclerview_goods)
    RecyclerView recyclerviewGoods;
    @BindView(R.id.tx_goodsAllPrice)
    TextView txGoodsAllPrice;
    @BindView(R.id.tx_title_deliver)
    TextView txTitleDeliver;
    @BindView(R.id.tx_concDescription)
    TextView txConcDescription;
    @BindView(R.id.tx_deliver_concPrice)
    TextView txDeliverConcPrice;
    @BindView(R.id.tx_deliver_price)
    TextView txDeliverPrice;
    @BindView(R.id.tx_goodsCoupon)
    TextView txGoodsCoupon;
    @BindView(R.id.rl_coupon)
    RelativeLayout rlCoupon;
    @BindView(R.id.tx_points)
    TextView txPoints;
    @BindView(R.id.rl_points)
    RelativeLayout rlPoints;
    @BindView(R.id.tx_count_pay)
    TextView txCountPay;
    @BindView(R.id.tx_orderPrice)
    TextView txOrderPrice;
    @BindView(R.id.tx_Courier)
    TextView txCourier;
    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.rl_Courier)
    RelativeLayout rlCourier;
    @BindView(R.id.tx_orderNumber)
    TextView txOrderNumber;
    @BindView(R.id.tx_copy)
    TextView txCopy;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.tx_timer)
    TimerView txTimer;
    @BindView(R.id.tx_time_message)
    TextView txTimeMessage;
    @BindView(R.id.tx_takeAway)
    TextView txTakeAway;
    @BindView(R.id.tx_log)
    TextView txLog;
    @BindView(R.id.recyclerview_btn)
    RecyclerView recyclerviewBtn;
    private OrderPresenter orderPresenter;
    private int orderId;
    /**
     * 是否倒计时
     */
    private boolean isTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
           /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("订单详情");
        orderId = getIntent().getIntExtra("orderId", 0);
        orderPresenter.getOrderDetail(orderId);
    }

    @Override
    protected OrderPresenter initPresenter() {
        return orderPresenter = new OrderPresenter();
    }

    @Override
    public void showDatas(List<OrderEntity> datas) {

    }

    @Override
    public void appendDatas(List<OrderEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

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

    /**
     * 获取订单详情信息
     *
     * @param orderDetailEntity
     */
    @Override
    public void getOrderDetail(OrderDetailEntity orderDetailEntity) {
        //显示订单状态的图标
        // 10:待支付;20:待接单;21:已接单;30:备货中;31:已备货;40:待取餐;41:已取餐;50:待配送;51:配送中;52:已送达;80:已完成;90:已取消
        if (Constants.VALUE_10.equals(orderDetailEntity.getStatus().getStatus())) {
            //待支付
            imgMoney.setImageResource(R.mipmap.ic_qian);
        } else if (Constants.VALUE_20.equals(orderDetailEntity.getStatus().getStatus()) || Constants.VALUE_21.equals(orderDetailEntity.getStatus().getStatus())
                || Constants.VALUE_30.equals(orderDetailEntity.getStatus().getStatus()) || Constants.VALUE_31.equals(orderDetailEntity.getStatus().getStatus())) {
            //待接单，已接单,备货中,已备货
            imgMoney.setImageResource(R.mipmap.ic_shangjia);
        } else if (Constants.VALUE_40.equals(orderDetailEntity.getStatus().getStatus()) || Constants.VALUE_41.equals(orderDetailEntity.getStatus().getStatus())) {
            //待取餐，已取餐（自提模式）
            imgMoney.setImageResource(R.mipmap.ic_ziti);
        } else if (Constants.VALUE_50.equals(orderDetailEntity.getStatus().getStatus()) || Constants.VALUE_51.equals(orderDetailEntity.getStatus().getStatus()) || Constants.VALUE_52.equals(orderDetailEntity.getStatus().getStatus())) {
            //待配送，配送中，已送达（外送模式）
            imgMoney.setImageResource(R.mipmap.order_peisong);
        } else if (Constants.VALUE_80.equals(orderDetailEntity.getStatus().getStatus())) {
            //已完成
            imgMoney.setImageResource(R.mipmap.order_success);
        } else if (Constants.VALUE_90.equals(orderDetailEntity.getStatus().getStatus())) {
            //已取消
            imgMoney.setImageResource(R.mipmap.order_cancel);
        }
        txDistribution.setText(orderDetailEntity.getStatus().getName());
        //判断是普通信息还是倒计时信息 1:普通信息;2:计时信息
        if (Constants.VALUE_1.equals(orderDetailEntity.getMessage().getType())) {
            isTimer = false;
            txTimer.setVisibility(View.GONE);
            txTimeMessage.setVisibility(View.GONE);
            txDistributionMsg.setText(orderDetailEntity.getMessage().getMessage());
        } else {
            isTimer = true;
            txTimeMessage.setVisibility(View.VISIBLE);
            txTimer.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(orderDetailEntity.getMessage().getData())) {
                txTimer.init(OrderDetailActivity.this);
                txTimer.startTiming(TimeUtil.getSecond(orderDetailEntity.getMessage().getData()));
                txTimer.setOnTimerListener(new TimerView.OnTimerListener() {
                    @Override
                    public void timeOver() {
                        txTimer.shopTiming();
                        isTimer = false;
                        //订单倒计时完成刷新
                        orderPresenter.getOrderDetail(orderId);
                    }
                });
            }

            txDistributionMsg.setText(orderDetailEntity.getMessage().getMessage().substring(4, orderDetailEntity.getMessage().getMessage().length()));
        }
        //自提还是外送标签
        txTakeAway.setVisibility(View.VISIBLE);
        txTakeAway.setText(orderDetailEntity.getDeliver().getDeliverType());
        //判断是外送还是自提 外送显示购买人地址 自提显示商铺
        if (orderDetailEntity.getDeliver().isTakeaway()) {
            //外送
            if (null != orderDetailEntity.getDeliver() && null != orderDetailEntity.getDeliver().getAddress()) {
                //收货人姓名
                txName.setText(orderDetailEntity.getDeliver().getAddress().getAddressee());
                //收货人电话
                txPhone.setText("电话:" + orderDetailEntity.getDeliver().getAddress().getPhone());
                //收货人地址
                txAddress.setText("地址:" + orderDetailEntity.getDeliver().getAddress().getAddress());
                //家  公司等信息
                txAddressFor.setVisibility(View.VISIBLE);
                txAddressFor.setText(orderDetailEntity.getDeliver().getAddress().getTag());
            }
        } else {
            txAddressFor.setVisibility(View.GONE);
            //自提
            if (null != orderDetailEntity.getDeliver() && null != orderDetailEntity.getDeliver().getShop()) {
                //店铺名称
                txName.setText(orderDetailEntity.getDeliver().getShop().getTitle());
                //营业时间
                txPhone.setText("营业时间:" + orderDetailEntity.getDeliver().getShop().getHours());
                //店铺地址
                txAddress.setText("地址:" + orderDetailEntity.getDeliver().getShop().getAddress());
            }

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
        OrderDetailProvider orderDetailProvider = new OrderDetailProvider(this);
        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(orderDetailEntity.getItems());
        mPatientsAdapter.register(OrderDetailEntity.ItemsBean.class, orderDetailProvider);
        recyclerviewGoods.setAdapter(mPatientsAdapter);
        //商品总价
        txGoodsAllPrice.setText("￥" + orderDetailEntity.getGoodsAmount());
//        //运费优惠说明
//        txConcDescription.setText("(" + orderDetailEntity.getDeliver().getConcDescription() + ")");
        //运费原价
//        txDeliverPrice.setText("￥" + orderDetailEntity.getDeliver().getPrice());
//        txDeliverPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        //运费优惠价格
        txDeliverConcPrice.setText("￥" + orderDetailEntity.getDeliver().getConcPrice());
        //优惠券使用情况
        if (null != orderDetailEntity.getCoupon()) {
            if (orderDetailEntity.getCoupon().isUsed()) {
                rlCoupon.setVisibility(View.VISIBLE);
                txGoodsCoupon.setText("- ￥" + orderDetailEntity.getCoupon().getDeduction());
            } else {
                rlCoupon.setVisibility(View.GONE);
            }

        } else {
            rlCoupon.setVisibility(View.GONE);
        }
        //积分使用情况
        if (null != orderDetailEntity.getPoints()) {
            if (orderDetailEntity.getPoints().isUsed()) {
                rlPoints.setVisibility(View.VISIBLE);
                txPoints.setText("- ￥" + orderDetailEntity.getPoints().getDeduction());
            } else {
                rlPoints.setVisibility(View.GONE);
            }
        } else {
            rlPoints.setVisibility(View.GONE);
        }
        //多少件商品
        txCountPay.setText("共" + orderDetailEntity.getGoodsQuantity() + "件 实付:");
        //实付金额
        txOrderPrice.setText("￥" + orderDetailEntity.getPayment().getPayment());
        //配送员信息
        if (TextUtils.isEmpty(orderDetailEntity.getDeliver().getCourier())) {
            rlCourier.setVisibility(View.GONE);
        } else {
            rlCourier.setVisibility(View.VISIBLE);
            txCourier.setText(orderDetailEntity.getDeliver().getCourier());
            imgPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + orderDetailEntity.getDeliver().getCourierMobile());
                    intent.setData(data);
                    startActivity(intent);
                }
            });
        }
        //订单编号
        txOrderNumber.setText("订单编号：" + orderDetailEntity.getId());
        txCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) OrderDetailActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", orderDetailEntity.getId() + ""); //Label是任意文字标签
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                showLongToast("已复制内容到剪切板");
            }
        });
        //订单日志
        if (NotNull.isNotNull(orderDetailEntity.getJournals())) {
            String orderLog = "";
            for (int i = 0; i < orderDetailEntity.getJournals().size(); i++) {
                if (i == orderDetailEntity.getJournals().size() - 1) {
                    orderLog += orderDetailEntity.getJournals().get(i);
                } else {
                    orderLog += orderDetailEntity.getJournals().get(i) + "\n";
                }

            }
            txLog.setText(orderLog);
        }

        //底部按钮
        //订单可操作列表
        if (null != orderDetailEntity.getActions()) {
            rlBottom.setVisibility(View.VISIBLE);
            //添加横向按钮
            LinearLayoutManager btnLayoutManager = new LinearLayoutManager(OrderDetailActivity.this, LinearLayoutManager.HORIZONTAL, false) {
                @Override
                public boolean canScrollHorizontally() {
                    //不能滑动
                    return false;
                }
            };
            recyclerviewBtn.setLayoutManager(btnLayoutManager);
            OrderDetailActionsAdapter orderDetailActionsAdapter = new OrderDetailActionsAdapter(OrderDetailActivity.this, orderDetailEntity.getActions());
            recyclerviewBtn.setAdapter(orderDetailActionsAdapter);
            orderDetailActionsAdapter.setOnClickListener(new OrderDetailActionsAdapter.OnClickListener() {
                @Override
                public void onItemClick(OrderDetailEntity.ActionsBean actionsBean) {
                    //1:去支付;2:取餐码;9:取消订单;11:去评价;12:开发票;19:再来一单
                }


            });
        } else {
            rlBottom.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
    /**
     * 评论弹窗
     */
    public void showDiscussDialog() {
        List<String> getDiscuss = new ArrayList<>();
        //自提显示店铺  外卖显示配送信息
        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.ActionSheetDialogStyle).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_dialog_discuss);
        window.setGravity(Gravity.CENTER);
        TextView tx_shop = window.findViewById(R.id.tx_shop);
        TextView tx_phone = window.findViewById(R.id.tx_phone);
        TextView tx_finishTime = window.findViewById(R.id.tx_finishTime);
        LinearLayout ll_cha = window.findViewById(R.id.ll_cha);
        ImageView img_cha = window.findViewById(R.id.img_cha);
        TextView tx_cha = window.findViewById(R.id.tx_cha);
        LinearLayout ll_yiBan = window.findViewById(R.id.ll_yiBan);
        ImageView img_yiBan = window.findViewById(R.id.img_yiBan);
        TextView tx_yiBan = window.findViewById(R.id.tx_yiBan);
        LinearLayout ll_hao = window.findViewById(R.id.ll_hao);
        ImageView img_hao = window.findViewById(R.id.img_hao);
        TextView tx_hao = window.findViewById(R.id.tx_hao);
        Button btn_commit = window.findViewById(R.id.btn_commit);
        RecyclerView recyclerview = window.findViewById(R.id.recyclerview);
        List<DiscussEntity> discussEntityList = new ArrayList<>();
        discussEntityList.add(new DiscussEntity().setContent("口味棒"));
        discussEntityList.add(new DiscussEntity().setContent("服务态度好"));
        discussEntityList.add(new DiscussEntity().setContent("配送及时"));
        discussEntityList.add(new DiscussEntity().setContent("某某内容"));
        recyclerview.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();

        DiscussProvider discussProvider = new DiscussProvider();
        //点击监听
        discussProvider.setOnClickListener(new DiscussProvider.OnClickListener() {
            @Override
            public void onItemClick(boolean isCheck, DiscussEntity discussEntity, int position) {
                //如果为true要加入到集合，反之从集合移除
                if (isCheck) {
                    getDiscuss.add(discussEntity.getContent());
                } else {
                    getDiscuss.remove(discussEntity.getContent());
                }
                discussProvider.setSelect(position);

            }
        });

        mPatientsAdapter.register(DiscussEntity.class, discussProvider);

        mPatientsAdapter.setItems(discussEntityList);
        recyclerview.setAdapter(mPatientsAdapter);
        ll_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tx_cha.setTextColor(Color.parseColor("#E6BD0B"));
                tx_yiBan.setTextColor(Color.parseColor("#A0A6A7"));
                tx_hao.setTextColor(Color.parseColor("#A0A6A7"));
            }
        });
        ll_yiBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tx_cha.setTextColor(Color.parseColor("#A0A6A7"));
                tx_yiBan.setTextColor(Color.parseColor("#E6BD0B"));
                tx_hao.setTextColor(Color.parseColor("#A0A6A7"));
            }
        });
        ll_hao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tx_cha.setTextColor(Color.parseColor("#A0A6A7"));
                tx_yiBan.setTextColor(Color.parseColor("#A0A6A7"));
                tx_hao.setTextColor(Color.parseColor("#E6BD0B"));
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLongToast(getDiscuss.toString());
                dlg.dismiss();
            }
        });
        window.findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isTimer) {
            txTimer.shopTiming();
        }
    }
}
