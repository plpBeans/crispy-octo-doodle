package vip.hengnai.wine.util.rvwrapper;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vip.hengnai.wine.R;


/**
 *
 * @author zhy
 * @date 16/6/23
 */
public class EmptyWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;

    private RecyclerView.Adapter mInnerAdapter;
    private View mEmptyView;
    private TextView textView;
    private Button btnGoMenu;
    private int mEmptyLayoutId;
    private OnClickListener mOnClickListener;
    /**
     * 判断是否是购物车进来的购物车进来的显示不一样 0w为购物车
     */
    private int type;
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 点击事件
         * @param
         */
        void onButtonClick();

    }
    public EmptyWrapper(RecyclerView.Adapter adapter,int type) {
        mInnerAdapter = adapter;
        this.type=type;
    }

    private boolean isEmpty() {
        return (mEmptyView != null || mEmptyLayoutId != 0) && mInnerAdapter.getItemCount() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isEmpty()) {
            ViewHolder holder;
            if (mEmptyView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mEmptyView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mEmptyLayoutId);
                textView=holder.getView(R.id.tx_error);
                btnGoMenu=holder.getView(R.id.btn_goMenu);
                if(0==type){
                    textView.setText("您的购物车是空的");
                    btnGoMenu.setVisibility(View.VISIBLE);
                }else{
                    textView.setText("暂无数据");
                    btnGoMenu.setVisibility(View.GONE);
                }
                btnGoMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnClickListener.onButtonClick();
                    }
                });

            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isEmpty()) {
                    return gridLayoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });


    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        if (isEmpty()) {
            WrapperUtils.setFullSpan(holder);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isEmpty()) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) {
            return 1;
        }
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        mInnerAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        mInnerAdapter.unregisterAdapterDataObserver(observer);
    }

    public void setEmptyView(@NonNull View emptyView) {
        mEmptyView = emptyView;
    }
    public void setTextView(String s){
        textView.setText(s);
    }
    public void setEmptyView(@LayoutRes int layoutId) {
        mEmptyLayoutId = layoutId;
    }

}
