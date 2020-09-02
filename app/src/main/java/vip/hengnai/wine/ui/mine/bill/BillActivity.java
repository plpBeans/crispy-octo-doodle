package vip.hengnai.wine.ui.mine.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;

/**
 * 按订单开发票
 *
 * @author Hugh
 */
public class BillActivity extends BaseMvpAppCompatActivity<IBillView, BillPresenter> implements IBillView {

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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @BindView(R.id.btn_bill)
    Button btnBill;
    private BillPresenter billPresenter;
    private List<BillEntity> billEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private BillProvider billProvider;
    private LinearLayoutManager layoutManager;
    /**
     * 用来存储选中的集合订单
     */
    private List<String> billList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("按订单开票");

        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                billEntityList.add(new BillEntity());
                billEntityList.add(new BillEntity());
                billEntityList.add(new BillEntity());
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
        mPatientsAdapter = new MultiTypeAdapter();
        billProvider = new BillProvider(mPatientsAdapter);
        mPatientsAdapter.setItems(billEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mPatientsAdapter.register(BillEntity.class, billProvider);
        recyclerview.setAdapter(emptyWrapper);
        billProvider.setOnClickListener(new BillProvider.OnClickListener() {
            @Override
            public void onItemClick(boolean isCheck, BillEntity billEntity, int position) {
                //如果为true要加入到集合，反之从集合移除
                if (isCheck) {
                    billList.add(String.valueOf(position));
                } else {
                    billList.remove(String.valueOf(position));
                }
                billProvider.setList(position);
                showLongToast(billList.toString());
            }

        });
        billEntityList.add(new BillEntity());
        billEntityList.add(new BillEntity());
        billEntityList.add(new BillEntity());
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


    @OnClick({R.id.img_back, R.id.btn_bill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_bill:
                if(0==billEntityList.size()){
                    showLongToast("请选择订单开票");
                    return;
                }
                startActivity(new Intent(BillActivity.this, BillDetailActivity.class));
                break;
        }
    }
}
