package vip.hengnai.wine.ui.mine.beer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.BeerEntity;
import vip.hengnai.wine.view.SuperTextView;

/**
 * 啤酒不凡provider
 *
 * @author Hugh
 */
public class BeerProvider extends ItemViewProvider<BeerEntity, BeerProvider.ProviderHolder> {


    private MultiTypeAdapter mMultiTypeAdapter;
    private Context mContext;

    public BeerProvider() {
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {

        /**
         * item点击事件
         *
         * @param beerEntity
         */
        void onItemClick(BeerEntity beerEntity);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.beer_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull BeerEntity beerEntity) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOnClickListener.onItemClick(beerEntity);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_beer)
        SuperTextView txBeer;
        @BindView(R.id.img_beer)
        ImageView imgBeer;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
