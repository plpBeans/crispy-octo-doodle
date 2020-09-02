package vip.hengnai.wine.ui.mine.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.BillEntity;
import vip.hengnai.wine.framework.BaseAppCompatActivity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;

/**
 * 发票历史详情
 *
 * @author Hugh
 */
public class BillHistoryActivity extends BaseMvpAppCompatActivity<IBillView, BillPresenter> implements IBillView {

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
    @BindView(R.id.tx_countBill)
    TextView txCountBill;
    @BindView(R.id.tx_countOrder)
    TextView txCountOrder;
    @BindView(R.id.tx_billType)
    TextView txBillType;
    @BindView(R.id.tx_billStatus)
    TextView txBillStatus;
    @BindView(R.id.tx_billPrice)
    TextView txBillPrice;
    @BindView(R.id.tx_billRise)
    TextView txBillRise;
    @BindView(R.id.tx_billCreateTime)
    TextView txBillCreateTime;
    @BindView(R.id.tx_billMail)
    TextView txBillMail;
    private BillPresenter billPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_history);
        ButterKnife.bind(this);
             /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("发票详情");
    }

    @Override
    protected BillPresenter initPresenter() {
        return billPresenter=new BillPresenter();
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
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
}
