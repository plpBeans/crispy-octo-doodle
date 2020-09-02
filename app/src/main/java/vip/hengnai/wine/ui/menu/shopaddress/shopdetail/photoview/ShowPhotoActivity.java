package vip.hengnai.wine.ui.menu.shopaddress.shopdetail.photoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.hengnai.wine.R;
import vip.hengnai.wine.framework.BaseAppCompatActivity;
import vip.hengnai.wine.util.StatusBarUtil;
import vip.hengnai.wine.util.glide.MyGlideModule;


/**
 * @author zh 2018/3/20
 */

public class ShowPhotoActivity extends BaseAppCompatActivity {

    @BindView(R.id.vp_images)
    ViewPager mViewPager;
    @BindView(R.id.ll_main_dot)
    LinearLayout mLinearLayoutDot;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    private List<ImageView> mImageViewList;
//    private List<ImageView> mImageViewDotList;

    private int currentPosition;//设置起始页面1为第一个
    private int dotPosition;    //设置起始小点0为第一个点
    private int prePosition;    //设置起始小点0为第一个点
    private List<String> imageurlses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        StatusBarUtil.setImmersiveStatusBar(this, false);
        ButterKnife.bind(this);
        initData();
        setViewPager();
        llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        imageurlses = intent.getStringArrayListExtra("imageurls");
        int currentItem = intent.getIntExtra("currentItem", 0);
        currentPosition = currentItem + 1;
        dotPosition = currentItem;
        prePosition = currentItem;

        mImageViewList = new ArrayList<>();
//        mImageViewDotList = new ArrayList();
        ImageView imageView;


        for (int i = 0; i < imageurlses.size() + 2; i++) {
            if (i == 0) {   //判断当i=0为该处的ImageView设置最后一张图片作为背景
                imageView = new ImageView(this);
                MyGlideModule.loadImage(ShowPhotoActivity.this, imageView, imageurlses.get(imageurlses.size() - 1), 0);

                mImageViewList.add(imageView);
            } else if (i == imageurlses.size() + 1) {   //判断当i=images.length+1时为该处的ImageView设置第一张图片作为背景
                imageView = new ImageView(this);
                MyGlideModule.loadImage(ShowPhotoActivity.this, imageView, imageurlses.get(0), 0);
//                Glide.with(this)
//                        .load(Constants.getBaseUrl() + imageurlses.get(0))
//                        .placeholder(R.mipmap.ic_kuaileshangchengshouye)
//                        .crossFade()
//                        .centerCrop()
//                        .into(imageView);

                mImageViewList.add(imageView);
            } else {  //其他情况则为ImageView设置images[i-1]的图片作为背景
                imageView = new ImageView(this);

                MyGlideModule.loadImage(ShowPhotoActivity.this, imageView, imageurlses.get(i - 1), 0);

//                Glide.with(this)
//                        .load(Constants.getBaseUrl() + imageurlses.get(i - 1))
//                        .placeholder(R.mipmap.ic_kuaileshangchengshouye)
//                        .crossFade()
//                        .centerCrop()
//                        .into(imageView);

                mImageViewList.add(imageView);
            }
        }
    }

    //  设置轮播小圆点
    private void setDot() {
        //  设置LinearLayout的子控件的宽高，这里单位是像素。
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
//        params.rightMargin = 20;
//        //  for循环创建images.length个ImageView（小圆点）
//        for (int i = 0; i < imageurlses.size(); i++) {
//            ImageView imageViewDot = new ImageView(this);
//            imageViewDot.setLayoutParams(params);
//            //  设置小圆点的背景为暗红图片
////            imageViewDot.setBackgroundResource(R.drawable.red_dot_night);
//            mLinearLayoutDot.addView(imageViewDot);
//            mImageViewDotList.add(imageViewDot);
//        }
        //设置第一个小圆点图片背景为红色
//        mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);

    }

    private void setViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(mImageViewList);
        adapter.setOnClickListener(new MyPagerAdapter.OnClickListener() {
            @Override
            public void onItemClick() {
                finish();
            }
        });
        mViewPager.setAdapter(adapter);

        mViewPager.setCurrentItem(currentPosition);
        //页面改变监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {    //判断当切换到第0个页面时把currentPosition设置为images.length,即倒数第二个位置，小圆点位置为length-1
                    currentPosition = imageurlses.size();
                    dotPosition = imageurlses.size() - 1;
                } else if (position == imageurlses.size() + 1) {    //当切换到最后一个页面时currentPosition设置为第一个位置，小圆点位置为0
                    currentPosition = 1;
                    dotPosition = 0;
                } else {
                    currentPosition = position;
                    dotPosition = position - 1;
                }
                //  把之前的小圆点设置背景为暗红，当前小圆点设置为红色
//                mImageViewDotList.get(prePosition).setBackgroundResource(R.drawable.red_dot_night);
//                mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);
//                prePosition = dotPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当state为SCROLL_STATE_IDLE即没有滑动的状态时切换页面
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mViewPager.setCurrentItem(currentPosition, false);
                }
            }
        });
    }
}
