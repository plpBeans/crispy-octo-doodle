package vip.hengnai.wine.ui.menu.personaddress;

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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.PoiAddressBean;
import vip.hengnai.wine.eventbus.AddressEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;
import vip.hengnai.wine.util.rvwrapper.LoadMoreWrapper;

/**
 * @author dell
 *         个人地址管理界面
 */
public class PersonAddressActivity extends BaseMvpAppCompatActivity<IPersonAddressView, PersonAddressPresenter> implements IPersonAddressView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;

    private PersonAddressPresenter personAddressPresenter;
    private List<PersonAddressEntity> list = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private PersonAddressProvider personAddressProvider;
    private LinearLayoutManager layoutManager;
    private LoadMoreWrapper mLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_address);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
           /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        initView();
    }

    @Override
    protected PersonAddressPresenter initPresenter() {
        return personAddressPresenter = new PersonAddressPresenter();
    }


    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        textTitle.setText("地址管理");
        textTitleRight.setText("添加");
        personAddressPresenter.getPersonAddressList();
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                personAddressPresenter.getPersonAddressList();
            }
        });
    }


    private void setAdapter() {
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        personAddressProvider = new PersonAddressProvider(this);
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(list);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter,1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
//        mLoadMoreWrapper = new LoadMoreWrapper(this, recyclerview, emptyWrapper, 20);
//        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                //网络请求
//                personAddressPresenter.getPersonAddressList(Constants.GET_LIST_PAGE_INDEX, Constants.GET_LIST_PAGE_SIZE_20, list.size());
//            }
//        });
        mPatientsAdapter.register(PersonAddressEntity.class, personAddressProvider);
        recyclerview.setAdapter(emptyWrapper);
        personAddressProvider.setOnClickListener(new PersonAddressProvider.OnClickListener() {
            @Override
            public void onItemClick(PersonAddressEntity personAddressEntity) {
                Intent intent = new Intent();
                intent.putExtra("personAddressEntity", personAddressEntity);
                intent.putExtra("type", "1");
                intent.setClass(PersonAddressActivity.this, ModifyAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AddressEvent addressEvent ) {
        personAddressPresenter.getPersonAddressList();
    }
    @Override
    public void showDatas(List<PersonAddressEntity> datas) {
        setAdapter();
        list.clear();
        list.addAll(datas);
        setAdapter();
    }

    @Override
    public void appendDatas(List<PersonAddressEntity> datas) {
        list.addAll(datas);
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
        setRefresh(true, swiprefresh);
    }

    @Override
    public void hideLoadingView() {
        setRefresh(false, swiprefresh);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(PersonAddressActivity.this, LoginActivity.class));
        finish();
    }



    @OnClick({R.id.img_back, R.id.text_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.text_title_right:
                /**
                 * 从我的地址界面进入到添加新地址界面
                 * type 0 新增地址  1 修改地址
                 */
                Intent intent = new Intent();
                intent.putExtra("type", "0");
                intent.setClass(PersonAddressActivity.this, ModifyAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void deletePersonAddressSuccess(String message) {

    }

    @Override
    public void modifyPersonAddressSuccess(String message) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
