package vip.hengnai.wine.ui.order.confirm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.PayEntity;

/**
 * 商品规格provider
 *
 * @author Hugh
 */
public class PayProvider extends ItemViewProvider<PayEntity, PayProvider.Holder> {



    private OnClickListener mOnClickListener;
    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();

    private String id;

    public PayProvider(String id) {
        this.id = id;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onItemClick(PayEntity payEntity);
    }


    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(inflater.inflate(R.layout.pay_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final Holder holder, @NonNull final PayEntity payEntity) {
        holder.txPayTitle.setText(payEntity.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(payEntity);
            }
        });
        if (id.equals(payEntity.getId())) {
            holder.imgChoose.setImageResource(R.mipmap.icon_pay);
        } else {
            holder.imgChoose.setImageResource(R.mipmap.icon_no_pay);
        }

        if(payEntity.getName().contains("微信")){
            holder.imgPay.setImageResource(R.mipmap.bg_weixin);
        }else if(payEntity.getName().contains("支付宝")){
            holder.imgPay.setImageResource(R.mipmap.bg_zhifubao);
        }
    }


    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_pay)
        ImageView imgPay;
        @BindView(R.id.tx_payTitle)
        TextView txPayTitle;
        @BindView(R.id.tx_payDis)
        TextView txPayDis;
        @BindView(R.id.img_choose)
        ImageView imgChoose;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
