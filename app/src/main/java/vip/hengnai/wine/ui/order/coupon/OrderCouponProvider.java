package vip.hengnai.wine.ui.order.coupon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderCouponEntity;
import vip.hengnai.wine.util.NotNull;

/**
 * 订单可用优惠券provider
 *
 * @author Hugh
 */
public class OrderCouponProvider extends ItemViewProvider<OrderCouponEntity, OrderCouponProvider.ProviderHolder> {



    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();
    private Context mContext;

    public OrderCouponProvider(Context mContext) {
        this.mContext = mContext;
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {

        /**
         * item点击事件
         *
         * @param orderCouponEntity
         */
        void onItemClick(OrderCouponEntity orderCouponEntity);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.order_coupon_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull OrderCouponEntity orderCouponEntity) {
        holder.txName.setText(orderCouponEntity.getName());
        holder.txDiscount.setText(orderCouponEntity.getDiscount());
        holder.txDate.setText("有效期至" + orderCouponEntity.getExpiration());
        holder.txMoney.setText("可抵扣¥" + orderCouponEntity.getDeduction());
        holder.txRule.setText(orderCouponEntity.getRule());
        if(NotNull.isNotNull(orderCouponEntity.getRemaining())){
            holder.txDay.setVisibility(View.VISIBLE);
            holder.txDay.setText(orderCouponEntity.getRemaining());
        }else{
            holder.txDay.setVisibility(View.GONE);
        }
        if (mSelectArray.get(getPosition(holder), false)) {
            holder.txRule.setVisibility(View.VISIBLE);
            holder.imgDetail.setRotation(180);
        } else {
            holder.txRule.setVisibility(View.GONE);
            holder.imgDetail.setRotation(0);
        }
        holder.llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSelectArray.get(getPosition(holder), false)) {
                    mSelectArray.put(getPosition(holder), false);
                } else {
                    mSelectArray.put(getPosition(holder), true);
                }
                getAdapter().notifyItemChanged(getPosition(holder));
            }
        });

        holder.txChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(orderCouponEntity);
            }
        });

    }

    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_name)
        TextView txName;
        @BindView(R.id.tx_day)
        TextView txDay;
        @BindView(R.id.tx_discount)
        TextView txDiscount;
        @BindView(R.id.tx_unit)
        TextView txUnit;
        @BindView(R.id.tx_date)
        TextView txDate;
        @BindView(R.id.tx_money)
        TextView txMoney;
        @BindView(R.id.tx_guize)
        TextView txGuize;
        @BindView(R.id.img_detail)
        ImageView imgDetail;
        @BindView(R.id.ll_detail)
        LinearLayout llDetail;
        @BindView(R.id.tx_choose)
        TextView txChoose;
        @BindView(R.id.tx_rule)
        TextView txRule;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
