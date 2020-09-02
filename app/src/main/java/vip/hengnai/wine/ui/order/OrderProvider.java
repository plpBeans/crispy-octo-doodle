package vip.hengnai.wine.ui.order;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.util.TimeUtil;
import vip.hengnai.wine.util.glide.MyGlideModule;
import vip.hengnai.wine.view.TimerView;

/**
 * 订单provider
 *
 * @author Hugh
 */
public class OrderProvider extends ItemViewProvider<OrderEntity, OrderProvider.ProviderHolder> {



    private Context mContext;
    private int width;

    public OrderProvider(Context mContext, int width) {
        this.mContext = mContext;
        this.width = width;
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 点击事件
         * 1:去支付;2:取餐码;9:取消订单;11:去评价;12:开发票;19:再来一单
         *
         * @param orderEntity
         * @param type
         */
        void onBtnClick(OrderEntity orderEntity, String type);

        /**
         * item点击事件
         *
         * @param orderEntity
         */
        void onItemClick(OrderEntity orderEntity);

        /**
         * 时间到需要刷新列表
         */
        void onOrderRefresh();
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.order_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull OrderEntity orderEntity) {
        //自提/外卖/直邮
        holder.txType.setText(orderEntity.getDeliverType());
        //店铺名称显示
        holder.txOrderAddress.setText(orderEntity.getAddress());
        //订单状态
        holder.txOrderType.setText(orderEntity.getStatus());
        //下单时间
        holder.txOrderTime.setText(orderEntity.getOrderTime());
        //共多少个商品
        holder.txCount.setText("共" + orderEntity.getGoodsQuantity() + "件商品");
        //支付金额
        holder.txRealPrice.setText("￥" + orderEntity.getPayment());
        //订单可操作列表
        if (null != orderEntity.getActions()) {
            holder.recyclerviewBtn.setVisibility(View.VISIBLE);
            //添加横向按钮
            LinearLayoutManager btnLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false) {
                @Override
                public boolean canScrollHorizontally() {
                    //不能滑动
                    return false;
                }
            };
            holder.recyclerviewBtn.setLayoutManager(btnLayoutManager);
            OrderActionsAdapter orderActionsAdapter = new OrderActionsAdapter(mContext, orderEntity.getActions());
            holder.recyclerviewBtn.setAdapter(orderActionsAdapter);
            orderActionsAdapter.setOnClickListener(new OrderActionsAdapter.OnClickListener() {
                @Override
                public void onItemClick(OrderEntity.ActionsBean actionsBean) {
                    //支付
                    mOnClickListener.onBtnClick(orderEntity, actionsBean.getType());
                }


            });
        } else {
            holder.recyclerviewBtn.setVisibility(View.GONE);
        }
        //显示右上角订单状态颜色 2警告色 9异常颜色
        if (orderEntity.getState() == Constants.INT_2) {
            holder.txOrderType.setTextColor(Color.parseColor("#ff0000"));
        } else if (orderEntity.getState() == Constants.INT_9) {
            holder.txOrderType.setTextColor(Color.parseColor("#ff8a00"));
        } else {
            //状态1且不活跃
            if (orderEntity.isFinished()) {
                holder.txOrderType.setTextColor(Color.parseColor("#999999"));
            } else {
                //状态1且活跃
                holder.txOrderType.setTextColor(Color.parseColor("#47c3fd"));
            }
        }
        //判断订单是否活跃
        //非活跃订单（已完成）
        if (orderEntity.isFinished()) {
            //非活跃订单不显示倒计时
            holder.llSubscribe.setVisibility(View.GONE);
            //非活跃订单
            holder.txGoodsName.setVisibility(View.VISIBLE);
            String txName = "";
            for (int i = 0; i < orderEntity.getItems().size(); i++) {
                if (0 == i) {
                    txName = orderEntity.getItems().get(i).getGoodsName();
                } else {
                    txName += "  " + orderEntity.getItems().get(i).getGoodsName();
                    txName += "  " + orderEntity.getItems().get(i).getGoodsName();
                }

            }
            holder.txGoodsName.setText(txName);

        } else {
            //判断是否是预约的订单,预约要显示预约时间
            if (orderEntity.getBooking().isBooking()) {
                holder.llSubscribe.setVisibility(View.VISIBLE);
                if (null != orderEntity.getMessage()) {
                    //type为2待支付的时候是倒计时
                    if (Constants.VALUE_2.equals(orderEntity.getMessage().getType())) {
                        holder.txPlease.setVisibility(View.VISIBLE);
                        holder.timer.setVisibility(View.VISIBLE);
                        holder.timer.init(mContext);
                        holder.timer.startTiming(TimeUtil.getSecond(orderEntity.getMessage().getData()));
//                        holder.timer.startTiming(70);
                        holder.timer.setOnTimerListener(new TimerView.OnTimerListener() {
                            @Override
                            public void timeOver() {
                                holder.timer.shopTiming();
                                //订单倒计时完成刷新
                                mOnClickListener.onOrderRefresh();
//                                holder.timer.setVisibility(View.GONE);
                            }
                        });
                        holder.txSubscribe.setText(orderEntity.getMessage().getMessage().substring(4, orderEntity.getMessage().getMessage().length()));
                    } else {
                        holder.timer.setVisibility(View.GONE);
                        holder.txPlease.setVisibility(View.GONE);
                        holder.txSubscribe.setText(orderEntity.getMessage().getMessage());
                    }
                } else {
                    holder.txSubscribe.setText("");
                }
            } else {
                holder.llSubscribe.setVisibility(View.GONE);
            }
            //活跃订单
            holder.recyclerviewMoreGoods.setVisibility(View.VISIBLE);
            holder.txGoodsName.setVisibility(View.GONE);
//            ViewGroup.LayoutParams lp = holder.llGoods.getLayoutParams();
//            lp.height = ((width - Constants.dip2px(mContext, 120)) / 5) + Constants.dip2px(mContext, 30);
//            holder.llGoods.setLayoutParams(lp);
            //添加横向图片
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            holder.recyclerviewMoreGoods.setLayoutManager(linearLayoutManager);

            OrderGoodsAdapter orderGoodsAdapter = new OrderGoodsAdapter(mContext, (width - Constants.dip2px(mContext, 120)) / 5, orderEntity.getItems());
            holder.recyclerviewMoreGoods.setAdapter(orderGoodsAdapter);
            orderGoodsAdapter.setOnClickListener(new OrderGoodsAdapter.OnClickListener() {
                @Override
                public void onItemClick() {
                    mOnClickListener.onItemClick(orderEntity);
                }
            });
//            //根据商品数量不同显示不同的布局
//            if (orderEntity.getItems().size() == Constants.INT_1) {
//                holder.recyclerviewMoreGoods.setVisibility(View.GONE);
//                holder.rlSingleGoods.setVisibility(View.VISIBLE);
//                //修改view大小
//                Constants.setViewLayoutParams(holder.imgGoods, (width - Constants.dip2px(mContext, 120)) / 5);
//                MyGlideModule.loadImage(mContext, holder.imgGoods, orderEntity.getItems().get(0).getImage(), R.mipmap.icon_goods);
//                holder.txSingleGoodsName.setText(orderEntity.getItems().get(0).getGoodsName());
//                holder.txSingleGoodsSpec.setText(orderEntity.getItems().get(0).getSpecName());
//                holder.txSingleGoodsCount.setText("x" + orderEntity.getItems().get(0).getQuantity());
//            } else {
//                //多商品
//                holder.rlSingleGoods.setVisibility(View.GONE);
//                holder.recyclerviewMoreGoods.setVisibility(View.VISIBLE);
//                //图片列表布局
//                //设置商品信息view高度
//                ViewGroup.LayoutParams lp = holder.llGoods.getLayoutParams();
//                lp.height = ((width - Constants.dip2px(mContext, 120)) / 5) + Constants.dip2px(mContext, 30);
//                holder.llGoods.setLayoutParams(lp);
//                //添加横向图片
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false) {
//                    @Override
//                    public boolean canScrollHorizontally() {
//                        //不能滑动
//                        return false;
//                    }
//                };
//                holder.recyclerviewMoreGoods.setLayoutManager(linearLayoutManager);
//                List<String> url = new ArrayList<>();
//                for (int i = 0; i < orderEntity.getItems().size(); i++) {
//                    if (i <= Constants.INT_4) {
//                        url.add(orderEntity.getItems().get(i).getImage());
//
//                    }
//                }
//                OrderGoodsImageAdapter orderGoodsImageAdapter = new OrderGoodsImageAdapter(mContext, (width - Constants.dip2px(mContext, 120)) / 5, url);
//                holder.recyclerviewMoreGoods.setAdapter(orderGoodsImageAdapter);
//                orderGoodsImageAdapter.setOnClickListener(new OrderGoodsImageAdapter.OnClickListener() {
//                    @Override
//                    public void onItemClick() {
//                        mOnClickListener.onItemClick(orderEntity);
//                    }
//                });
//
//            }

        }

//        holder.btnBill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.btnBill.setText("去评价");
//                holder.btnBill.setBackgroundResource(R.mipmap.btn_anniu);
//                holder.btnBill.setTextColor(Color.parseColor("#606161"));
//                holder.btnAgain.setVisibility(View.VISIBLE);
//                //支付
//                mOnClickListener.onBtnClick(orderEntity, "1");
//            }
//        });
//        holder.btnAgain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //支付
//                mOnClickListener.onBtnClick(orderEntity, "3");
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(orderEntity);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_type)
        TextView txType;
        @BindView(R.id.tx_order_address)
        TextView txOrderAddress;
        @BindView(R.id.tx_orderType)
        TextView txOrderType;
        @BindView(R.id.img_subscribe)
        ImageView imgSubscribe;
        @BindView(R.id.tx_please)
        TextView txPlease;
        @BindView(R.id.timer)
        TimerView timer;
        @BindView(R.id.tx_subscribe)
        TextView txSubscribe;
        @BindView(R.id.ll_subscribe)
        LinearLayout llSubscribe;
        @BindView(R.id.tx_goodsName)
        TextView txGoodsName;
        @BindView(R.id.img_goods)
        ImageView imgGoods;
        @BindView(R.id.tx_singleGoodsName)
        TextView txSingleGoodsName;
        @BindView(R.id.tx_singleGoodsSpec)
        TextView txSingleGoodsSpec;
        @BindView(R.id.tx_singleGoodsCount)
        TextView txSingleGoodsCount;
        @BindView(R.id.rl_singleGoods)
        RelativeLayout rlSingleGoods;
        @BindView(R.id.recyclerview_moreGoods)
        RecyclerView recyclerviewMoreGoods;
        @BindView(R.id.ll_goods)
        LinearLayout llGoods;
        @BindView(R.id.tx_orderTime)
        TextView txOrderTime;
        @BindView(R.id.tx_count)
        TextView txCount;
        @BindView(R.id.tx_realPrice)
        TextView txRealPrice;
        @BindView(R.id.recyclerview_btn)
        RecyclerView recyclerviewBtn;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
