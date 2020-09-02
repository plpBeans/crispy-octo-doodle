package vip.hengnai.wine.ui.order.confirm;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderConfirmEntity;
import vip.hengnai.wine.util.glide.MyGlideModule;

/**
 * 订单确认provider
 *
 * @author Hugh
 */
public class ConfirmProvider extends ItemViewProvider<OrderConfirmEntity.ItemsBean, ConfirmProvider.ProviderHolder> {

    private Context mContext;

    public ConfirmProvider(Context mContext) {
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
         * @param itemsBean
         */
        void onItemClick(OrderConfirmEntity.ItemsBean itemsBean);

    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.item_confirm, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull OrderConfirmEntity.ItemsBean itemsBean) {
        MyGlideModule.loadImage(mContext, holder.imgGoods, itemsBean.getImage(), R.mipmap.icon_goods);
        //商品名称
        holder.txGoodsName.setText(itemsBean.getGoodsName());
        //商品优惠的价格
        holder.txGoodsPrice.setText("￥" + itemsBean.getConcPrice());
        //商品原价
        holder.txOldPrice.setText("￥" +itemsBean.getPrice());
        holder.txOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        //商品规格
        holder.txGoodsSpecs.setText(itemsBean.getSpecName());
        //商品数量
        holder.txGoodsCount.setText("x" + itemsBean.getQuantity());
    }


    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_goods)
        ImageView imgGoods;
        @BindView(R.id.tx_goodsName)
        TextView txGoodsName;
        @BindView(R.id.tx_goodsPrice)
        TextView txGoodsPrice;
        @BindView(R.id.tx_oldPrice)
        TextView txOldPrice;
        @BindView(R.id.tx_goodsSpecs)
        TextView txGoodsSpecs;
        @BindView(R.id.tx_goodsCount)
        TextView txGoodsCount;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
