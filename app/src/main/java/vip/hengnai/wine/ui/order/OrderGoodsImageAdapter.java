package vip.hengnai.wine.ui.order;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.util.glide.MyGlideModule;


/**
 * 订单种的商品列表纯图片
 * @author Hugh
 */
public class OrderGoodsImageAdapter extends RecyclerView.Adapter<OrderGoodsImageAdapter.Holder> {

    private List<String> itemsBeanList;
    private int width;
    private Context context;

    public OrderGoodsImageAdapter(Context context, int width, List<String> itemsBeanList) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_goods_image, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        //修改view大小
        Constants.setViewLayoutParams(holder.imgGoods, width);
        if(position==Constants.INT_4){
            holder.imgGoods.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.imgGoods.setImageResource(R.mipmap.ic_gengduo);
        }else{
            holder.imgGoods.setScaleType(ImageView.ScaleType.CENTER_CROP);
            MyGlideModule.loadImage(context,holder.imgGoods,itemsBeanList.get(position),R.mipmap.icon_goods);
        }
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
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

