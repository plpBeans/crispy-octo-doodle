package vip.hengnai.wine.ui.order.detail;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderDetailEntity;
import vip.hengnai.wine.entity.OrderEntity;


/**
 * 订单后续操作按钮列表
 *
 * @author Hugh
 */
public class OrderDetailActionsAdapter extends RecyclerView.Adapter<OrderDetailActionsAdapter.Holder> {


    private List<OrderDetailEntity.ActionsBean> actionsBeanList;
    private Context context;

    public OrderDetailActionsAdapter(Context context, List<OrderDetailEntity.ActionsBean> actionsBeanList) {
        this.actionsBeanList = actionsBeanList;
        this.context = context;
    }

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * item点击事件
         *
         * @param actionsBean
         */
        void onItemClick(OrderDetailEntity.ActionsBean actionsBean);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail_actions, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        //1:去支付;2:取餐码;9:取消订单;11:去评价;12:开发票;19:再来一单
        if (Constants.VALUE_1.equals(actionsBeanList.get(position).getType())||Constants.VALUE_19.equals(actionsBeanList.get(position).getType()) ) {
            holder.btn.setBackgroundResource(R.mipmap.btn_fukuan);
            holder.btn.setTextColor(Color.WHITE);
        }else{
            holder.btn.setBackgroundResource(R.mipmap.btn_quxiao);
            holder.btn.setTextColor(Color.parseColor("#606161"));
        }
        holder.btn.setText(actionsBeanList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(actionsBeanList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return actionsBeanList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn)
        Button btn;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

