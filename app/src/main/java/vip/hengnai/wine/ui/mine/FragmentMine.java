package vip.hengnai.wine.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.HomeDateEntity;
import vip.hengnai.wine.eventbus.LoginEvent;
import vip.hengnai.wine.framework.BaseMvpFragment;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.ui.mine.beer.BeerActivity;
import vip.hengnai.wine.ui.mine.bill.BillManagerActivity;
import vip.hengnai.wine.ui.mine.member.MemberCenterActivity;
import vip.hengnai.wine.ui.mine.integral.IntegralActivity;
import vip.hengnai.wine.ui.order.coupon.OrderOrderCouponActivity;
import vip.hengnai.wine.util.AuthUtil;
import vip.hengnai.wine.util.StatusBarUtil;

/**
 *我的个人中心
 * @author Hugh
 */
public class FragmentMine extends BaseMvpFragment<IMineView, MinePresenter> implements IMineView {



    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.tx_userName)
    TextView txUserName;
    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.tx_member)
    TextView txMember;
    @BindView(R.id.rl_member)
    RelativeLayout rlMember;
    @BindView(R.id.img_beer)
    ImageView imgBeer;
    @BindView(R.id.tx_beer)
    TextView txBeer;
    @BindView(R.id.rl_beer)
    RelativeLayout rlBeer;
    @BindView(R.id.img_mine)
    ImageView imgMine;
    @BindView(R.id.tx_mine)
    TextView txMine;
    @BindView(R.id.rl_mine)
    RelativeLayout rlMine;
    @BindView(R.id.img_bill)
    ImageView imgBill;
    @BindView(R.id.tx_bill)
    TextView txBill;
    @BindView(R.id.rl_bill)
    RelativeLayout rlBill;
    @BindView(R.id.img_help)
    ImageView imgHelp;
    @BindView(R.id.tx_help)
    TextView txHelp;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;
    private View view;
    private MinePresenter mPresenter;

    @Override
    protected MinePresenter initPresenter() {
        return mPresenter = new MinePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 优化View减少View的创建次数
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            /*设置头部栏高度*/
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
            layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
            rlTop.setLayoutParams(layoutParams);
            StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
            initView();
            return view;
        }

        return view;
    }

    private void initView() {


        if(AuthUtil.getAuthUtil(getActivity()).hasLoginIn()){
            txUserName.setText("楚振华");
        }
    }

    /**
     * 每次fragment显示的时候在此方法调取定位
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            // 不在最前端界面显示

        } else {
            StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
        }
    }

    @Override
    public void showDatas(List<HomeDateEntity> datas) {

    }

    @Override
    public void appendDatas(List<HomeDateEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }


    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showErrorMessage(@NonNull String message) {

    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(LoginEvent loginEvent) {
        txUserName.setText("楚振华");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.rl_member, R.id.rl_beer, R.id.rl_mine, R.id.rl_bill, R.id.rl_help,R.id.rl_top,R.id.ll_integral,R.id.ll_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_member:
                //个人中心
                startActivity(new Intent(getActivity(), MemberCenterActivity.class));
                break;
            case R.id.rl_beer:
                //啤酒不凡
                startActivity(new Intent(getActivity(), BeerActivity.class));
                break;
            case R.id.rl_mine:
                //个人资料
                startActivity(new Intent(getActivity(), MyInfoActivity.class));
                break;
            case R.id.rl_bill:
                //发票管理
                startActivity(new Intent(getActivity(), BillManagerActivity.class));
                break;
            case R.id.rl_help:
                break;
            case R.id.rl_top:
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
            case R.id.ll_integral:
                //积分
                startActivity(new Intent(getActivity(),IntegralActivity.class));
                break;
            case R.id.ll_coupon:
                //优惠券
//                startActivity(new Intent(getActivity(),OrderOrderCouponActivity.class));
                break;
        }
    }
}
