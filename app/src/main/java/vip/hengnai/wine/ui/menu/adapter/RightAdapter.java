package vip.hengnai.wine.ui.menu.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.GoodsEntity;
import vip.hengnai.wine.util.glide.MyGlideModule;


/**
 * @author Hugh
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.Holder> {


    private List<GoodsEntity> list;
    private Context context;
    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    public RightAdapter(Context context,List<GoodsEntity> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        Holder vh = new Holder(v, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        int itemViewType = RightAdapter.this.getItemViewType(position);
        switch (itemViewType) {
            case 0:
                holder.title.setText(list.get(position).getBigName());
                holder.itemView.setEnabled(false);
                break;
            case 1:
                holder.txGoodsTitle.setText(list.get(position).getName());
                holder.txRealPrice.setText("￥"+list.get(position).getConcPrice());
                holder.txDiscountPrice.setText("￥"+list.get(position).getPrice());
                holder.txDiscountPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG );
                if(list.get(position).getImage().equals(((String)holder.itemView.getTag()))){

                }else{
                        holder.itemView.setTag(list.get(position).getImage());
                    MyGlideModule.loadImage(context,holder.imgGoods,list.get(position).getImage(),R.mipmap.icon_goods);
                }

                holder.itemView.setEnabled(true);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(position);
                        }
                    }
                });
                break;
            case 2:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.menu_item_right_title : R.layout.menu_item_right_goods;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView txGoodsTitle,txRealPrice,txDiscountPrice;
        ImageView imgAdd,imgGoods;
        public Holder(View itemView, int type) {
            super(itemView);

            switch (type) {
                case 0:
                    title= (TextView) itemView.findViewById(R.id.tv_title);
                    break;
                case 1:
                    txGoodsTitle= (TextView) itemView.findViewById(R.id.tx_goodsTitle);
                    txRealPrice= (TextView) itemView.findViewById(R.id.tx_realPrice);
                    txDiscountPrice= (TextView) itemView.findViewById(R.id.tx_discountPrice);
                    imgAdd= (ImageView) itemView.findViewById(R.id.img_add);
                    imgGoods=(ImageView)itemView.findViewById(R.id.img_goods);
                    break;
                default:
                    break;
            }
        }

    }

}
