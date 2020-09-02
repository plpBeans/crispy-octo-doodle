package vip.hengnai.wine.ui.home.message;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MessageEntity;

public class MessageProvider extends ItemViewProvider<MessageEntity, MessageProvider.ProviderHolder> {



    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 点击事件
         *
         * @param position
         * @param messageEntity
         */
        void onItemClick(int position, MessageEntity messageEntity);
    }

    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.message_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final ProviderHolder holder, @NonNull final MessageEntity messageEntity) {

        holder.rlDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(getPosition(holder), messageEntity);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_title)
        TextView txTitle;
        @BindView(R.id.tx_content)
        TextView txContent;
        @BindView(R.id.rl_detail)
        RelativeLayout rlDetail;
        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}