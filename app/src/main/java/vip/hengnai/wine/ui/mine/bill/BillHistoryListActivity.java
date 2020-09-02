package vip.hengnai.wine.ui.mine.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.BillEntity;
import vip.hengnai.wine.entity.BillHistoryEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;

/**
 * 发票清单
 *
 * @author Hugh
 */
public class BillHistoryListActivity extends BaseMvpAppCompatActivity<IBillView, BillPresenter> implements IBillView {
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
    @BindView(R.id.img_money)
    ImageView imgMoney;
    @BindView(R.id.tx_distribution)
    TextView txDistribution;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private BillPresenter billPresenter;
    private List<BillHistoryEntity> billHistoryEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private BillHistoryProvider billHistoryProvider;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_histoy_list);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("发票清单");

        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                billHistoryEntityList.add(new BillHistoryEntity());
                billHistoryEntityList.add(new BillHistoryEntity());
                billHistoryEntityList.add(new BillHistoryEntity());
                mPatientsAdapter.notifyDataSetChanged();
                hideLoadingView();
            }
        });
        setAdapter();
    }
    /**
     * 优惠券列表布局
     */
    private void setAdapter() {
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        billHistoryProvider = new BillHistoryProvider(this);
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(billHistoryEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mPatientsAdapter.register(BillHistoryEntity.class, billHistoryProvider);
        recyclerview.setAdapter(emptyWrapper);
        billHistoryProvider.setOnClickListener(new BillHistoryProvider.OnClickListener() {
            @Override
            public void onItemClick(BillHistoryEntity billHistoryEntity) {
                //发票清单详情
                startActivity(new Intent(BillHistoryListActivity.this,BillHistoryActivity.class));
            }

        });
        billHistoryEntityList.add(new BillHistoryEntity());
        billHistoryEntityList.add(new BillHistoryEntity());
        billHistoryEntityList.add(new BillHistoryEntity());
        mPatientsAdapter.notifyDataSetChanged();
    }
    @Override
    protected BillPresenter initPresenter() {
        return billPresenter = new BillPresenter();
    }

    @Override
    public void showDatas(List<BillEntity> datas) {

    }

    @Override
    public void appendDatas(List<BillEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

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
}
