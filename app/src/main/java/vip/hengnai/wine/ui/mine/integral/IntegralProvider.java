package vip.hengnai.wine.ui.mine.integral;

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
import vip.hengnai.wine.entity.IntegralEntity;

/**
 * 发票provider
 *
 * @author Hugh
 */
public class IntegralProvider extends ItemViewProvider<IntegralEntity, IntegralProvider.ProviderHolder> {


    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();

    private Context mContext;

    public IntegralProvider() {

    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {

        /**
         * item点击事件
         *
         * @param integralEntity
         */
        void onItemClick(IntegralEntity integralEntity);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.integral_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull IntegralEntity integralEntity) {


    }

    public class ProviderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_point)
        ImageView imgPoint;
        @BindView(R.id.tx_title)
        TextView txTitle;
        @BindView(R.id.tx_time)
        TextView txTime;
        @BindView(R.id.tx_count)
        TextView txCount;
        @BindView(R.id.ll_integral)
        LinearLayout llIntegral;
        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
