package vip.hengnai.wine.ui.menu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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


/**
 * @author Hugh
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.Holder> {

    private int clickPositon;
    private List list;
    private Context context;
    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    public LeftAdapter(List list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_left, parent, false);
        Holder vh = new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        if (position == clickPositon) {
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.tx_Title.setTextColor(ContextCompat.getColor(context, R.color.text_title_black3));
            holder.imageChoose.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.bg_activity_fragment));

            holder.tx_Title.setTextColor(ContextCompat.getColor(context, R.color.text_title_black4));
            holder.imageChoose.setVisibility(View.GONE);
        }
        holder.tx_Title.setText(list.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public void setClickPositon(int position) {
        clickPositon = position;
        notifyDataSetChanged();//更新view，否则点击背景不换
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_title)
        TextView tx_Title;
        @BindView(R.id.img_choose)
        ImageView imageChoose;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
