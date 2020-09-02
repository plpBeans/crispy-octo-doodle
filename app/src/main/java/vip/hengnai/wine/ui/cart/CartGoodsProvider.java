package vip.hengnai.wine.ui.cart;

import android.content.Context;
import android.graphics.Paint;
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
import vip.hengnai.wine.entity.CartEntity;
import vip.hengnai.wine.util.glide.MyGlideModule;
import vip.hengnai.wine.view.AddDeleteView;

/**
 * 购物车provider
 *
 * @author Hugh
 */
public class CartGoodsProvider extends ItemViewProvider<CartEntity.GroupsBean.ItemsBean, CartGoodsProvider.ProviderHolder> {



    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();
    private Context mContext;
    public CartGoodsProvider(Context mContext) {
        this.mContext = mContext;
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 选中事件
         * @param adapter
         * @param itemsBean
         * @param goodsPosition
         */
        void onItemChoose( RecyclerView.Adapter adapter,CartEntity.GroupsBean.ItemsBean itemsBean, int goodsPosition);

        /**
         * 修改商品数量
         * @param adapter
         * @param view
         * @param itemsBean
         * @param isAdd
         * @param goodsPosition
         */
        void modifyCount(RecyclerView.Adapter adapter,AddDeleteView view,CartEntity.GroupsBean.ItemsBean itemsBean, boolean isAdd, int goodsPosition);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.cart_goods_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull CartEntity.GroupsBean.ItemsBean itemsBean) {
        if(itemsBean.getImage().equals(((String)holder.itemView.getTag()))){

        }else{
            holder.itemView.setTag(itemsBean.getImage());
            MyGlideModule.loadImage(mContext, holder.imgGoods, itemsBean.getImage(), R.mipmap.icon_goods);
        }

        holder.txGoodsName.setText(itemsBean.getGoodsName());
        holder.txRealPrice.setText("￥" + itemsBean.getConcPrice());
        holder.txPrice.setText("￥" + itemsBean.getPrice());
        holder.txSpecs.setText(itemsBean.getSpecName());
        holder.txPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        //数量
        holder.addCount.setNumber(itemsBean.getQuantity());
        holder.imgChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemChoose(getAdapter(),itemsBean, getPosition(holder));
            }
        });

        if(getPosition(holder)==getAdapter().getItemCount()-1){
            holder.viewLine.setVisibility(View.GONE);
        }else{
            holder.viewLine.setVisibility(View.VISIBLE);
        }
        //根据返回值判断给true还是false
        if (itemsBean.isChoose()) {
            holder.imgChoose.setImageResource(R.mipmap.bg_xuan);
        } else {
            holder.imgChoose.setImageResource(R.mipmap.bg_moren);
        }
        holder.addCount.setOnAddDelClickListener(new AddDeleteView.OnAddDelClickListener() {
            @Override
            public void onAddClick(View v) {
                mOnClickListener.modifyCount(getAdapter(),holder.addCount,itemsBean, true,getPosition(holder));
            }

            @Override
            public void onDelClick(View v) {
                mOnClickListener.modifyCount(getAdapter(),holder.addCount,itemsBean, false,getPosition(holder));
            }
        });
    }


    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_choose)
        ImageView imgChoose;
        @BindView(R.id.img_goods)
        ImageView imgGoods;
        @BindView(R.id.tx_goodsName)
        TextView txGoodsName;
        @BindView(R.id.tx_specs)
        TextView txSpecs;
        @BindView(R.id.tx_realPrice)
        TextView txRealPrice;
        @BindView(R.id.tx_Price)
        TextView txPrice;
        @BindView(R.id.rl_goods)
        LinearLayout rlGoods;
        @BindView(R.id.addCount)
        AddDeleteView addCount;
        @BindView(R.id.view_line)
        View viewLine;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
