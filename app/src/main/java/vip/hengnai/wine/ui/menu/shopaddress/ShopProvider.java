package vip.hengnai.wine.ui.menu.shopaddress;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.ShopEntity;

/**
 * 商家provider
 *
 * @author Hugh
 */
public class ShopProvider extends ItemViewProvider<ShopEntity, ShopProvider.ProviderHolder> {
    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 点击事件item
         *
         * @param shopEntity
         */
        void onItemClick(ShopEntity shopEntity);
        /**
         * 点击事件item
         *
         * @param shopEntity
         */
        void onDetailClick(ShopEntity shopEntity);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.layout_shop_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull ShopEntity shopEntity) {
        holder.txShopName.setText(shopEntity.getTitle());
        holder.txTime.setText(shopEntity.getHours());
        holder.txAddress.setText(shopEntity.getAddress());
        holder.txDistance.setText(shopEntity.getDistance());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(shopEntity);
            }
        });
        holder.txShopDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onDetailClick(shopEntity);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_shopName)
        AppCompatTextView txShopName;
        @BindView(R.id.img_time)
        ImageView imgTime;
        @BindView(R.id.tx_time)
        AppCompatTextView txTime;
        @BindView(R.id.tx_distance)
        AppCompatTextView txDistance;
        @BindView(R.id.img_address)
        ImageView imgAddress;
        @BindView(R.id.tx_address)
        AppCompatTextView txAddress;
        @BindView(R.id.tx_shop_detail)
        AppCompatTextView txShopDetail;
        @BindView(R.id.ll_shop_detail)
        LinearLayout llShopDetail;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
