package vip.hengnai.wine.ui.order;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.OrderEntity;
import vip.hengnai.wine.util.glide.MyGlideModule;


/**
 * 订单种的商品列图片文字结合
 *
 * @author Hugh
 */
public class OrderGoodsAdapter extends RecyclerView.Adapter<OrderGoodsAdapter.Holder> {


    private List<OrderEntity.ItemsBean> itemsBeanList;
    private int width;
    private Context context;

    public OrderGoodsAdapter(Context context, int width, List<OrderEntity.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
        this.context = context;
        this.width = width;
    }

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * item点击事件
         */
        void onItemClick();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_goods, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        //修改view大小
        Constants.setViewLayoutParams(holder.imgGoods, width);
        if(itemsBeanList.get(position).getImage().equals(((String)holder.itemView.getTag()))){

        }else{
            holder.itemView.setTag(itemsBeanList.get(position).getImage());
            MyGlideModule.loadImage(context,holder.imgGoods,itemsBeanList.get(position).getImage(),R.mipmap.icon_goods);
        }
        MyGlideModule.loadImage(context, holder.imgGoods, itemsBeanList.get(position).getImage(), R.mipmap.icon_goods);
        holder.txSingleGoodsName.setText(itemsBeanList.get(position).getGoodsName());
        holder.txSingleGoodsSpec.setText(itemsBeanList.get(position).getSpecName());
        holder.txSingleGoodsCount.setText("x" + itemsBeanList.get(position).getQuantity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsBeanList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_goods)
        ImageView imgGoods;
        @BindView(R.id.tx_singleGoodsName)
        TextView txSingleGoodsName;
        @BindView(R.id.tx_singleGoodsSpec)
        TextView txSingleGoodsSpec;
        @BindView(R.id.tx_singleGoodsCount)
        TextView txSingleGoodsCount;
        @BindView(R.id.rl_singleGoods)
        RelativeLayout rlSingleGoods;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

