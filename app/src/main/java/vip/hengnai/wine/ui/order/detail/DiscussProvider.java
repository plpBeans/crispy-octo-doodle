package vip.hengnai.wine.ui.order.detail;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.DiscussEntity;

/**
 * 商品规格provider
 *
 * @author Hugh
 */
public class DiscussProvider extends ItemViewProvider<DiscussEntity, DiscussProvider.Holder> {


    private OnClickListener mOnClickListener;
    /**
     * SparseBooleanArray 存放boolean 类型的pair(key,value)
     */
    private SparseBooleanArray mSelectArray = new SparseBooleanArray();


    public DiscussProvider() {

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onItemClick(boolean isCheck, DiscussEntity discussEntity, int position);
    }

    /**
     * 获取点击的位置
     */

    public void setSelect(int position) {

        getAdapter().notifyItemChanged(position);
    }


    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(inflater.inflate(R.layout.discuss_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final Holder holder, @NonNull final DiscussEntity discussEntity) {
        holder.txDiscuss.setText(discussEntity.getContent());
        holder.txDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSelectArray.get(getPosition(holder), false)) {
                    mSelectArray.put(getPosition(holder), false);
                } else {
                    mSelectArray.put(getPosition(holder), true);
                }
                mOnClickListener.onItemClick(mSelectArray.get(getPosition(holder)), discussEntity, getPosition(holder));
            }
        });
        if (mSelectArray.get(getPosition(holder), false)) {
            holder.txDiscuss.setBackgroundResource(R.mipmap.dis_xuanzhong);
            holder.txDiscuss.setTextColor(Color.parseColor("#333434"));
        } else {
            holder.txDiscuss.setBackgroundResource(R.mipmap.dis_moren);
            holder.txDiscuss.setTextColor(Color.parseColor("#4F4F4F"));
        }

    }


    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_discuss)
        Button txDiscuss;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
