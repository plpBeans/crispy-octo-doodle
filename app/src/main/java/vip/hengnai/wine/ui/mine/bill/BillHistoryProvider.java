package vip.hengnai.wine.ui.mine.bill;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.BillEntity;
import vip.hengnai.wine.entity.BillHistoryEntity;

/**
 * 发票清单provider
 *
 * @author Hugh
 */
public class BillHistoryProvider extends ItemViewProvider<BillHistoryEntity, BillHistoryProvider.ProviderHolder> {



    private Context mContext;

    public BillHistoryProvider(Context mContext) {
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
         * @param billHistoryEntity
         */
        void onItemClick(BillHistoryEntity billHistoryEntity);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.bill_history_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull BillHistoryEntity billHistoryEntity) {
        holder.txIsOpen.setText("申请中");
        holder.txIsOpen.setTextColor(Color.parseColor("#DEA656"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(billHistoryEntity);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tx_peopleName)
        TextView txPeopleName;
        @BindView(R.id.tx_isOpen)
        TextView txIsOpen;
        @BindView(R.id.tx_orderPrice)
        TextView txOrderPrice;
        @BindView(R.id.tx_isElectron)
        TextView txIsElectron;
        @BindView(R.id.tx_orderTime)
        TextView txOrderTime;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
