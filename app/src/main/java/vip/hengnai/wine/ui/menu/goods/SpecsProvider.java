package vip.hengnai.wine.ui.menu.goods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.GoodsDetailEntity;
import vip.hengnai.wine.entity.SpecsEntity;

/**
 * 商品规格provider
 *
 * @author Hugh
 */
public class SpecsProvider extends ItemViewProvider<GoodsDetailEntity.SpecsBean, SpecsProvider.Holder> {

    private OnClickListener mOnClickListener;

    private Drawable mMale;
    private Drawable mFemale;
    private Context context;
    private MultiTypeAdapter mPatientsAdapter;
    private int defaultPosition = -1;

    public SpecsProvider(Context context) {
        this.context = context;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onItemClick(int position, GoodsDetailEntity.SpecsBean specsEntity);
    }

    /**
     * 获取点击的位置
     */

    public void setSelect(int position) {
        this.defaultPosition = position;
        getAdapter().notifyDataSetChanged();
    }


    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(inflater.inflate(R.layout.good_item_specs, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final Holder holder, @NonNull final GoodsDetailEntity.SpecsBean specsEntity) {
        if (-1 == defaultPosition) {
            if(0==getPosition(holder)){
                holder.txSpecs.setTextColor(ContextCompat.getColor(context, R.color.white));
                holder.txSpecs.setBackgroundResource(R.drawable.bg_circle_gray_boder);
            }else{
                holder.txSpecs.setTextColor(ContextCompat.getColor(context, R.color.text_gray6));
                holder.txSpecs.setBackgroundResource(R.drawable.bg_circle_white_boder);
            }

        } else {
                /*点的位置跟点击的textview位置一样设置点击后的不同样式*/
            if (defaultPosition == getPosition(holder)) {
                 /*设置选中的样式*/
                holder.txSpecs.setTextColor(ContextCompat.getColor(context, R.color.white));
                holder.txSpecs.setBackgroundResource(R.drawable.bg_circle_gray_boder);
            } else {
                /*其他的变为未选择状态
                 *设置未选中的样式
                */
                holder.txSpecs.setTextColor(ContextCompat.getColor(context, R.color.text_gray6));
                holder.txSpecs.setBackgroundResource(R.drawable.bg_circle_white_boder);
            }
        }
        holder.txSpecs.setText(specsEntity.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnClickListener) {
                    mOnClickListener.onItemClick(getPosition(holder), specsEntity);
                }
            }
        });

    }


    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_specs)
        TextView txSpecs;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
