package vip.hengnai.wine.ui.mine.bill;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.BillEntity;

/**
 * 发票provider
 *
 * @author Hugh
 */
public class BillProvider extends ItemViewProvider<BillEntity, BillProvider.ProviderHolder> {

    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();
    private MultiTypeAdapter mMultiTypeAdapter;
    private Context mContext;
    public BillProvider(MultiTypeAdapter mMultiTypeAdapter) {
        this.mMultiTypeAdapter = mMultiTypeAdapter;
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
    public void setList( int position){
        mMultiTypeAdapter.notifyItemChanged(position);
    }
    public interface OnClickListener {

        /**
         * item点击事件
         *@param  isCheck 是否点击
         * @param billEntity
         * @param position
         */
        void onItemClick(boolean isCheck,BillEntity billEntity,int position);
    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.bill_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull BillEntity billEntity) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSelectArray.get(getPosition(holder),false)){
                    mSelectArray.put(getPosition(holder),false);
                }else{
                    mSelectArray.put(getPosition(holder),true);
                }
                mOnClickListener.onItemClick(mSelectArray.get(getPosition(holder)),billEntity,getPosition(holder));
            }
        });
        if(mSelectArray.get(getPosition(holder),false)){
            holder.imgSelect.setImageResource(R.mipmap.bg_xuan);
        }else{
            holder.imgSelect.setImageResource(R.mipmap.bg_moren);
        }
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.img_select)
        ImageView imgSelect;
        @BindView(R.id.tx_billPrice)
        TextView txBillPrice;
        @BindView(R.id.tx_billNumber)
        TextView txBillNumber;
        @BindView(R.id.tx_billTime)
        TextView txBillTime;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
