package vip.hengnai.wine.ui.mine.beer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.BeerEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.home.FragmentHome;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;
import vip.hengnai.wine.util.rvwrapper.LoadMoreWrapper;
import vip.hengnai.wine.view.NewBanner;

/**
 * 啤酒不凡
 *
 * @author Hugh
 */
public class BeerActivity extends BaseMvpAppCompatActivity<IBeerView, BeerPresenter> implements IBeerView {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.banner)
    NewBanner banner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private BeerPresenter beerPresenter;
    private List<String> urlList = new ArrayList<>();
    private List<BeerEntity> beerEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private BeerProvider beerProvider;
    private LinearLayoutManager layoutManager;
    private LoadMoreWrapper mLoadMoreWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
              /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("啤酒不凡");
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                beerEntityList.clear();
                beerEntityList.add(new BeerEntity());
            }
        });
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        //设置图片集合
        banner.setImages(urlList);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showLongToast("点击了第" + position + "个");
            }
        });
        setAdapter();
    }

    @Override
    protected BeerPresenter initPresenter() {
        return beerPresenter = new BeerPresenter();
    }

    private void setAdapter() {
        beerEntityList.add(new BeerEntity());
        beerEntityList.add(new BeerEntity());
        beerEntityList.add(new BeerEntity());
        beerEntityList.add(new BeerEntity());
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        beerProvider = new BeerProvider();
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(beerEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter,1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mLoadMoreWrapper = new LoadMoreWrapper(this, recyclerview, emptyWrapper, 20);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //网络请求
//                beerPresenter.getPersonAddressList(Constants.GET_LIST_PAGE_INDEX, Constants.GET_LIST_PAGE_SIZE_20, list.size());
            }
        });
        mPatientsAdapter.register(BeerEntity.class, beerProvider);
        recyclerview.setAdapter(mLoadMoreWrapper);
        beerProvider.setOnClickListener(new BeerProvider.OnClickListener() {
            @Override
            public void onItemClick(BeerEntity beerEntity) {
                    startActivity(new Intent(BeerActivity.this,BeerDetailActivity.class));
            }
        });
    }


    @Override
    public void showDatas(List<BeerEntity> datas) {

        beerEntityList.clear();
        beerEntityList.addAll(datas);
        mLoadMoreWrapper.notifyDataSetChanged();
    }

    @Override
    public void appendDatas(List<BeerEntity> datas) {
        beerEntityList.addAll(datas);
        recyclerview.post(new Runnable() {
            @Override
            public void run() {
                mLoadMoreWrapper.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showLoadingMore() {
        mLoadMoreWrapper.loadingMore();
    }

    @Override
    public void noMoreData() {
        mLoadMoreWrapper.reachEnd();
    }

    @Override
    public void showLoadingView() {
        swiprefresh.setRefreshing(true);
    }

    @Override
    public void hideLoadingView() {
        swiprefresh.setRefreshing(false);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showLongToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
