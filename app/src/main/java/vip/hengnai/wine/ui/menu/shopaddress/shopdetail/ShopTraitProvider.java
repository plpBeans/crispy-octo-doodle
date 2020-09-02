package vip.hengnai.wine.ui.menu.shopaddress.shopdetail;

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
 * 商铺特点
 *
 * @author Hugh
 */
public class ShopTraitProvider extends ItemViewProvider<String, ShopTraitProvider.Holder> {


    private OnClickListener mOnClickListener;

    private Drawable mMale;
    private Drawable mFemale;
    private Context context;
    private MultiTypeAdapter mPatientsAdapter;

    public ShopTraitProvider(Context context) {
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
        return new Holder(inflater.inflate(R.layout.shop_item_trait, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final Holder holder, @NonNull final String trait) {
        holder.txTrait.setText(trait);

    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_trait)
        TextView txTrait;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
