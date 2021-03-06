package vip.hengnai.wine.ui.menu.adapter;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.GoodsEntity;
import vip.hengnai.wine.ui.menu.IMenuView;

/**
 * @author Hugh
 */
public class ItemHeaderDecoration extends RecyclerView.ItemDecoration {
    private List<GoodsEntity> mDatas;
    private LayoutInflater mInflater;
    private int mTitleHeight;
    private IMenuView mCheckListener;
    private Context context;

    public static String currentTag = "0";
    /**
     * 左边list
     */
    private List<String> listLeft;
    public void setCheckListener(IMenuView checkListener) {
        mCheckListener = checkListener;
    }

    public ItemHeaderDecoration(Context context, List<GoodsEntity> datas, List<String> listLeft) {
        super();
        this.context=context;
        this.mDatas = datas;
        this.listLeft=listLeft;
        Paint paint = new Paint();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        int titleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
        paint.setTextSize(titleFontSize);
        paint.setAntiAlias(true);
        mInflater = LayoutInflater.from(context);
    }


    public ItemHeaderDecoration setData(List<GoodsEntity> mDatas) {
        this.mDatas = mDatas;
        return this;
    }

    public static void setCurrentTag(String currentTag) {
        ItemHeaderDecoration.currentTag = currentTag;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDrawOver(Canvas c, final RecyclerView parent, RecyclerView.State state) {
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        //获取当前item的标题
        String tag = mDatas.get(pos).getTag();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;
        boolean flag = false;
        if ((pos + 1) < mDatas.size()) {
            //获取下一个item的标题
           String suspensionTag = mDatas.get(pos + 1).getTag();
            if (null != tag && !tag.equals(suspensionTag)) {
                if (child.getHeight() + child.getTop() < mTitleHeight) {
                    c.save();
                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
                }
            }
        }

        View topTitleView = mInflater.inflate(R.layout.menu_item_right_title, parent, false);
        TextView tvTitle = (TextView) topTitleView.findViewById(R.id.tv_title);
        tvTitle.setText(listLeft.get(Integer.parseInt(tag)));
        int toDrawWidthSpec;//用于测量的widthMeasureSpec
        int toDrawHeightSpec;//用于测量的heightMeasureSpec
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) topTitleView.getLayoutParams();
        if (lp == null) {
            //这里是根据复杂布局layout的width height，new一个Lp
            lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            topTitleView.setLayoutParams(lp);
        }
        topTitleView.setLayoutParams(lp);
        if (lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            //如果是MATCH_PARENT，则用父控件能分配的最大宽度和EXACTLY构建MeasureSpec。
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.EXACTLY);
        } else if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            //如果是WRAP_CONTENT，则用父控件能分配的最大宽度和AT_MOST构建MeasureSpec。
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.AT_MOST);
        } else {
            //否则则是具体的宽度数值，则用这个宽度和EXACTLY构建MeasureSpec。
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(lp.width, View.MeasureSpec.EXACTLY);
        }
        //高度同理
        if (lp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.EXACTLY);
        } else if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.AT_MOST);
        } else {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(mTitleHeight, View.MeasureSpec.EXACTLY);
        }
        //依次调用 measure,layout,draw方法，将复杂头部显示在屏幕上。
        topTitleView.measure(toDrawWidthSpec, toDrawHeightSpec);
        topTitleView.layout(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getPaddingLeft() + topTitleView.getMeasuredWidth(), parent.getPaddingTop() + topTitleView.getMeasuredHeight());
        //Canvas默认在视图顶部，无需平移，直接绘制
        topTitleView.draw(c);
        if (flag) {
            c.restore();//恢复画布到之前保存的状态
        }
        /*
        如果左边的item的position与右边的tag不同，则将tag赋值给item的position，然后调用check方法实现左边联动
         */
        if (!TextUtils.equals(tag, currentTag)) {
            currentTag = tag;
            Integer integer = Integer.valueOf(currentTag);
            mCheckListener.check(integer, false);
        }

    }
}
