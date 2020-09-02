package vip.hengnai.wine.ui.mine.bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.framework.BaseAppCompatActivity;

/**
 * 发票管理
 *
 * @author Hugh
 */
public class BillManagerActivity extends BaseAppCompatActivity {

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
    @BindView(R.id.rl_bill)
    RelativeLayout rlBill;
    @BindView(R.id.rl_billHistory)
    RelativeLayout rlBillHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_manager);
        ButterKnife.bind(this);
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("发票管理");
    }

    @OnClick({R.id.img_back, R.id.rl_bill, R.id.rl_billHistory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_bill:
                //按订单开发票
                startActivity(new Intent(BillManagerActivity.this,BillActivity.class));
                break;
            case R.id.rl_billHistory:
                //发票清单
                startActivity(new Intent(BillManagerActivity.this,BillHistoryListActivity.class));
                break;
        }
    }
}
