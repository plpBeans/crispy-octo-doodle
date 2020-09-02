package vip.hengnai.wine.ui.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.CartEntity;
import vip.hengnai.wine.view.AddDeleteView;

/**
 * 购物车provider
 *
 * @author Hugh
 */
public class CartProvider extends ItemViewProvider<CartEntity.GroupsBean, CartProvider.ProviderHolder> {



    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();

    private Context mContext;

    public CartProvider(Context mContext) {
        this.mContext = mContext;
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 点击事件
         *
         * @param groupsBean
         */
        void onItemClick(CartEntity.GroupsBean groupsBean);

        /**
         * 修改商品数量
         * @param txAllPrice
         * @param txConcDescription
         * @param adapter
         * @param groupsBean
         * @param itemsBean
         * @param isAdd
         * @param goodsPosition
         */
        void modifyCount(TextView txAllPrice,TextView txConcDescription,RecyclerView.Adapter adapter,CartEntity.GroupsBean groupsBean,CartEntity.GroupsBean.ItemsBean itemsBean, boolean isAdd,int goodsPosition);

        /**
         * 选中事件
         * @param txAllPrice
         * @param txConcDescription
         * @param adapter
         * @param groupsBean
         * @param itemsBean
         * @param goodsPosition
         */
        void onItemChoose(TextView txAllPrice,TextView txConcDescription, RecyclerView.Adapter adapter,CartEntity.GroupsBean groupsBean,CartEntity.GroupsBean.ItemsBean itemsBean, int goodsPosition);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.shopping_cart_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull CartEntity.GroupsBean groupsBean) {
        holder.txGroupName.setText(groupsBean.getGroupName());
        holder.txDeliverType.setText(groupsBean.getDeliverType());
        holder.txAllPrice.setText("￥" + groupsBean.getAmount());
        //是否有优惠
        if(groupsBean.isPreferential()){
            holder.txConcDescription.setVisibility(View.VISIBLE);
            holder.txConcDescription.setText(groupsBean.getConcDescription());

        }else{
            holder.txConcDescription.setVisibility(View.GONE);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        holder.addRecyclerview.setLayoutManager(layoutManager);
        CartGoodsProvider cartGoodsProvider = new CartGoodsProvider(mContext);
        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(groupsBean.getItems());
        mPatientsAdapter.register(CartEntity.GroupsBean.ItemsBean.class, cartGoodsProvider);
        holder.addRecyclerview.setAdapter(mPatientsAdapter);
        /**
         * 防止addRecyclerview刷新闪一下
         */
        ((SimpleItemAnimator) holder.addRecyclerview.getItemAnimator()).setSupportsChangeAnimations(false);

        cartGoodsProvider.setOnClickListener(new CartGoodsProvider.OnClickListener() {
            @Override
            public void onItemChoose(RecyclerView.Adapter adapter, CartEntity.GroupsBean.ItemsBean itemsBean, int goodsPosition) {
                mOnClickListener.onItemChoose(holder.txAllPrice,holder.txConcDescription,adapter,groupsBean,itemsBean, goodsPosition);
            }

            @Override
            public void modifyCount(RecyclerView.Adapter adapter, AddDeleteView view, CartEntity.GroupsBean.ItemsBean itemsBean, boolean isAdd, int goodsPosition) {
                mOnClickListener.modifyCount(holder.txAllPrice,holder.txConcDescription,adapter,groupsBean,itemsBean, isAdd,goodsPosition);
            }



        });
        holder.btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(groupsBean);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_choose)
        ImageView imgChoose;
        @BindView(R.id.tx_groupName)
        TextView txGroupName;
        @BindView(R.id.tx_deliverType)
        TextView txDeliverType;
        @BindView(R.id.add_recyclerview)
        RecyclerView addRecyclerview;
        @BindView(R.id.tx_allPrice)
        TextView txAllPrice;
        @BindView(R.id.tx_concDescription)
        TextView txConcDescription;
        @BindView(R.id.btn_bill)
        Button btnBill;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
