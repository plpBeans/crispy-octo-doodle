package vip.hengnai.wine.ui.mine.integral;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
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
import vip.hengnai.wine.entity.IntegralEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;

/**
 * 积分页面
 *
 * @author Hugh
 */
public class IntegralActivity extends BaseMvpAppCompatActivity<IIntegralView, IntegralPresenter> implements IIntegralView {


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
    @BindView(R.id.btn_guize)
    Button btnGuize;
    @BindView(R.id.tx_integral)
    TextView txIntegral;
    @BindView(R.id.tx_allIntegral)
    TextView txAllIntegral;
    @BindView(R.id.tx_quanyi)
    TextView txQuanyi;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_integral)
    Button btnIntegral;
    private IntegralPresenter integralPresenter;
    private List<IntegralEntity> integralEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter mPatientsAdapter;
    private IntegralProvider integralProvider;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("积分界面");


        setAdapter();
    }

    /**
     * 积分券列表布局
     */
    private void setAdapter() {
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mPatientsAdapter = new MultiTypeAdapter();
        integralProvider = new IntegralProvider();
        mPatientsAdapter.setItems(integralEntityList);
        emptyWrapper = new EmptyWrapper(mPatientsAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mPatientsAdapter.register(IntegralEntity.class, integralProvider);
        recyclerview.setAdapter(emptyWrapper);

        integralEntityList.add(new IntegralEntity());
        integralEntityList.add(new IntegralEntity());
        integralEntityList.add(new IntegralEntity());
        mPatientsAdapter.notifyDataSetChanged();
    }
    @Override
    protected IntegralPresenter initPresenter() {
        return integralPresenter=new IntegralPresenter();
    }

    @Override
    public void showDatas(List<IntegralEntity> datas) {

    }

    @Override
    public void appendDatas(List<IntegralEntity> datas) {

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

    @OnClick({R.id.img_back, R.id.btn_guize, R.id.btn_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_guize:
                showDialog();
                break;
            case R.id.btn_integral:
                break;
        }
    }
    /**
     * 积分规则
     */
    public void showDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this, R.style.ActionSheetDialogStyle).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_integral);
        window.setGravity(Gravity.CENTER);

    }
}
