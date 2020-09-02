package vip.hengnai.wine.ui.mine.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;

/**
 * 开发票
 *
 * @author Hugh
 */
public class BillDetailActivity extends BaseMvpAppCompatActivity<IBillView, BillPresenter> implements IBillView {
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
    @BindView(R.id.btn_bill)
    Button btnBill;
    @BindView(R.id.tx_billNumber)
    TextView txBillNumber;
    @BindView(R.id.img_risePeople)
    ImageView imgRisePeople;
    @BindView(R.id.tx_risePeople)
    TextView txRisePeople;
    @BindView(R.id.img_riseCompany)
    ImageView imgRiseCompany;
    @BindView(R.id.tx_riseCompany)
    TextView txRiseCompany;
    @BindView(R.id.tx_company)
    TextView txCompany;
    @BindView(R.id.ed_company)
    EditText edCompany;
    @BindView(R.id.rl_company)
    RelativeLayout rlCompany;
    @BindView(R.id.tx_number)
    TextView txNumber;
    @BindView(R.id.ed_number)
    EditText edNumber;
    @BindView(R.id.rl_number)
    RelativeLayout rlNumber;
    @BindView(R.id.tx_bank)
    TextView txBank;
    @BindView(R.id.ed_bank)
    EditText edBank;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.tx_bankNumber)
    TextView txBankNumber;
    @BindView(R.id.ed_bankNumber)
    EditText edBankNumber;
    @BindView(R.id.rl_bankNumber)
    RelativeLayout rlBankNumber;
    @BindView(R.id.tx_address)
    TextView txAddress;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tx_phone)
    TextView txPhone;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.ll_company)
    LinearLayout llCompany;
    @BindView(R.id.img_choose)
    ImageView imgChoose;
    @BindView(R.id.tx_mail)
    TextView txMail;
    @BindView(R.id.ed_mail)
    EditText edMail;
    @BindView(R.id.rl_mail)
    RelativeLayout rlMail;
    @BindView(R.id.tx_content)
    TextView txContent;
    @BindView(R.id.tx_getContent)
    TextView txGetContent;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    private BillPresenter billPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("开发票");
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

    @OnClick({R.id.img_back, R.id.img_risePeople, R.id.img_riseCompany, R.id.btn_bill,R.id.img_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_risePeople:
                imgRisePeople.setImageResource(R.mipmap.bg_xuan);
                imgRiseCompany.setImageResource(R.mipmap.bg_moren);
                llCompany.setVisibility(View.GONE);
                break;
            case R.id.img_riseCompany:
                imgRisePeople.setImageResource(R.mipmap.bg_moren);
                imgRiseCompany.setImageResource(R.mipmap.bg_xuan);
                llCompany.setVisibility(View.VISIBLE);
                break;
            case R.id.img_choose:
                break;
            case R.id.btn_bill:
                break;
        }
    }
}
