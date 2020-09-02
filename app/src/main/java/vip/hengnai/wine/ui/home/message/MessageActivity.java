package vip.hengnai.wine.ui.home.message;

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
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MessageEntity;
import vip.hengnai.wine.entity.MessageTypeEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.home.message.detail.MessageDetailActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;
import vip.hengnai.wine.util.rvwrapper.LoadMoreWrapper;

/**
 * @author Hugh
 */
public class MessageActivity extends BaseMvpAppCompatActivity<IMessageView, MessagePresenter> implements IMessageView {
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
    private MessagePresenter messagePresenter;
    private LinearLayoutManager linearLayoutManager;
    private List<MessageEntity> messageEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private MessageProvider messageProvider;
    private LoadMoreWrapper mLoadMoreWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("消息中心");
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiprefresh.setRefreshing(false);
            }
        });
        setAdapter();
    }

    private void setAdapter() {
        messageEntityList.add(new MessageEntity());
        messageEntityList.add(new MessageEntity());
        messageEntityList.add(new MessageEntity());
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        messageProvider = new MessageProvider();
        mPatientsAdapter = new MultiTypeAdapter();
        mPatientsAdapter.setItems(messageEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mLoadMoreWrapper = new LoadMoreWrapper(this, recyclerview, emptyWrapper, 20);
        mPatientsAdapter.register(MessageEntity.class, messageProvider);
        recyclerview.setAdapter(mLoadMoreWrapper);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //加载更多网络请求
//                messagePresenter.getMessageList(Constants.GET_LIST_PAGE_INDEX, Constants.GET_LIST_PAGE_SIZE_20, publishLevel,  messageEntityList.size());
            }
        });
        messageProvider.setOnClickListener(new MessageProvider.OnClickListener() {
            @Override
            public void onItemClick(int position, MessageEntity messageEntity) {
                startActivity(new Intent(MessageActivity.this, MessageDetailActivity.class).putExtra("messageEntity", messageEntity));
            }

        });
    }

    @Override
    protected MessagePresenter initPresenter() {
        return messagePresenter = new MessagePresenter();
    }

    @Override
    public void showDatas(List<MessageEntity> datas) {
        messageEntityList.clear();
        messageEntityList.addAll(datas);
        setAdapter();
    }

    @Override
    public void appendDatas(List<MessageEntity> datas) {
        messageEntityList.addAll(datas);
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
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
