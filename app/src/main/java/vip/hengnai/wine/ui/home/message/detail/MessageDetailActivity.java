package vip.hengnai.wine.ui.home.message.detail;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MessageEntity;
import vip.hengnai.wine.framework.BaseAppCompatActivity;
import vip.hengnai.wine.view.AlignTextView;
import vip.hengnai.wine.view.SuperTextView;

public class MessageDetailActivity extends BaseAppCompatActivity {

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
    @BindView(R.id.tx_title)
    TextView txTitle;
    @BindView(R.id.tx_time)
    TextView txTime;
    @BindView(R.id.tx_content)
    AlignTextView txContent;
    private MessageEntity messageEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("消息详情");
        txContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        messageEntity = (MessageEntity) getIntent().getSerializableExtra("messageEntity");
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
