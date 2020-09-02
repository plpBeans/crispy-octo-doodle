package vip.hengnai.wine.ui.mine.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MemberEntity;
import vip.hengnai.wine.eventbus.MainEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;

/**
 * 会员中心
 *
 * @author Hugh
 */
public class MemberCenterActivity extends BaseMvpAppCompatActivity<IMemberView, MemberCenterPresenter> implements IMemberView {
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
    @BindView(R.id.btn_goMenu)
    Button btnGoMenu;
    @BindView(R.id.btn_member)
    Button btnMember;
    @BindView(R.id.tx_userName)
    TextView txUserName;
    @BindView(R.id.tx_phone)
    TextView txPhone;
    @BindView(R.id.tx_integral)
    TextView txIntegral;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tx_levelOne)
    TextView txLevelOne;
    @BindView(R.id.tx_levelTwo)
    TextView txLevelTwo;
    @BindView(R.id.tx_levelThree)
    TextView txLevelThree;
    @BindView(R.id.tx_levelFour)
    TextView txLevelFour;
    @BindView(R.id.ll_level)
    LinearLayout llLevel;
    @BindView(R.id.tx_one)
    TextView txOne;
    @BindView(R.id.tx_two)
    TextView txTwo;
    @BindView(R.id.tx_three)
    TextView txThree;
    @BindView(R.id.tx_four)
    TextView txFour;
    @BindView(R.id.ll_tx_level)
    LinearLayout llTxLevel;
    @BindView(R.id.tx_quanyi)
    TextView txQuanyi;
    private MemberCenterPresenter memberCenterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_center);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("会员中心");


        setAdapter();
    }

    /**
     * 权益
     */
    private void setAdapter() {
        List<MemberEntity> memberEntityList = new ArrayList<>();
        memberEntityList.add(new MemberEntity());
        memberEntityList.add(new MemberEntity());
        memberEntityList.add(new MemberEntity());
        memberEntityList.add(new MemberEntity());
        recyclerview.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));

        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();

        MemberProvider memberProvider = new MemberProvider();


        mPatientsAdapter.register(MemberEntity.class, memberProvider);

        mPatientsAdapter.setItems(memberEntityList);
        recyclerview.setAdapter(mPatientsAdapter);
    }
    @Override
    protected MemberCenterPresenter initPresenter() {
        return memberCenterPresenter = new MemberCenterPresenter();
    }

    @Override
    public void showDatas(List<MemberEntity> datas) {

    }

    @Override
    public void appendDatas(List<MemberEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void showLoadingView() {
        showProgressDialog(null, getString(R.string.loading), null, true);
    }

    @Override
    public void hideLoadingView() {
        dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showLongToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick({R.id.img_back, R.id.btn_goMenu, R.id.btn_member})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_goMenu:
                EventBus.getDefault().post(new MainEvent().setIndex("1"));
                finish();
                break;
            case R.id.btn_member:
                break;
        }
    }
}
