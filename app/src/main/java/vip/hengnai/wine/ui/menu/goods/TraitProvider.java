package vip.hengnai.wine.ui.menu.goods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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

/**
 * 商品特性provider
 *
 * @author Hugh
 */
public class TraitProvider extends ItemViewProvider<String, TraitProvider.Holder> {


    private OnClickListener mOnClickListener;

    private Drawable mMale;
    private Drawable mFemale;
    private Context context;
    private MultiTypeAdapter mPatientsAdapter;

    public TraitProvider(Context context) {
        this.context = context;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onItemClick(int position, String trait);
    }


    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(inflater.inflate(R.layout.good_item_trait, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final Holder holder, @NonNull final String trait) {
        holder.txFeature.setText(trait);


    }


    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_feature)
        TextView txFeature;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
